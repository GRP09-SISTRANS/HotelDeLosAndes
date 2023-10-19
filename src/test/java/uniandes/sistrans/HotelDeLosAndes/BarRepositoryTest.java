package uniandes.sistrans.HotelDeLosAndes;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import uniandes.sistrans.HotelDeLosAndes.model.BarEntity;
import uniandes.sistrans.HotelDeLosAndes.repositories.BarRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.CuentaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BarRepository barRepository;

}