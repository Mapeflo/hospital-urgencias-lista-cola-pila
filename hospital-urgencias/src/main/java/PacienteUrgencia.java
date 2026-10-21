public class PacienteUrgencia {
    private String codigoTriage;   // Identificador principal
    private String nombre;
    private String nivelUrgencia;  // Rojo, Naranja, Amarillo, Verde
    private String sintomas;
    private String estado;         // Pendiente, Atendido, Cancelado

    // Constructor
    public PacienteUrgencia(String codigoTriage, String nombre, String nivelUrgencia, String sintomas) {
        this.codigoTriage = codigoTriage;
        this.nombre = nombre;
        this.nivelUrgencia = nivelUrgencia;
        this.sintomas = sintomas;
        this.estado = "Pendiente";
    }

    // Getters
    public String getCodigoTriage() {
        return codigoTriage;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    public String getSintomas() {
        return sintomas;
    }

    public String getEstado() {
        return estado;
    }

    // Setters
    public void setCodigoTriage(String codigoTriage) {
        this.codigoTriage = codigoTriage;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNivelUrgencia(String nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // toString
    @Override
    public String toString() {
        return "PacienteUrgencia{" +
                "codigoTriage='" + codigoTriage + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nivelUrgencia='" + nivelUrgencia + '\'' +
                ", sintomas='" + sintomas + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    // equals (compara por el identificador principal: codigoTriage)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PacienteUrgencia that = (PacienteUrgencia) obj;
        return codigoTriage != null ? codigoTriage.equals(that.codigoTriage) : that.codigoTriage == null;
    }
}