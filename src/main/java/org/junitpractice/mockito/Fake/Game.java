package org.junitpractice.mockito.Fake;

public class Game {
    private int gameId;
    private String gameType;

    public Game(int gameId,String gameType){
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