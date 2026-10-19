package es.cdm.daw.ud3;

public class SocioForm {
    private String nombre = "";
    private String email = "";
    private int edad;
    private String modalidad = "general";
    private boolean novedades;

    public SocioForm() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public boolean isNovedades() {
        return novedades;
    }

    public void setNovedades(boolean novedades) {
        this.novedades = novedades;
    }
}
