package com.kzhukov.rps.gamesession.controller;

import org.springframework.stereotype.Controller;

@Controller
public class GameSessionController {
    private GameSessionService gameSessionService;

    public CreateGameSessionResponse createGameSession() {
        return gameSessionService.createGameSession();
    }
}
