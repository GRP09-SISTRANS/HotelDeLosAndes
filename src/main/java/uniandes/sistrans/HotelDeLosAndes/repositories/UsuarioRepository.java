package uniandes.sistrans.HotelDeLosAndes.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import uniandes.sistrans.HotelDeLosAndes.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    @Query(value = "SELECT * FROM Usuario WHERE id = :id", nativeQuery = true)
    Usuario darUsuario(Integer id);

    @Query(value = "SELECT * FROM Usuario", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM Usuario WHERE nombre = :nombre", nativeQuery = true)
    Collection<Usuario> darUsuariosPorNombre(String nombre);

    @Query(value = "SELECT * FROM Usuario WHERE correo = :correo", nativeQuery = true)
    Usuario darUsuarioPorCorreo(String correo);

    @Query(value = "SELECT * FROM Usuario WHERE apellido = :apellido", nativeQuery = true)
    Collection<Usuario> darUsuariosPorApellido(String apellido);

    @Query(value = "SELECT * FROM Usuario WHERE tipo_usuario = :tipo_usuario", nativeQuery = true)
    Collection<Usuario> darUsuariosPorTipo_usuario(char tipo_usuario);

    @Query(value = "SELECT * FROM Usuario WHERE tipo_doc = :tipo_doc", nativeQuery = true)
    Collection<Usuario> darUsuariosPorTipo_doc(String tipo_doc);

    @Query(value = "SELECT * FROM Usuario WHERE direccion = :direccion", nativeQuery = true)
    Collection<Usuario> darUsuariosPorDireccion(String direccion);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Usuario (id, tipo_doc, correo, direccion, nombre, apellido, contrasenia, tipo_usuario) VALUES (:id, :tipo_doc, :correo, :direccion, :nombre, :apellido, :contrasenia, :tipo_usuario)", nativeQuery = true)
    void insertarUsuario(Integer id, String tipo_doc, String correo, String direccion, String nombre, String apellido, String contrasenia, char tipo_usuario);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuario SET tipo_doc=:tipo_doc, correo=:correo, direccion=:direccion, nombre=:nombre, apellido=:apellido, contrasenia=:contrasenia, tipo_usuario=:tipo_usuario WHERE id = :id", nativeQuery = true)
    void actualizarUsuario(Integer id, String tipo_doc, String correo, String direccion, String nombre, String apellido, String contrasenia, char tipo_usuario);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Usuario WHERE id = :id", nativeQuery = true)
    void eliminarUsuario(Integer id);
}
