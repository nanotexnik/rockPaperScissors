package com.kzhukov.rps.gamesession;

import com.kzhukov.rps.game.GameResult;

import java.util.concurrent.atomic.AtomicInteger;

public class GameSessionStatistics {
    private AtomicInteger wins = new AtomicInteger(0);
    private AtomicInteger loses = new AtomicInteger(0);
    private AtomicInteger draws = new AtomicInteger(0);

    public void update(GameResult result) {
        switch (result) {
            case USER_WIN:
                wins.incrementAndGet();
            case USER_LOSE:
                loses.incrementAndGet();
            case DRAW:
                draws.incrementAndGet();
        }
    }
}
