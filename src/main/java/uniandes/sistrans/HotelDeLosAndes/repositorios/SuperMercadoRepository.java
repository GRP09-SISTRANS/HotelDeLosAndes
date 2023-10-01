package uniandes.sistrans.HotelDeLosAndes.repositorios;

import uniandes.sistrans.HotelDeLosAndes.modelo.SuperMercadoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SuperMercadoRepository extends JpaRepository<SuperMercadoEntity, Long> {
    // métodos de consulta personalizados si los necesitas
}