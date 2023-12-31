package uniandes.sistrans.HotelDeLosAndes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.models.SuperMercadoEntity;


@Repository
public interface SuperMercadoRepository extends JpaRepository<SuperMercadoEntity, Long> {

}