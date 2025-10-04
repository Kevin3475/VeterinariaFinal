package co.edu.uniquindio.poo.veterinariafinal.controller;

import co.edu.uniquindio.poo.veterinariafinal.model.Dueno;
import co.edu.uniquindio.poo.veterinariafinal.model.Veterinaria;
import javafx.collections.ObservableList;

import java.util.List;

public class DuenoController {

    private Veterinaria veterinaria;

    public DuenoController(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
    }


    public boolean eliminarDueno(String id) {
        return veterinaria.eliminarDueno(id);
    }

    public boolean actualizarDueno(String id,String nombre,String numero,String direccion,String puntajeFidelidad) {
        Dueno dueno = new Dueno(id, nombre, numero, direccion, puntajeFidelidad);
        return veterinaria.actualizarDueno(dueno);
    }

    public ObservableList<Dueno> obtenerDuenos() {
        return veterinaria.getListDuenos();
    }

    public boolean agregarDueno(String id,String nombre,String numero,String direccion,String puntajeFidelidad) {
        Dueno dueno = new Dueno(id, nombre, numero, direccion, puntajeFidelidad);
        boolean agregado = veterinaria.agregarDueno(dueno); // debe agregar a listDuenos
        return agregado;
    }
}

