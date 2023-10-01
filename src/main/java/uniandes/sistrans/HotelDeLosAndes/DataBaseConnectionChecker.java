package uniandes.sistrans.HotelDeLosAndes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class DataBaseConnectionChecker {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void checkConnection() {
        jdbcTemplate.execute("SELECT 1 FROM DUAL");
    }
    
}



    
