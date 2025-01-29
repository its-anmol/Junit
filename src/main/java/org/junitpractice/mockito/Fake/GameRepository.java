package org.junitpractice.mockito.Fake;

import java.sql.SQLException;
import java.util.Collection;

public interface GameRepository {
    void save(Game game);
    Collection<Game> findAll();
    Collection<Game> findAllGamesFromDataBase() throws SQLException;
    void checkConnectionToDataBase() throws SQLException;
}
