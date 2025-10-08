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
        // Inicializar columnas de tabla
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        colTelefono.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNumero()));
        colDireccion.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDireccion()));
        colPuntaje.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPuntajeFidelidad()));
    }

    public void setVeterinaria(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
        this.controller = new DuenoController(veterinaria);

        // Inicializar tabla con los dueños existentes
        listaDuenos = controller.obtenerDuenos();
        tablaDuenos.setItems(listaDuenos);
    }

    @FXML
    private void agregarDueno() {
        if (validarCampos()) {
            if (controller.agregarDueno(txtId.getText(), txtNombre.getText(), txtTelefono.getText(), txtDireccion.getText(), txtPuntaje.getText())) {
                tablaDuenos.refresh();
                limpiarCampos();
                mostrarAlerta("Éxito", "Dueño agregado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "Ya existe un dueño con ese ID.", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void eliminarDueno() {
        Dueno dueno = tablaDuenos.getSelectionModel().getSelectedItem();
        if (dueno != null) {
            if (controller.eliminarDueno(dueno.getId())) {
                tablaDuenos.refresh();
                mostrarAlerta("Éxito", "Dueño eliminado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo eliminar el dueño.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Advertencia", "Seleccione un dueño para eliminar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void actualizarDueno() {
        if (validarCampos()) {
            if (controller.actualizarDueno(txtId.getText(), txtNombre.getText(), txtTelefono.getText(), txtDireccion.getText(), txtPuntaje.getText())) {
                tablaDuenos.refresh();
                limpiarCampos();
                mostrarAlerta("Éxito", "Dueño actualizado correctamente.", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No existe un dueño con ese ID.", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void seleccionarDueno() {
        Dueno duenoSeleccionado = tablaDuenos.getSelectionModel().getSelectedItem();
        if (duenoSeleccionado != null) {
            txtId.setText(duenoSeleccionado.getId());
            txtNombre.setText(duenoSeleccionado.getNombre());
            txtTelefono.setText(duenoSeleccionado.getNumero());
            txtDireccion.setText(duenoSeleccionado.getDireccion());
            txtPuntaje.setText(duenoSeleccionado.getPuntajeFidelidad());
        }
    }

    // 🔧 MÉTODO NUEVO AGREGADO
    @FXML
    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        txtPuntaje.clear();
    }

    private boolean validarCampos() {
        if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty()) {
            // 🔧 CORREGIDO: Agregar el parámetro faltante
            mostrarAlerta("Error", "Campos obligatorios", "ID y Nombre son campos obligatorios.", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    // 🔧 MÉTODO CORREGIDO: Agregar el parámetro faltante
    private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    // 🔧 MÉTODO SOBRECARGADO PARA MANTENER COMPATIBILIDAD
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}