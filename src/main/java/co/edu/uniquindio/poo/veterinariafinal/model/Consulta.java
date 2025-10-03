package co.edu.uniquindio.poo.veterinariafinal.model;

import java.time.LocalDate;
import java.util.List;

public class Consulta {

    private String id;
    private LocalDate fecha;
    private Mascota mascota;
    private TipoConsulta tipoConsulta;
    private double valorBase;

    public Consulta(String id,LocalDate fecha,TipoConsulta tipoConsulta,double valorBase,Mascota mascota){

        this.id = id;
        this.fecha = fecha;
        this.tipoConsulta = tipoConsulta;
        this.valorBase = valorBase;
        this.mascota = mascota;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(TipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id='" + id + '\'' +
                ", fecha=" + fecha +
                ", mascota=" + mascota +
                ", tipoConsulta=" + tipoConsulta +
                ", valorBase=" + valorBase +
                '}';
    }


    public double calcularCosto() {
        double costo = valorBase;

        // Recargo según especie
        if (mascota instanceof Ave || mascota instanceof Reptil) {
            costo += 20000; // recargo para aves y reptiles
        }

        // Recargo según edad
        if (mascota.getEdad() >= 8) { // considerando senior ≥ 8 años
            costo += 15000;
        }

        // Recargo según tipo de consulta
        if (tipoConsulta == TipoConsulta.URGENCIA) {
            costo += 25000;
        }

        return costo;
    }

    public int prioridadConsulta(Consulta consulta) {
        switch (consulta.getTipoConsulta()) {
            case URGENCIA: return 3;
            case VACUNACION: return 2;
            case CONSULTA_GENERAL: return 1;
            default: return 0;
        }
    }






}



