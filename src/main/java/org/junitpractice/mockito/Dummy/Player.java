package org.junitpractice.mockito.Dummy;

public class Player {
    private GameDummy gameDummy;
    private TableService tableService;
    public Player(GameDummy gameDummy,TableService tableService) {
        this.gameDummy = gameDummy;
        this.tableService=tableService;
    }

    public String getPlayerInfo() {
        return "Player is playing " + gameDummy.getGameType() + " game with ID " + gameDummy.getGameId()+"on table"+tableService.getTableName();

    }

    public void setGame(GameDummy gameDummy) {
        this.gameDummy = gameDummy;
    }
}
