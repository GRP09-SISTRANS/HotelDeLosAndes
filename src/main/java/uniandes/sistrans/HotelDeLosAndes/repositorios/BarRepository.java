package uniandes.sistrans.HotelDeLosAndes.repositorios;
import uniandes.sistrans.HotelDeLosAndes.modelo.BarEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BarRepository extends JpaRepository<BarEntity, Long> {
    // métodos de consulta personalizados si los necesitas
}
