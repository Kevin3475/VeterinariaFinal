package co.edu.uniquindio.poo.veterinariafinal.viewController;

import co.edu.uniquindio.poo.veterinariafinal.controller.PerroController;
import co.edu.uniquindio.poo.veterinariafinal.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class PerroViewController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtRaza;
    @FXML private TextField txtEdad;
    @FXML private TextField txtPeso;
    @FXML private TextField txtId;
    @FXML private TextField txtDueno;
    @FXML private ComboBox<TamanoPerro> comboTamano;
    @FXML private TextField txtAdiestramiento;
    @FXML private TextField txtPaseo;
    @FXML private Label lblMensaje;

    @FXML private TableView<Perro> tablaPerros;
    @FXML private TableColumn<Perro, String> colNombre;
    @FXML private TableColumn<Perro, String> colRaza;
    @FXML private TableColumn<Perro, Integer> colEdad;
    @FXML private TableColumn<Perro, Double> colPeso;
    @FXML private TableColumn<Perro, String> colId;
    @FXML private TableColumn<Perro, String> colDueno;
    @FXML private TableColumn<Perro, TamanoPerro> colTamano;
    @FXML private TableColumn<Perro, String> colAdiestramiento;
    @FXML private TableColumn<Perro, String> colPaseo;

    private final PerroController perroController = new PerroController();

    @FXML
    public void initialize() {
        comboTamano.getItems().addAll(TamanoPerro.values());
        tablaPerros.setItems(perroController.obtenerListaPerros());

        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colRaza.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getRaza()));
        colEdad.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getEdad()));
        colPeso.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getPesoKg()));
        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colDueno.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getDueno() != null ? cell.getValue().getDueno().getNombre() : "Sin dueño"));
        colTamano.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getTamanoPerro()));
        colAdiestramiento.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNivelAdiestramiento()));
        colPaseo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNecesidadPaseo()));
    }

    @FXML
    private void guardarPerro() {
        try {
            String nombre = txtNombre.getText();
            String raza = txtRaza.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            String id = txtId.getText();
            String nombreDueno = txtDueno.getText();
            TamanoPerro tamano = comboTamano.getValue();
            String adiestramiento = txtAdiestramiento.getText();
            String paseo = txtPaseo.getText();

            String resultado = perroController.guardarPerro(nombre, raza, edad, peso, id,
                    nombreDueno, tamano, adiestramiento, paseo);

            lblMensaje.setText(resultado);
            lblMensaje.setStyle(resultado.startsWith("✅") ? "-fx-text-fill: green;" : "-fx-text-fill: red;");

            limpiarCampos();

        } catch (NumberFormatException e) {
            lblMensaje.setText("⚠️ Edad y peso deben ser numéricos.");
            lblMensaje.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            lblMensaje.setText("❌ Error inesperado.");
            lblMensaje.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelar() {
        limpiarCampos();
        lblMensaje.setText("");
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtRaza.clear();
        txtEdad.clear();
        txtPeso.clear();
        txtId.clear();
        txtDueno.clear();
        txtAdiestramiento.clear();
        txtPaseo.clear();
        comboTamano.setValue(null);
    }
}
