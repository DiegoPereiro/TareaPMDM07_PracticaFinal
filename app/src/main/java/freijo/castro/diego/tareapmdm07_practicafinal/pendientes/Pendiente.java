package freijo.castro.diego.tareapmdm07_practicafinal.pendientes;

public class Pendiente {
    private int id, idcliente;
    private String fecha, cifCliente, NombreCliente, referencia, concepto;
    private float cantidad, precio;


    public Pendiente(int id, int idcliente, String fecha, String cifCliente, String nombreCliente, String referencia, String concepto, float cantidad, float precio) {
        this.id = id;
        this.idcliente = idcliente;
        this.fecha = fecha;
        this.cifCliente = cifCliente;
        NombreCliente = nombreCliente;
        this.referencia = referencia;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCifCliente() {
        return cifCliente;
    }

    public void setCifCliente(String cifCliente) {
        this.cifCliente = cifCliente;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
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
