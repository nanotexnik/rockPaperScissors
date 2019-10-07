package com.kzhukov.rps.bot.markov;

import com.kzhukov.rps.bot.Bot;
import com.kzhukov.rps.game.Move;
import com.kzhukov.rps.gamesession.GameHistory;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MarkovChainStrategyBot implements Bot {
    private List<GameHistory> gameHistory;

    public MarkovChainStrategyBot(List<GameHistory> gameHistory) {
        this.gameHistory = gameHistory;
    }

    @Override
    public Optional<Move> makeMove() {
        Stream<MarkovHistory> markovHistory = IntStream.range(1, gameHistory.size())
                .mapToObj(i -> {
                    GameHistory previousMove = this.gameHistory.get(i - 1);
                    return MarkovHistory.of(
                            this.gameHistory.get(i).getUserMove(),
                            previousMove.getUserMove(),
                            previousMove.getBotMove()
                    );
                });

        GameHistory lastUserMove = this.gameHistory.get(this.gameHistory.size() - 1);

        Map<Move, Long> nextMoveCounts =
                markovHistory.filter(h -> h.getPreviousUserMove().equals(lastUserMove.getUserMove())
                        && h.getPreviousBotMove().equals(lastUserMove.getBotMove()))
                        .collect(Collectors.groupingBy(MarkovHistory::getUserMove, Collectors.counting()));


        Optional<Move> nextMove = nextMoveCounts.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey);

        return nextMove;
    }
}

@Data(staticConstructor = "of")
class MarkovHistory {
    private final Move userMove;
    private final Move previousUserMove;
    private final Move previousBotMove;
}
