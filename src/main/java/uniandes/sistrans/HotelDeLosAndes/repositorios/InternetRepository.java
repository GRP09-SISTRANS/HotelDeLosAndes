package uniandes.sistrans.HotelDeLosAndes.repositorios;
import uniandes.sistrans.HotelDeLosAndes.modelo.InternetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InternetRepository extends JpaRepository<InternetEntity, Long> {
    // métodos de consulta personalizados si los necesitas
}
