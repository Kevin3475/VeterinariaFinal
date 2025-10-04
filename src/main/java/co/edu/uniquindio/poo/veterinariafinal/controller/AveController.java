package co.edu.uniquindio.poo.veterinariafinal.controller;

import co.edu.uniquindio.poo.veterinariafinal.model.Ave;
import co.edu.uniquindio.poo.veterinariafinal.model.Dueno;
import co.edu.uniquindio.poo.veterinariafinal.model.Vuela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AveController {

    private final ObservableList<Ave> listaAves = FXCollections.observableArrayList();

    public ObservableList<Ave> getListaAves() {
        return listaAves;
    }

    public void agregarAve(String nombre, String raza, int edad, double pesoKg, String id, Dueno dueno,
                           String tipoPlumaje, int sonidosImitados, Vuela vuela) {

        Ave ave = new Ave(nombre, raza, edad, pesoKg, id, dueno, tipoPlumaje, sonidosImitados, vuela);
        listaAves.add(ave);
    }

    public void eliminarAve(Ave ave) {
        listaAves.remove(ave);
    }
}
