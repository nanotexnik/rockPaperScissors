package com.kzhukov.rps.gamesession;

import com.kzhukov.rps.game.Move;
import com.kzhukov.rps.hash.MoveHash;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameSessionRepository {
    private final Map<String, GameSession> sessions = new ConcurrentHashMap<>();

    public void createGameSession(String gameSessionUUID, Move firstBotMove, MoveHash moveHash) {
        sessions.put(gameSessionUUID, GameSession.of(firstBotMove, moveHash));
    }

    public Optional<GameSession> findGameSession(String uuid) {
        return Optional.ofNullable(sessions.get(uuid));
    }

    public void deleteGameSession(String uuid) {
        sessions.remove(uuid);
    }
}
