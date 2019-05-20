package freijo.castro.diego.tareapmdm07_practicafinal.clientes;

public class Cliente {
    private int id;
    private String cif, nombre, via, direccion, numero, escalera, piso, puerta, codigopostal, poblacion, provincia, pais, telefono, telefono2, fax, email, iban;

    public Cliente(int id, String cif, String nombre, String via, String direccion, String numero, String escalera, String piso, String puerta, String codigopostal, String poblacion, String provincia, String pais, String telefono, String telefono2, String fax, String email, String iban) {
        this.id = id;
        this.cif = cif;
        this.nombre = nombre;
        this.via = via;
        this.direccion = direccion;
        this.numero = numero;
        this.escalera = escalera;
        this.piso = piso;
        this.puerta = puerta;
        this.codigopostal = codigopostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.pais = pais;
        this.telefono = telefono;
        this.telefono2 = telefono2;
        this.fax = fax;
        this.email = email;
        this.iban = iban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEscalera() {
        return escalera;
    }

    public void setEscalera(String escalera) {
        this.escalera = escalera;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
