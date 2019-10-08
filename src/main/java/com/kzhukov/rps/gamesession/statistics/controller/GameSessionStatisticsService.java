package com.kzhukov.rps.gamesession.statistics.controller;

import com.kzhukov.rps.gamesession.GameSession;
import com.kzhukov.rps.gamesession.GameSessionNotExists;
import com.kzhukov.rps.gamesession.GameSessionRepository;
import com.kzhukov.rps.gamesession.statistics.GameSessionStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameSessionStatisticsService {
    private final GameSessionRepository repository;

    public GameSessionStatistics getGameSessionStatistics(String sessionUuid) {
        GameSession gameSession = repository.findGameSession(sessionUuid)
                .orElseThrow(() -> new GameSessionNotExists(sessionUuid));

        return gameSession.getStatistics();
    }
}
