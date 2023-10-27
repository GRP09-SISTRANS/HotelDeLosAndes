package uniandes.sistrans.HotelDeLosAndes.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name= "reserva_servicio")
public class ReservaServicio {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Date fecha;

    public ReservaServicio (
        Integer Id,
        ProductoEntity Producto,
        Usuario Usuario,
        Date Fecha
    ) {
        this.id = Id;
        this.producto = Producto;
        this.usuario = Usuario;
        this.fecha = Fecha;
    }
    
    public ReservaServicio() {;}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ProductoEntity getProducto() {
        return this.producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}


