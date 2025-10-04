package co.edu.uniquindio.poo.veterinariafinal.viewController;

import co.edu.uniquindio.poo.veterinariafinal.controller.AveController;
import co.edu.uniquindio.poo.veterinariafinal.model.Ave;
import co.edu.uniquindio.poo.veterinariafinal.model.Dueno;
import co.edu.uniquindio.poo.veterinariafinal.model.Vuela;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AveViewController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtRaza;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtTipoPlumaje;
    @FXML
    private TextField txtSonidosImitados;
    @FXML
    private ComboBox<Vuela> comboVuela;
    @FXML
    private TextField txtDuenoNombre;

    @FXML
    private TableView<Ave> tablaAves;
    @FXML
    private TableColumn<Ave, String> colNombre;
    @FXML
    private TableColumn<Ave, String> colRaza;
    @FXML
    private TableColumn<Ave, Integer> colEdad;
    @FXML
    private TableColumn<Ave, Double> colPeso;
    @FXML
    private TableColumn<Ave, String> colId;
    @FXML
    private TableColumn<Ave, String> colTipoPlumaje;
    @FXML
    private TableColumn<Ave, Integer> colSonidosImitados;
    @FXML
    private TableColumn<Ave, String> colVuela;

    private final AveController aveController = new AveController();

    @FXML
    public void initialize() {
        comboVuela.setItems(FXCollections.observableArrayList(Vuela.values()));

        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colRaza.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRaza()));
        colEdad.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getEdad()).asObject());
        colPeso.setCellValueFactory(c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().getPesoKg()).asObject());
        colId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        colTipoPlumaje.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipoPlumaje()));
        colSonidosImitados.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getSonidosImitados()).asObject());
        colVuela.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getVuela().toString()));

        tablaAves.setItems(aveController.getListaAves());
    }

    @FXML
    private void agregarAve() {
        try {
            String nombre = txtNombre.getText();
            String raza = txtRaza.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            String id = txtId.getText();
            String tipoPlumaje = txtTipoPlumaje.getText();
            int sonidosImitados = Integer.parseInt(txtSonidosImitados.getText());
            Vuela vuela = comboVuela.getValue();

            // Crea un dueño simple con el nombre ingresado (puedes cambiarlo si tienes un sistema de dueños)
            Dueno dueno = new Dueno(txtDuenoNombre.getText(), "", "", "","");

            aveController.agregarAve(nombre, raza, edad, peso, id, dueno, tipoPlumaje, sonidosImitados, vuela);
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Campos numéricos inválidos", "Verifica edad, peso o sonidos imitados.");
        }
    }

    @FXML
    private void eliminarAve() {
        Ave aveSeleccionada = tablaAves.getSelectionModel().getSelectedItem();
        if (aveSeleccionada != null) {
            aveController.eliminarAve(aveSeleccionada);
        } else {
            mostrarAlerta("Advertencia", "Ninguna ave seleccionada", "Selecciona un ave para eliminar.");
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtRaza.clear();
        txtEdad.clear();
        txtPeso.clear();
        txtId.clear();
        txtTipoPlumaje.clear();
        txtSonidosImitados.clear();
        txtDuenoNombre.clear();
        comboVuela.setValue(null);
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(encabezado);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}
