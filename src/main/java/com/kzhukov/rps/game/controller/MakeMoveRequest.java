package com.kzhukov.rps.game.controller;

import com.kzhukov.rps.game.Move;
import lombok.Data;

@Data
public class MakeMoveRequest {
    private Move userMove;
    private String gameSessionUuid;
}
