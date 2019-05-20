package freijo.castro.diego.tareapmdm07_practicafinal.partidas;

public class Partida {
    private String referencia, concepto;
    private float cantidad, precio;

    public Partida(String referencia, String concepto, float cantidad, float precio) {
        this.referencia = referencia;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
