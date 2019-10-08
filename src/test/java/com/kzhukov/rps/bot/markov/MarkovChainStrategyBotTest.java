package com.kzhukov.rps.bot.markov;

import com.kzhukov.rps.game.Move;
import com.kzhukov.rps.gamesession.GameHistory;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MarkovChainStrategyBotTest {
    @Test
    public void markovStrategy_emptyHistory_noMove() {
        MarkovChainStrategyBot markovChainStrategyBot = new MarkovChainStrategyBot(emptyList());
        Optional<Move> move = markovChainStrategyBot.makeMove();
        assertThat(move.isPresent(), is(false));
    }

    @Test
    public void markovStrategy_RockAfterRockPaper_botMovePaper() {
        List<GameHistory> gamesHistory =
                Arrays.asList(GameHistory.of(Move.ROCK, Move.PAPER), GameHistory.of(Move.ROCK, Move.ROCK),
                        GameHistory.of(Move.ROCK, Move.PAPER));

        MarkovChainStrategyBot markovChainStrategyBot = new MarkovChainStrategyBot(gamesHistory);
        Optional<Move> move = markovChainStrategyBot.makeMove();
        assertThat(move.get(), is(Move.PAPER));
    }

    @Test
    public void markovStrategy_withTwoRockPaperRockAndThreeRockPaperPaper_botMoveScissors() {
        List<GameHistory> gamesHistory =
                Arrays.asList(GameHistory.of(Move.ROCK, Move.PAPER), GameHistory.of(Move.ROCK, Move.PAPER),
                        GameHistory.of(Move.PAPER, Move.PAPER), GameHistory.of(Move.ROCK, Move.PAPER),
                        GameHistory.of(Move.PAPER, Move.PAPER), GameHistory.of(Move.ROCK, Move.PAPER),
                        GameHistory.of(Move.PAPER, Move.PAPER), GameHistory.of(Move.ROCK, Move.PAPER));

        MarkovChainStrategyBot markovChainStrategyBot = new MarkovChainStrategyBot(gamesHistory);
        Optional<Move> move = markovChainStrategyBot.makeMove();
        assertThat(move.get(), is(Move.SCISSORS));
    }
}
