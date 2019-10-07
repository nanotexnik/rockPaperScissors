package com.kzhukov.rps.game;

import com.kzhukov.rps.bot.BotFactory;
import com.kzhukov.rps.game.controller.MakeMoveRequest;
import com.kzhukov.rps.game.controller.MakeMoveResponse;
import com.kzhukov.rps.gamesession.GameHistory;
import com.kzhukov.rps.gamesession.GameSession;
import com.kzhukov.rps.gamesession.GameSessionNotExists;
import com.kzhukov.rps.gamesession.GameSessionRepository;
import com.kzhukov.rps.hash.MoveHash;
import com.kzhukov.rps.hash.MoveHashGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final BotFactory botFactory;
    private final GameSessionRepository gameSessionRepository;
    private final GameRules game;
    private final MoveHashGenerator hashGenerator;

    public MakeMoveResponse makeMove(MakeMoveRequest request) {
        GameSession gameSession = gameSessionRepository.findGameSession(request.getGameSessionUuid())
                .orElseThrow(() -> new GameSessionNotExists(request.getGameSessionUuid()));

        GameResult result = computeGameResult(request.getUserMove(), gameSession.getBotMove());
        gameSession.addGameResult(GameHistory.of(
                request.getUserMove(),
                gameSession.getBotMove()
        ), result);

        Move move = makeNextMove(gameSession.getGamesHistory());
        MoveHash nextMoveHash = hashGenerator.computeHash(move);

        return MakeMoveResponse.of(
                result,
                gameSession.getMoveHash().getSecret(),
                nextMoveHash.getHash()
        );
    }

    private Move makeNextMove(List<GameHistory> history) {
        Optional<Move> markoveMove = botFactory.createMarkovStrategyBot(history).makeMove();
        if (markoveMove.isPresent()) {
            return markoveMove.get();
        } else {
            return botFactory.createRandomStrategyBot().makeMove().orElseThrow(IllegalStateException::new);
        }
    }

    private GameResult computeGameResult(Move userMove, Move botMove) {
        int result = game.play(userMove, botMove);
        if (result == 0) {
            return GameResult.DRAW;
        } else if (result == 1) {
            return GameResult.USER_LOSE;
        } else if (result == -1) {
            return GameResult.USER_WIN;
        }

        throw new IllegalStateException();
    }
}
