package uniandes.sistrans.HotelDeLosAndes.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="usuario")
public class Usuario implements UserDetails {
    @Id
    private Integer id;

    @Column(length=2)
    private String tipo_doc;

    private String correo;
    private String direccion;
    private String nombre;
    private String apellido;
    private String contrasenia;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "user_role_junction",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;

    private char tipo_usuario;

    @OneToMany(mappedBy = "usuario")
    private List<ReservaServicioEntity> reservas;

    public Usuario(
        Integer Id, 
        String Tipo_doc,
        String Correo,
        String Direccion,
        String Nombre,
        String Apellido,
        String Contrasenia,
        Set<Role> authorities,
        char Tipo_usuario
    ) {
        this.id = Id;
        this.tipo_doc = Tipo_doc;
        this.correo = Correo;
        this.direccion = Direccion;
        this.nombre = Nombre;
        this.apellido = Apellido;
        this.contrasenia = Contrasenia;
        this.authorities = authorities;
        this.tipo_usuario = Tipo_usuario;
    }

    public Usuario() {
        super();
        this.authorities = new HashSet<Role>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo_doc() {
        return this.tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String getUsername() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String getPassword() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public char getTipo_usuario() {
        return this.tipo_usuario;
    }

    public void setTipo_usuario(char tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(HashSet<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
