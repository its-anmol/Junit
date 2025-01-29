package org.junitpractice.mockito.Fake;

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
}
