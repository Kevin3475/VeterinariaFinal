package co.edu.uniquindio.poo.veterinariafinal.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Dueno {

    private String nombre;
    private String id;
    private String numero;
    private String direccion;
    private String puntajeFidelidad;
    private ObservableList<Mascota> listMascotas;

    public Dueno(String id, String nombre, String numero, String direccion, String puntajeFidelidad) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.direccion = direccion;
        this.puntajeFidelidad = puntajeFidelidad;
        this.listMascotas = FXCollections.observableArrayList();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getPuntajeFidelidad() { return puntajeFidelidad; }
    public void setPuntajeFidelidad(String puntajeFidelidad) { this.puntajeFidelidad = puntajeFidelidad; }
    public ObservableList<Mascota> getListMascotas() { return listMascotas; }
    public void setListMascotas(ObservableList<Mascota> listMascotas) { this.listMascotas = listMascotas; }

    // Método para agregar mascota
    public void agregarMascota(Mascota mascota) {
        if (!listMascotas.contains(mascota)) {
            listMascotas.add(mascota);
        }
    }

    // Método para eliminar mascota
    public void eliminarMascota(Mascota mascota) {
        listMascotas.remove(mascota);
    }

    @Override
    public String toString() {
        return nombre + " (" + id + ")";
    }
}