package com.mockito.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junitpractice.mockito.Dummy.GameDummy;
import org.junitpractice.mockito.Dummy.Player;
import org.junitpractice.mockito.Dummy.TableRepository;
import org.junitpractice.mockito.Dummy.TableService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPlayer {

    @InjectMocks
    private Player player;

    @Mock
    private GameDummy gameDummy;
    @Mock
    private TableRepository tableRepository;
    @Mock
    private TableService tableService;

    @Test
    void testGetPlayerInfo() {
        /* added @mock annotation to perform below operation
         GameDummy gameDummy=mock(GameDummy.class);
         TableRepository tableRepository=mock(TableRepository.class);
         TableService tableService=mock(TableService.class);

         */
        /*
        added @InjectMocks annotation to perform below operation
        Player player=new Player(gameDummy,tableService);
         */

         when(gameDummy.getGameType()).thenReturn("SNL");
         when(gameDummy.getGameId()).thenReturn(1234);
         when(tableService.getTableName()).thenReturn("VIP Table");
         String playerInfo = player.getPlayerInfo();
         assertEquals("Player is playing SNL game with ID 1234on tableVIP Table", playerInfo);
    }
}
