package uniandes.sistrans.HotelDeLosAndes.model;


public class ServicioForm {
    private String nombre;
    private String tipo;
    private Integer capacidad;
    private Float profundidad;
    private Integer maquinas;
    private String estilo;
    private String tipoServicio;
    private Long id;

    public ServicioForm() {;}

    public ServicioForm(String nombre, String tipo, Integer capacidad, Float profundidad, Integer maquinas, String tipoServicio, String estilo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.profundidad = profundidad;
        this.maquinas = maquinas;
        this.estilo = estilo;
        this.tipoServicio = tipoServicio;
    }

    public ServicioForm(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Float profundidad) {
        this.profundidad = profundidad;
    }

    public Integer getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(Integer maquinas) {
        this.maquinas = maquinas;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}