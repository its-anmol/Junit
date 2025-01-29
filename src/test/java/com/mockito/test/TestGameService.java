package com.mockito.test;

import org.junit.jupiter.api.Test;
import org.junitpractice.mockito.Dummy.GameDummy;
import org.junitpractice.mockito.Dummy.GameDummyRepository;
import org.junitpractice.mockito.Dummy.GameDummyService;
import org.junitpractice.mockito.Dummy.TableService;
import org.junitpractice.mockito.Fake.DataBaseReadException;
import org.junitpractice.mockito.Fake.Game;
import org.junitpractice.mockito.Fake.GameRepository;
import org.junitpractice.mockito.Fake.GameService;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TestGameService {

    @Test
    public void testFakeWithMockito(){
        GameRepository gameRepository=mock(GameRepository.class);
        GameService gameService=new GameService(gameRepository);
        Game game1=new Game(16,"SNL");
        Game game2=new Game(17,"Roullete");

        Collection<Game> games =new ArrayList<>();
        games.add(game1);
        games.add(game2);
        when(gameRepository.findAll()).thenReturn(games);
        assertEquals(2,gameService.findTotalNumberOfGame());
    }

    @Test
    public void testDummyWithMockito(){
        GameDummyRepository gameDummyRepository=mock(GameDummyRepository.class);
        TableService tableService=mock(TableService.class);

        GameDummyService gameDummyService=new GameDummyService(gameDummyRepository,tableService);

        GameDummy game1=new GameDummy(101,"Black Jack");
        Collection<GameDummy> games =new ArrayList<>();
        games.add(game1);
        when(gameDummyRepository.findAll()).thenReturn(games);
        assertEquals(1,gameDummyService.findAllGame());
    }

    @Test
    public void testMockWithMockito(){
        GameRepository gameRepository=mock(GameRepository.class);
        GameService gameService=new GameService(gameRepository);
        Game game1=new Game(16,"SNL");
        Game game2=new Game(17,"Roullete");

        Collection<Game> games =new ArrayList<>();
        gameService.addGame(game1);
        gameService.addGame(game2);

        Mockito.verify(gameRepository,times(1)).save(game1);
        Mockito.verify(gameRepository,times(1)).save(game2);
    }

    @Test
    public void testSpyWithMockito(){
        GameRepository gameRepository=spy(GameRepository.class);
        GameService gameService=new GameService(gameRepository);
        Game game1=new Game(16,"SNL");
        Game game2=new Game(17,"Roullete");

        Collection<Game> games =new ArrayList<>();
        gameService.addGame(game1);
        gameService.addGame(game2);

        Mockito.verify(gameRepository,times(1)).save(game1);
        Mockito.verify(gameRepository,times(1)).save(game2);
    }

    @Test
    public void testStaticMethodWithMockito(){
        try(MockedStatic<GameService> gameService=mockStatic(GameService.class)){
            gameService.when(()->GameService.checkStatic()).thenReturn("hello mocking static method");
            assertEquals("hello mocking static method",GameService.checkStatic());
        }
    }

    @Test
    public void testSavetoDataBaseWithMockito() throws SQLException {
        GameService gameService= mock(GameService.class);
        when(gameService.findAllGamesFromDataBase()).thenThrow(DataBaseReadException.class);
        assertThrows(DataBaseReadException.class,()->gameService.findAllGamesFromDataBase());
    }

    @Test
    public void testCheckConnectionToDataBase() throws SQLException {
        GameRepository gameRepository=spy(GameRepository.class);
        doThrow(SQLException.class).when(gameRepository).checkConnectionToDataBase();
        assertThrows(SQLException.class,()->gameRepository.checkConnectionToDataBase());
    }
}
