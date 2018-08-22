package ee.bilal.dev.dataprocessor.domain.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Created by bilal90 on 8/22/2018.
 */
@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
public abstract class BaseRepositoryTest<T> {

    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        entityManager.clear();
    }

    List<T> saveList(List<T> list){
        List<T> saved = new ArrayList<>();
        for (T t : list) {
            saved.add(entityManager.persist(t));
        }

        entityManager.flush();

        return saved;
    }

    T save(T t){
        if(t == null){
            throw new IllegalArgumentException("Entity cannot be null");
        }

        return entityManager.persistFlushFind(t);
    }

}
