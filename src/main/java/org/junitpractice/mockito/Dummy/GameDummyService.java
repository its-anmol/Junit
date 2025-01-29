package org.junitpractice.mockito.Dummy;


public class GameDummyService {
    private GameDummyRepository gameRepository;
    private TableService tableservice;
    public GameDummyService(GameDummyRepository gameRepository,TableService tableService){
        this.gameRepository=gameRepository;
        this.tableservice=tableService;
    }

    public void addGame(GameDummy gameDummy){
        gameRepository.save(gameDummy);
    }

    public int findAllGame(){return  gameRepository.findAll().size();}
}
