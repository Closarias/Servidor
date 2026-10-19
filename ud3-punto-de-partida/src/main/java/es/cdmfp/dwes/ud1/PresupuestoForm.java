package es.cdmfp.dwes.ud1;

public class PresupuestoForm {

    private String producto;
    private int unidades;
    private double precio;
    private boolean montaje;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
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