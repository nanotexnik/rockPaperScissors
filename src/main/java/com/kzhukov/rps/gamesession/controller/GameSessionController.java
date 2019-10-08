package com.kzhukov.rps.gamesession.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class GameSessionController {
    private final GameSessionService gameSessionService;

    @PostMapping("/game-session")
    public @ResponseBody
    CreateGameSessionResponse createGameSession() {
        return gameSessionService.createGameSession();
    }

    @DeleteMapping("/game-session/{uuid}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteGameSession(@PathVariable("uuid") String gameSessionUuid) {
        gameSessionService.deleteGameSession(gameSessionUuid);
    }

}
