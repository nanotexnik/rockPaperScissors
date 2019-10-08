package com.kzhukov.rps.gamesession.statistics;

import com.kzhukov.rps.game.GameResult;
import lombok.Getter;

@Getter
public class GameSessionStatistics {
    private int wins = 0;
    private int loses = 0;
    private int draws = 0;

    public void update(GameResult result) {
        switch (result) {
            case USER_WIN:
                wins++;
                break;
            case USER_LOSE:
                loses++;
                break;
            case DRAW:
                draws++;
                break;
        }
    }
}
