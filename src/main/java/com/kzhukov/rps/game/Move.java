package com.kzhukov.rps.game;

public enum Move {
    ROCK,
    PAPER,
    SCISSORS;

    static {
        ROCK.loseTo = PAPER;
        PAPER.loseTo = SCISSORS;
        SCISSORS.loseTo = ROCK;
    }

    private Move loseTo;

    public boolean isLoseTo(Move move) {
        return this.loseTo.equals(move);
    }
}
