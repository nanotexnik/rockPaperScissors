package com.kzhukov.rps.gamesession.controller;

import com.kzhukov.rps.bot.BotFactory;
import com.kzhukov.rps.game.Move;
import com.kzhukov.rps.gamesession.GameSessionRepository;
import com.kzhukov.rps.hash.MoveHash;
import com.kzhukov.rps.hash.MoveHashGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameSessionService {
    private final BotFactory botFactory;
    private final MoveHashGenerator hashGenerator;
    private final GameSessionRepository gameSessionRepository;

    public CreateGameSessionResponse createGameSession() {
        Move move = botFactory.createRandomStrategyBot().makeMove();
        MoveHash moveHash = hashGenerator.computeHash(move);

        String gameSessionUUID = UUID.randomUUID().toString();
        gameSessionRepository.createGameSession(gameSessionUUID, move, moveHash);

        return CreateGameSessionResponse.of(gameSessionUUID, moveHash.getHash());
    }

    public void deleteGameSession(String gameSessionUuid) {
        gameSessionRepository.deleteGameSession(gameSessionUuid);
    }
}
