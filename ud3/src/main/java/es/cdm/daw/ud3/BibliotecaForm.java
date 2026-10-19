package es.cdm.daw.ud3;

public class BibliotecaForm {

    private String titulo = "";
    private boolean disponibles = false;

    public BibliotecaForm() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isDisponibles() {
        return disponibles;
    }

    public void setDisponibles(boolean disponibles) {
        this.disponibles = disponibles;
    }
}