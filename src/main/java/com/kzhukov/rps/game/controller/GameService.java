package com.kzhukov.rps.game.controller;

import com.kzhukov.rps.bot.BotFactory;
import com.kzhukov.rps.game.GameResult;
import com.kzhukov.rps.game.GameRules;
import com.kzhukov.rps.game.Move;
import com.kzhukov.rps.gamesession.GameHistory;
import com.kzhukov.rps.gamesession.GameSession;
import com.kzhukov.rps.gamesession.GameSessionNotExists;
import com.kzhukov.rps.gamesession.GameSessionRepository;
import com.kzhukov.rps.hash.MoveHash;
import com.kzhukov.rps.hash.MoveHashGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (gameSession) {
            GameResult result = computeGameResult(request.getUserMove(), gameSession.getBotMove());
            gameSession.addGameResult(GameHistory.of(
                    request.getUserMove(),
                    gameSession.getBotMove()
            ), result);

            Move botMove = botFactory.createCasinoStrategy(gameSession.getGamesHistory()).makeMove();
            MoveHash nextBotMoveHash = hashGenerator.computeHash(botMove);

            String previousSecret = gameSession.getMoveHash().getSecret();
            gameSession.updateNextBotMove(nextBotMoveHash, botMove);
            return MakeMoveResponse.of(
                    result,
                    previousSecret,
                    nextBotMoveHash.getHash()
            );
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
