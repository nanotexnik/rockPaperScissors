package com.kzhukov.rps.game.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class GameController {
    private final GameService gameService;

    @PostMapping("/move")
    public @ResponseBody
    MakeMoveResponse makeMove(@Valid @RequestBody MakeMoveRequest request) {
        return gameService.makeMove(request);
    }
}
