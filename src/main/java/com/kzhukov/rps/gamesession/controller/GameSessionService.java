package com.kzhukov.rps.gamesession.controller;

import com.kzhukov.rps.bot.random.RandomStrategyBot;
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
    private final RandomStrategyBot bot;
    private final MoveHashGenerator hashGenerator;
    private final GameSessionRepository gameSessionRepository;

    public CreateGameSessionResponse createGameSession() {
        Move move = bot.makeMove().orElseThrow(IllegalStateException::new);
        MoveHash moveHash = hashGenerator.computeHash(move);

        String gameSessionUUID;
        do {
            gameSessionUUID = UUID.randomUUID().toString();
        } while (!gameSessionRepository.createGameSession(gameSessionUUID, move, moveHash));

        return CreateGameSessionResponse.of(gameSessionUUID, moveHash.getHash());
    }
}
