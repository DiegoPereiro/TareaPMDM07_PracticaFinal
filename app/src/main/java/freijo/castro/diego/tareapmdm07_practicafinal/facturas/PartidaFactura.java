package freijo.castro.diego.tareapmdm07_practicafinal.facturas;

public class PartidaFactura {
    private  int id, idfactura;
    private String referencia, concepto;
    private float cantidad, precio, total;

    public PartidaFactura(int id, int idfactura, String referencia, String concepto, float cantidad, float precio, float total) {
        this.id = id;
        this.idfactura = idfactura;
        this.referencia = referencia;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
