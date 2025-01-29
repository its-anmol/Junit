package com.mockito.test;

import org.junit.jupiter.api.Test;
import org.junitpractice.mockito.Fake.Game;
import org.junitpractice.mockito.Fake.GameRepository;
import org.junitpractice.mockito.Fake.GameService;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FakeTestGameService {

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
}
