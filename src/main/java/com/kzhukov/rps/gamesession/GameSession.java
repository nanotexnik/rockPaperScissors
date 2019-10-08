package com.kzhukov.rps.gamesession;

import com.kzhukov.rps.game.GameResult;
import com.kzhukov.rps.game.Move;
import com.kzhukov.rps.gamesession.statistics.GameSessionStatistics;
import com.kzhukov.rps.hash.MoveHash;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data(staticConstructor = "of")
public class GameSession {
    @NonNull
    private Move botMove;
    @NonNull
    private MoveHash moveHash;

    private GameSessionStatistics statistics = new GameSessionStatistics();
    private List<GameHistory> gamesHistory = new ArrayList<>();

    public void addGameResult(GameHistory gameHistory, GameResult result) {
        gamesHistory.add(gameHistory);
        statistics.update(result);
    }

    public void updateNextBotMove(MoveHash nextBotMoveHash, Move botMove) {
        this.botMove = botMove;
        this.moveHash = nextBotMoveHash;
    }
}
