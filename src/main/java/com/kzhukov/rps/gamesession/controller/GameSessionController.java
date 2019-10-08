package com.kzhukov.rps.gamesession.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class GameSessionController {
    private final GameSessionService gameSessionService;

    @GetMapping("/game-session")
    public @ResponseBody
    CreateGameSessionResponse createGameSession() {
        return gameSessionService.createGameSession();
    }
}
