package uniandes.sistrans.HotelDeLosAndes.repositorios;

import uniandes.sistrans.HotelDeLosAndes.modelo.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {
    
}