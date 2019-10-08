package com.kzhukov.rps.game.controller;

import com.kzhukov.rps.game.Move;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MakeMoveRequest {
    @NotNull(message = "userMove is mandatory")
    private Move userMove;
    @NotNull(message = "gameSessionUuid is mandatory")
    private String gameSessionUuid;
}
