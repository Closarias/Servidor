package es.cdmfp.dwes.ud1;

public class BicicletaForm {

    private String tipo;
    private int unidades = 1;
    private double precio;
    private boolean montaje;

    public BicicletaForm() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isMontaje() {
        return montaje;
    }

    public void setMontaje(boolean montaje) {
        this.montaje = montaje;
    }
}