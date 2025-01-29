package org.junitpractice.mockito.Dummy;
import java.util.Collection;
public interface GameDummyRepository {
    void save(GameDummy gameDummy);
    Collection<GameDummy> findAll();
}
