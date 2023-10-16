package uniandes.sistrans.HotelDeLosAndes.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import uniandes.sistrans.HotelDeLosAndes.model.Plan_Consumo;


public interface Plan_ConsumoRepository extends JpaRepository<Plan_Consumo, Integer> {
    @Query(value = "SELECT * FROM Plan_Consumo WHERE id = :id", nativeQuery = true)
    Plan_Consumo darPlan_ConsumoPorId(Integer id);

    @Query(value = "SELECT * FROM Plan_Consumo WHERE nombre = :nombre", nativeQuery = true)
    Plan_Consumo darPlan_ConsumoPorNombre(String nombre);

    @Query(value = "SELECT * FROM Plan_Consumo ", nativeQuery = true)
    Collection<Plan_Consumo> darPlan_Consumo();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Plan_Consumo (id, nombre, descripcion) VALUES (:id, :nombre, :costo, :descripcion)", nativeQuery = true)
    void insertarPlan_Consumo(Integer id, String nombre
    , String descripcion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Plan_Consumo SET nombre=:nombre, descripcion=:descripcion WHERE id = :id", nativeQuery = true)
    void actualizarPlan_Consumo(Integer id, String nombre, String descripcion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Plan_Consumo WHERE id = :id", nativeQuery = true)
    void eliminarPlan_Consumo(Integer id);    
}
