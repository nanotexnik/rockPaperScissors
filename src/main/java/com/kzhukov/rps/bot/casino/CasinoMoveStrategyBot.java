package com.kzhukov.rps.bot.casino;

import com.kzhukov.rps.bot.BotFactory;
import com.kzhukov.rps.bot.BotWithGuaranteeMove;
import com.kzhukov.rps.game.Move;
import com.kzhukov.rps.gamesession.GameHistory;

import java.util.List;
import java.util.Optional;

public class CasinoMoveStrategyBot implements BotWithGuaranteeMove {
    private final List<GameHistory> history;
    private final BotFactory botFactory;

    public CasinoMoveStrategyBot(List<GameHistory> history, BotFactory botFactory) {
        this.history = history;
        this.botFactory = botFactory;
    }

    @Override
    public Move makeMove() {
        Optional<Move> markovMove = botFactory.createMarkovStrategyBot(history).makeMove();
        return markovMove.orElseGet(() -> botFactory.createRandomStrategyBot().makeMove());
    }
}
