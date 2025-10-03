package co.edu.uniquindio.poo.veterinariafinal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Veterinaria {

    private String nombre;
    private String nit;
    private List<Mascota> listMascotas;
    private List<Consulta> listConsultas;
    private List<Dueno> listDuenos;

    public Veterinaria(String nombre,String nit){

        this.nombre = nombre;
        this.nit = nit;
        this.listMascotas = new ArrayList<>();
        this.listConsultas = new ArrayList<>();
        this.listDuenos = new ArrayList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public List<Consulta> getListConsultas() {
        return listConsultas;
    }

    public void setListConsultas(List<Consulta> listConsultas) {
        this.listConsultas = listConsultas;
    }

    public List<Mascota> getListMascotas() {
        return listMascotas;
    }

    public void setListMascotas(List<Mascota> listMascotas) {
        this.listMascotas = listMascotas;
    }

    public List<Dueno> getListDuenos() {
        return listDuenos;
    }

    public void setListDuenos(List<Dueno> listDuenos) {
        this.listDuenos = listDuenos;
    }

    @Override
    public String toString() {
        return "Veterinaria{" +
                "nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", listMascotas=" + listMascotas +
                ", listConsultas=" + listConsultas +
                ", listDuenos=" + listDuenos +
                '}';
    }


    public List<Dueno> rankingDuenosFrecuentes() {
        Map<Dueno, Long> contador = listConsultas.stream()
                .collect(Collectors.groupingBy(c -> c.getMascota().getDueno(), Collectors.counting()));

        // Ordenar descendente según número de visitas
        List<Dueno> ranking = contador.entrySet().stream()
                .sorted(Map.Entry.<Dueno, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return ranking;
    }
}
