package uniandes.sistrans.HotelDeLosAndes.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class Reserva_clientePK implements Serializable{
    @ManyToOne
    @JoinColumn(name="Usuario_id", referencedColumnName="id")
    private Usuario Usuario_id;

    @ManyToOne
    @JoinColumn(name="Reserva_id", referencedColumnName="id")
    private Reserva Reserva_id;

    public Reserva_clientePK(Usuario Usuario_id, Reserva Reserva_id) {
        super();
        this.Usuario_id = Usuario_id;
        this.Reserva_id = Reserva_id;
    }

    public Reserva_clientePK() {;}

    public Usuario getUsuario_id() {
        return Usuario_id;
    }

    public void setUsuario_id(Usuario usuario_id) {
        Usuario_id = usuario_id;
    }

    public Reserva getReserva_id() {
        return Reserva_id;
    }

    public void setReserva_id(Reserva reserva_id) {
        Reserva_id = reserva_id;
    }
}
