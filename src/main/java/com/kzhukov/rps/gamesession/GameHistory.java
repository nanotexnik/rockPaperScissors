package com.kzhukov.rps.gamesession;

import com.kzhukov.rps.game.Move;
import lombok.Data;

@Data(staticConstructor = "of")
public class GameHistory {
    private final Move userMove;
    private final Move botMove;
}
