package com.kzhukov.rps.gamesession;

import com.kzhukov.rps.game.Move;
import com.kzhukov.rps.hash.MoveHash;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class GameSessionRepository {
    private Map<String, GameSession> sessions = new ConcurrentHashMap<>();

    public boolean createGameSession(String gameSessionUUID, Move firstBotMove, MoveHash moveHash) {
        if (sessions.containsKey(gameSessionUUID)) {
            return false;
        } else {
            sessions.put(gameSessionUUID, GameSession.of(firstBotMove, moveHash));
            return true;
        }
    }

    public Optional<GameSession> findGameSession(String uuid) {
        return Optional.ofNullable(sessions.get(uuid));
    }
}
