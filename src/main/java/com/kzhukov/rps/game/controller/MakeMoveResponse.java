package com.kzhukov.rps.game.controller;

import com.kzhukov.rps.game.GameResult;
import lombok.Data;

@Data(staticConstructor = "of")
public class MakeMoveResponse {
    private final GameResult result;
    private final String secret;
    private final String nextCasinoMoveHash;
}
