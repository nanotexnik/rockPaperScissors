package com.kzhukov.rps.game.controller;

import com.kzhukov.rps.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class GameController {
    private final GameService gameService;

    public MakeMoveResponse makeMove(MakeMoveRequest request) {
        return gameService.makeMove(request);
    }
}
