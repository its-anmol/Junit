package com.mockito.test;

import org.junit.jupiter.api.Test;
import org.junitpractice.mockito.Dummy.GameDummy;
import org.junitpractice.mockito.Dummy.GameDummyRepository;
import org.junitpractice.mockito.Dummy.GameDummyService;
import org.junitpractice.mockito.Dummy.TableService;
import org.junitpractice.mockito.Fake.Game;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestGameDummyService {
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


}
