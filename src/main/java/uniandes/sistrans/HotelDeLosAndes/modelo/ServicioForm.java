package uniandes.sistrans.HotelDeLosAndes.modelo;

public class ServicioForm {
    private String nombre;
    private char tipo;
    private Integer capacidad;
    private Float profundidad;
    private Integer maquinas;
    private String estilo;
    private String tipoServicio;
    
    
    
    
    public ServicioForm() {;
    }
    public ServicioForm(String nombre, char tipo, Integer capacidad, Float profundidad, Integer maquinas, String tipoServicio,
            String estilo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.profundidad = profundidad;
        this.maquinas = maquinas;
        this.estilo = estilo;
        this.tipoServicio = tipoServicio;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public char getTipo() {
        return tipo;
    }
    public void setTipo(char tipo) {
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
    
}