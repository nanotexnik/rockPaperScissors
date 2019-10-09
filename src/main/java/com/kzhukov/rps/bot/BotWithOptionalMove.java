package com.kzhukov.rps.bot;

import com.kzhukov.rps.game.Move;

import java.util.Optional;

public interface BotWithOptionalMove extends Bot {
    Optional<Move> makeMove();
}
