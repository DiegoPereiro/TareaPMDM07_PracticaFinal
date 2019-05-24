package freijo.castro.diego.tareapmdm07_practicafinal.inicio.recortatorios;

import java.security.PrivateKey;
import java.util.Date;
import java.util.Timer;

import freijo.castro.diego.tareapmdm07_practicafinal.Principal;

public class Recordatorio {
    private Date fecha, hora;
    private String notificacion, destino;
    private Boolean alarma;
    private int id;

    public Recordatorio(Date fecha, Date hora, String notificacion, String destino, Boolean alarma, int id) {
        this.fecha = fecha;
        this.hora = hora;
        this.notificacion = notificacion;
        this.destino = destino;
        this.alarma = alarma;
        this.id=id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Boolean getAlarma() {
        return alarma;
    }

    public void setAlarma(Boolean alarma) {
        this.alarma = alarma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
