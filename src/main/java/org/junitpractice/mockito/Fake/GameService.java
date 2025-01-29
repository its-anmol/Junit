package org.junitpractice.mockito.Fake;

import java.sql.SQLException;
import java.util.Collection;

public class GameService {
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository){
        this.gameRepository=gameRepository;
    }

    public void addGame(Game game){
        gameRepository.save(game);
    }

    public int findTotalNumberOfGame(){
        return gameRepository.findAll().size();
    }

    public static String checkStatic(){
        return "Inside Static Method";
    }

    public int findAllGamesFromDataBase() throws SQLException {
        Collection<Game> games=null;
        try{
            games=gameRepository.findAllGamesFromDataBase();
        }
        catch (SQLException e){
            throw new DataBaseReadException("Unable to read from DataBase"+e.getMessage());
        }
        return games.size();

    }
}
