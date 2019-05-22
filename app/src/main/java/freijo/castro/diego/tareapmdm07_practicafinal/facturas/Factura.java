package freijo.castro.diego.tareapmdm07_practicafinal.facturas;

public class Factura {
    private int id, numero, idcliente;
    private String fecha, cifCliente, nombreCliente;
    private float base, iva, total;

    public Factura(int id, int numero, int idcliente, String fecha, String cifCliente, String nombreCliente, float base, float iva, float total) {
        this.id = id;
        this.numero = numero;
        this.idcliente = idcliente;
        this.fecha = fecha;
        this.cifCliente = cifCliente;
        this.nombreCliente = nombreCliente;
        this.base = base;
        this.iva = iva;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public float getBase() {
        return base;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
