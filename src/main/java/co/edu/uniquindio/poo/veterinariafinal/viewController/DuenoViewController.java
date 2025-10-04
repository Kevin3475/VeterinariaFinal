package co.edu.uniquindio.poo.veterinariafinal.viewController;

import co.edu.uniquindio.poo.veterinariafinal.controller.DuenoController;
import co.edu.uniquindio.poo.veterinariafinal.model.Dueno;
import co.edu.uniquindio.poo.veterinariafinal.model.Veterinaria;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DuenoViewController implements Initializable {

    @FXML
    private TextField txtId, txtNombre, txtTelefono, txtDireccion, txtPuntaje;

    @FXML
    private TableView<Dueno> tablaDuenos;

    @FXML
    private TableColumn<Dueno, String> colId, colNombre, colTelefono, colDireccion, colPuntaje;

    private DuenoController controller;
    private ObservableList<Dueno> listaDuenos;
    private Veterinaria veterinaria;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Crear veterinaria y controller
        veterinaria = new Veterinaria("Patitas", "1203");
        controller = new DuenoController(veterinaria);

        // Inicializar columnas de tabla
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        colTelefono.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNumero()));
        colDireccion.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDireccion()));
        colPuntaje.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPuntajeFidelidad()));

        // Usar la lista observable directamente de la veterinaria
        listaDuenos = controller.obtenerDuenos(); // devuelve ObservableList
        tablaDuenos.setItems(listaDuenos);
    }

    public void setVeterinaria(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
        this.controller = new DuenoController(veterinaria);

        // Inicializar tabla con los dueños existentes
        tablaDuenos.setItems(controller.obtenerDuenos());
    }


    @FXML
    private void agregarDueno() {
        if (controller.agregarDueno(txtNombre.getText(), txtTelefono.getText(), txtDireccion.getText(), txtPuntaje.getText(), txtId.getText())) {
            tablaDuenos.refresh();
            limpiarCampos();
            mostrarAlerta("Éxito", "Dueño agregado correctamente.");
        } else {
            mostrarAlerta("Error", "Ya existe un dueño con ese ID.");
        }
    }

    @FXML
    private void eliminarDueno() {
        Dueno dueno = tablaDuenos.getSelectionModel().getSelectedItem();
        if (dueno != null && controller.eliminarDueno(dueno.getId())) {
            tablaDuenos.refresh();
            mostrarAlerta("Éxito", "Dueño eliminado correctamente.");
        } else {
            mostrarAlerta("Error", "No se pudo eliminar el dueño.");
        }
    }

    @FXML
    private void actualizarDueno() {
        if (controller.actualizarDueno(txtNombre.getText(), txtTelefono.getText(), txtDireccion.getText(), txtPuntaje.getText(), txtId.getText())) {
            tablaDuenos.refresh();
            limpiarCampos();
            mostrarAlerta("Éxito", "Dueño actualizado correctamente.");
        } else {
            mostrarAlerta("Error", "No existe un dueño con ese ID.");
        }
    }

    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        txtPuntaje.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
