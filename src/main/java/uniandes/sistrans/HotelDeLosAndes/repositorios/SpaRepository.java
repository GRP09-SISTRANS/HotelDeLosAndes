package uniandes.sistrans.HotelDeLosAndes.repositorios;

import uniandes.sistrans.HotelDeLosAndes.modelo.SpaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpaRepository extends JpaRepository<SpaEntity, Long> {
    // métodos de consulta personalizados si los necesitas
}