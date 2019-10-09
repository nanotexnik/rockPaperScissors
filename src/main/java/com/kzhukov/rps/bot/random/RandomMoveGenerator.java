package com.kzhukov.rps.bot.random;

import com.kzhukov.rps.game.Move;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomMoveGenerator {
    private static final List<Move> moves = Collections.unmodifiableList(Arrays.asList(Move.values()));

    public Move generateMove() {
        return moves.get(ThreadLocalRandom.current().nextInt(moves.size()));
    }
}
