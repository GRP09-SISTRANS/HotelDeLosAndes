package uniandes.sistrans.HotelDeLosAndes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.models.ProductoEntity;


@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

}
