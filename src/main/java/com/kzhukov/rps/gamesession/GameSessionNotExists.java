package com.kzhukov.rps.gamesession;

public class GameSessionNotExists extends RuntimeException {
    public GameSessionNotExists(String uuid) {
        super(String.format("Game session with uuid = '%s' does't create. Please create a new one", uuid));
    }
}
