package com.kzhukov.rps.gamesession;

import com.kzhukov.rps.game.GameResult;
import com.kzhukov.rps.game.Move;
import com.kzhukov.rps.hash.MoveHash;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Data(staticConstructor = "of")
public class GameSession {
    private final Move botMove;
    private final MoveHash moveHash;

    private GameSessionStatistics statistics = new GameSessionStatistics();
    private List<GameHistory> gamesHistory = new CopyOnWriteArrayList<>();

    public void addGameResult(GameHistory gameHistory, GameResult result) {
        gamesHistory.add(gameHistory);
        statistics.update(result);
    }
}
