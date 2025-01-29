package com.mockito.test;

import org.junit.jupiter.api.Test;
import org.junitpractice.mockito.Dummy.GameDummy;
import org.junitpractice.mockito.Dummy.Player;
import org.junitpractice.mockito.Dummy.TableRepository;
import org.junitpractice.mockito.Dummy.TableService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestPlayer {




    @Test
    void testGetPlayerInfo() {
         GameDummy gameDummy=mock(GameDummy.class);
         TableRepository tableRepository=mock(TableRepository.class);
//         TableService tableService=new TableService(tableRepository);
        TableService tableService=mock(TableService.class);

         Player player=new Player(gameDummy,tableService);

         when(gameDummy.getGameType()).thenReturn("SNL");
         when(gameDummy.getGameId()).thenReturn(1234);
         when(tableService.getTableName()).thenReturn("VIP Table");
         String playerInfo = player.getPlayerInfo();
         assertEquals("Player is playing SNL game with ID 1234on tableVIP Table", playerInfo);
    }
}
