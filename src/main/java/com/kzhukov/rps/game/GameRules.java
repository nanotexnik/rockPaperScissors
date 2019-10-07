package com.kzhukov.rps.game;

import org.springframework.stereotype.Component;

@Component
public class GameRules {
    /*
    Return -1 if first win
    0 if draw
    1 if second win
     */
    public int play(Move first, Move second) {
        if (first.equals(second)) {
            return 0;
        }

        if (first.isLoseTo(second)) {
            return 1;
        } else {
            return -1;
        }
    }
}
