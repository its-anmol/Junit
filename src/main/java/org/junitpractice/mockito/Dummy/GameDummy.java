package org.junitpractice.mockito.Dummy;

public class GameDummy {
    private int gameId;
    private String gameType;

    public GameDummy(int gameId,String gameType){
        this.gameId=gameId;
        this.gameType=gameType;
    }

    public int getGameId(){
        return this.gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameType() {
        return gameType;
    }
}
