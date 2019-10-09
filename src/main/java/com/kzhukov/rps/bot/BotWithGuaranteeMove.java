package com.kzhukov.rps.bot;

import com.kzhukov.rps.game.Move;

public interface BotWithGuaranteeMove extends Bot {
    Move makeMove();
}
