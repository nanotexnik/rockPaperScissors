package com.kzhukov.rps.gamesession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Game session not found")
public class GameSessionNotExists extends RuntimeException {
    public GameSessionNotExists(String uuid) {
        super(String.format("Game session with uuid = '%s' does't exist. Please create a new one", uuid));
    }
}
