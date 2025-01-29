package org.junitpractice.mockito.Fake;

import java.util.Collection;

public interface GameRepository {
    void save(Game game);
    Collection<Game> findAll();
}
