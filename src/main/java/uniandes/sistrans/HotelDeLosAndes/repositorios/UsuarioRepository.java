package uniandes.sistrans.HotelDeLosAndes.repositorios;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uniandes.sistrans.HotelDeLosAndes.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    @Query(value = "SELECT * FROM Usuario WHERE id = :id", nativeQuery = true)
    Usuario darUsuario(Integer id);

    @Query(value = "SELECT * FROM Usuario", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM Usuario WHERE nombre = :nombre", nativeQuery = true)
    Collection<Usuario> darUsuariosPorNombre(String nombre);


    
}
