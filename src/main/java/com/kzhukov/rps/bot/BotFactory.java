package com.kzhukov.rps.bot;

import com.kzhukov.rps.bot.markov.MarkovChainStrategyBot;
import com.kzhukov.rps.bot.random.RandomMoveGenerator;
import com.kzhukov.rps.bot.random.RandomStrategyBot;
import com.kzhukov.rps.gamesession.GameHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BotFactory {
    private final RandomMoveGenerator moveGenerator;

    public Bot createRandomStrategyBot() {
        return new RandomStrategyBot(moveGenerator);
    }

    public Bot createMarkovStrategyBot(List<GameHistory> history) {
        return new MarkovChainStrategyBot(history);
    }
}
