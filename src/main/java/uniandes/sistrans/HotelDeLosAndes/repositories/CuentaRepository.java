package uniandes.sistrans.HotelDeLosAndes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.models.CuentaEntity;


@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {
    
}