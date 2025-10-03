package co.edu.uniquindio.poo.veterinariafinal.model;

import java.util.ArrayList;
import java.util.List;

public class Dueno {

    private String nombre;
    private String numero;
    private String direccion;
    private String puntajeFidelidad;
    private List<Mascota> listMascotas;

    public Dueno(String nombre,String numero,String direccion,String puntajeFidelidad){

        this.nombre = nombre;
        this.numero = numero;
        this.direccion = direccion;
        this.puntajeFidelidad = puntajeFidelidad;
        this.listMascotas = new ArrayList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPuntajeFidelidad() {
        return puntajeFidelidad;
    }

    public void setPuntajeFidelidad(String puntajeFidelidad) {
        this.puntajeFidelidad = puntajeFidelidad;
    }

    public List<Mascota> getListMascotas() {
        return listMascotas;
    }

    public void setListMascotas(List<Mascota> listMascotas) {
        this.listMascotas = listMascotas;
    }

    @Override
    public String toString() {
        return "Dueno{" +
                "puntajeFidelidad='" + puntajeFidelidad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", numero='" + numero + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
