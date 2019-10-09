package com.kzhukov.rps.bot.random;

import com.kzhukov.rps.bot.BotWithGuaranteeMove;
import com.kzhukov.rps.game.Move;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RandomStrategyBot implements BotWithGuaranteeMove {
    private final RandomMoveGenerator moveGenerator;

    @Override
    public Move makeMove() {
        return moveGenerator.generateMove();
    }
}
