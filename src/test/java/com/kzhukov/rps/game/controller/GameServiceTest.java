package com.kzhukov.rps.game.controller;

import com.kzhukov.rps.bot.BotFactory;
import com.kzhukov.rps.bot.casino.CasinoMoveStrategyBot;
import com.kzhukov.rps.game.GameResult;
import com.kzhukov.rps.game.GameRules;
import com.kzhukov.rps.game.Move;
import com.kzhukov.rps.gamesession.GameHistory;
import com.kzhukov.rps.gamesession.GameSession;
import com.kzhukov.rps.gamesession.GameSessionNotExists;
import com.kzhukov.rps.gamesession.GameSessionRepository;
import com.kzhukov.rps.hash.MoveHash;
import com.kzhukov.rps.hash.MoveHashGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    public static final String GAMESESSION_UUID = "abc";
    @InjectMocks
    private GameService gameService;

    @Mock
    private BotFactory botFactory;
    @Mock
    private GameSessionRepository repository;
    @Mock
    private GameRules gameRules;
    @Mock
    private MoveHashGenerator hashGenerator;

    private GameSession gameSession;

    @Before
    public void setUp() {
        //init default behaviour in order to avoid NPE
        gameSession = stubGameSession();
        when(repository.findGameSession(GAMESESSION_UUID)).thenReturn(Optional.of(gameSession));
        CasinoMoveStrategyBot bot = mock(CasinoMoveStrategyBot.class);
        when(botFactory.createCasinoStrategy(gameSession.getGamesHistory())).thenReturn(bot);
        Move botMove = Move.SCISSORS;
        when(bot.makeMove()).thenReturn(botMove);
        MoveHash nextMoveHash = MoveHash.of("resulting-secret",
                "resulting-hash");
        when(hashGenerator.computeHash(botMove)).thenReturn(nextMoveHash);
    }

    @Test(expected = GameSessionNotExists.class)
    public void makeMove_sessionDoesNotExists_exception() {
        when(repository.findGameSession(GAMESESSION_UUID)).thenReturn(Optional.empty());
        gameService.makeMove(stubMakeMoveRequest());
    }

    @Test
    public void makeMove_casinoStrategyReturnsMove_responseAndRepositoryContainsHashOfMove() {
        //arrange
        CasinoMoveStrategyBot bot = mock(CasinoMoveStrategyBot.class);
        when(botFactory.createCasinoStrategy(gameSession.getGamesHistory())).thenReturn(bot);
        Move botMove = Move.SCISSORS;
        when(bot.makeMove()).thenReturn(botMove);
        MoveHash nextMoveHash = MoveHash.of("resulting-secret",
                "resulting-hash");
        when(hashGenerator.computeHash(botMove)).thenReturn(nextMoveHash);
        //act
        MakeMoveResponse response = gameService.makeMove(stubMakeMoveRequest());
        //assert
        assertThat(response.getNextCasinoMoveHash(), is("resulting-hash"));
        verify(gameSession).updateNextBotMove(nextMoveHash, botMove);
    }

    @Test
    public void makeMove_userMoveWin_responseGoodAndStatisticsUpdated() {
        //arrange
        MakeMoveRequest request = stubMakeMoveRequest();
        when(gameRules.play(request.getUserMove(), gameSession.getBotMove())).thenReturn(-1);
        Move previousBotMove = gameSession.getBotMove();
        //act
        MakeMoveResponse response = gameService.makeMove(request);
        //assert
        assertThat(response.getResult(), is(GameResult.USER_WIN));
        GameHistory expectedHistory = GameHistory.of(request.getUserMove(), previousBotMove);
        verify(gameSession).addGameResult(expectedHistory,
                GameResult.USER_WIN);
    }

    private GameSession stubGameSession() {
        return Mockito.spy(GameSession.of(
                Move.PAPER,
                MoveHash.of("previous-secret", "previous-hash")
        ));
    }

    MakeMoveRequest stubMakeMoveRequest() {
        MakeMoveRequest makeMoveRequest = new MakeMoveRequest();
        makeMoveRequest.setGameSessionUuid(GAMESESSION_UUID);
        makeMoveRequest.setUserMove(Move.ROCK);
        return makeMoveRequest;
    }
}
