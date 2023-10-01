package uniandes.sistrans.HotelDeLosAndes.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    private Integer id;

    @Column(length=2)
    private String tipo_doc;

    private String correo;

    private String direccion;

    private String nombre;

    private String apellido;

    private String contrasenia;

    private char tipo_usuario;

    public Usuario(Integer Id, String Tipo_doc, String Correo, String Direccion, String Nombre, String Apellido, String Contrasenia, char Tipo_usuario) {

        this.id = Id;
        this.tipo_doc = Tipo_doc;
        this.correo = Correo;
        this.direccion = Direccion;
        this.nombre = Nombre;
        this.apellido = Apellido;
        this.contrasenia = Contrasenia;
        this.tipo_usuario = Tipo_usuario;
    }

    public Usuario()
    {;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public char getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(char tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    
}
