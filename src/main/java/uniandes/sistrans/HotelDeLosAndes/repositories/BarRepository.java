package uniandes.sistrans.HotelDeLosAndes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.models.BarEntity;


@Repository
public interface BarRepository extends JpaRepository<BarEntity, Long> {

}
