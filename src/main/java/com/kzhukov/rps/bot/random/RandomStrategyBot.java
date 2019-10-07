package com.kzhukov.rps.bot.random;

import com.kzhukov.rps.bot.Bot;
import com.kzhukov.rps.game.Move;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RandomStrategyBot implements Bot {
    private final RandomMoveGenerator moveGenerator;

    @Override
    public Optional<Move> makeMove() {
        return Optional.of(moveGenerator.generateMove());
    }
}
