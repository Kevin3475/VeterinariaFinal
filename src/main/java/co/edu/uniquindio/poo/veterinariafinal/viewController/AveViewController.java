package co.edu.uniquindio.poo.veterinariafinal.viewController;

import co.edu.uniquindio.poo.veterinariafinal.controller.AveController;
import co.edu.uniquindio.poo.veterinariafinal.model.Ave;
import co.edu.uniquindio.poo.veterinariafinal.model.Dueno;
import co.edu.uniquindio.poo.veterinariafinal.model.Vuela;
import co.edu.uniquindio.poo.veterinariafinal.model.Veterinaria;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox<Dueno> comboDueno;

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
    @FXML
    private TableColumn<Ave, String> colDueno;

    private AveController aveController;
    private Veterinaria veterinaria;

    @FXML
    public void initialize() {
        aveController = new AveController();
        comboVuela.setItems(FXCollections.observableArrayList(Vuela.values()));
        configurarTabla();
        tablaAves.setItems(aveController.getListaAves());
    }

    public void setVeterinaria(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
        if (veterinaria != null) {
            ObservableList<Dueno> listaDuenos = veterinaria.getListDuenos();
            comboDueno.setItems(listaDuenos);

            // Configurar cómo mostrar los dueños
            comboDueno.setCellFactory(lv -> new ListCell<Dueno>() {
                @Override
                protected void updateItem(Dueno item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? "" : item.getNombre() + " - " + item.getId());
                }
            });

            comboDueno.setButtonCell(new ListCell<Dueno>() {
                @Override
                protected void updateItem(Dueno item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? "" : item.getNombre() + " - " + item.getId());
                }
            });
        }
    }

    private void configurarTabla() {
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colRaza.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRaza()));
        colEdad.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getEdad()).asObject());
        colPeso.setCellValueFactory(c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().getPesoKg()).asObject());
        colId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        colTipoPlumaje.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipoPlumaje()));
        colSonidosImitados.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getSonidosImitados()).asObject());
        colVuela.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getVuela().toString()));
        colDueno.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getDueno() != null ? c.getValue().getDueno().getNombre() : "Sin dueño"));
    }

    @FXML
    private void agregarAve() {
        try {
            if (!validarCampos()) return;

            Dueno duenoSeleccionado = comboDueno.getValue();
            if (duenoSeleccionado == null) {
                mostrarAlerta("Error", "Debe seleccionar un dueño", "Seleccione un dueño de la lista", Alert.AlertType.ERROR);
                return;
            }

            String nombre = txtNombre.getText();
            String raza = txtRaza.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            String id = txtId.getText();
            String tipoPlumaje = txtTipoPlumaje.getText();
            int sonidosImitados = Integer.parseInt(txtSonidosImitados.getText());
            Vuela vuela = comboVuela.getValue();

            aveController.agregarAve(nombre, raza, edad, peso, id, duenoSeleccionado, tipoPlumaje, sonidosImitados, vuela);

            mostrarAlerta("Éxito", "Ave registrada", "El ave se registró correctamente", Alert.AlertType.INFORMATION);
            limpiarCampos();
            tablaAves.refresh();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Campos numéricos inválidos", "Verifique edad, peso o sonidos imitados.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void actualizarAve() {
        Ave aveSeleccionada = tablaAves.getSelectionModel().getSelectedItem();
        if (aveSeleccionada != null) {
            try {
                if (!validarCampos()) return;

                Dueno duenoSeleccionado = comboDueno.getValue();
                if (duenoSeleccionado == null) {
                    mostrarAlerta("Error", "Debe seleccionar un dueño", "Seleccione un dueño de la lista", Alert.AlertType.ERROR);
                    return;
                }

                // Actualizar el ave
                aveSeleccionada.setNombre(txtNombre.getText());
                aveSeleccionada.setRaza(txtRaza.getText());
                aveSeleccionada.setEdad(Integer.parseInt(txtEdad.getText()));
                aveSeleccionada.setPesoKg(Double.parseDouble(txtPeso.getText()));
                aveSeleccionada.setId(txtId.getText());
                aveSeleccionada.setDueno(duenoSeleccionado);
                aveSeleccionada.setTipoPlumaje(txtTipoPlumaje.getText());
                aveSeleccionada.setSonidosImitados(Integer.parseInt(txtSonidosImitados.getText()));
                aveSeleccionada.setVuela(comboVuela.getValue());

                mostrarAlerta("Éxito", "Ave actualizada", "El ave se actualizó correctamente", Alert.AlertType.INFORMATION);
                tablaAves.refresh();
                limpiarCampos();

            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "Campos numéricos inválidos", "Verifique edad, peso o sonidos imitados.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Advertencia", "Ninguna ave seleccionada", "Seleccione un ave para actualizar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void eliminarAve() {
        Ave aveSeleccionada = tablaAves.getSelectionModel().getSelectedItem();
        if (aveSeleccionada != null) {
            aveController.eliminarAve(aveSeleccionada);
            mostrarAlerta("Éxito", "Ave eliminada", "El ave se eliminó correctamente", Alert.AlertType.INFORMATION);
            tablaAves.refresh();
        } else {
            mostrarAlerta("Advertencia", "Ninguna ave seleccionada", "Seleccione un ave para eliminar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void seleccionarAve() {
        Ave aveSeleccionada = tablaAves.getSelectionModel().getSelectedItem();
        if (aveSeleccionada != null) {
            txtNombre.setText(aveSeleccionada.getNombre());
            txtRaza.setText(aveSeleccionada.getRaza());
            txtEdad.setText(String.valueOf(aveSeleccionada.getEdad()));
            txtPeso.setText(String.valueOf(aveSeleccionada.getPesoKg()));
            txtId.setText(aveSeleccionada.getId());
            txtTipoPlumaje.setText(aveSeleccionada.getTipoPlumaje());
            txtSonidosImitados.setText(String.valueOf(aveSeleccionada.getSonidosImitados()));
            comboVuela.setValue(aveSeleccionada.getVuela());

            // Buscar y seleccionar el dueño en el ComboBox
            if (aveSeleccionada.getDueno() != null && veterinaria != null) {
                for (Dueno dueno : veterinaria.getListDuenos()) {
                    if (dueno.getId().equals(aveSeleccionada.getDueno().getId())) {
                        comboDueno.setValue(dueno);
                        break;
                    }
                }
            }
        }
    }

    // 🔧 MÉTODO NUEVO AGREGADO
    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtRaza.clear();
        txtEdad.clear();
        txtPeso.clear();
        txtId.clear();
        txtTipoPlumaje.clear();
        txtSonidosImitados.clear();
        comboDueno.setValue(null);
        comboVuela.setValue(null);
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtRaza.getText().isEmpty() || txtId.getText().isEmpty() ||
                txtTipoPlumaje.getText().isEmpty() || txtSonidosImitados.getText().isEmpty()) {
            mostrarAlerta("Error", "Campos obligatorios", "Complete todos los campos obligatorios", Alert.AlertType.ERROR);
            return false;
        }
        if (comboVuela.getValue() == null) {
            mostrarAlerta("Error", "Campo obligatorio", "Seleccione si vuela o no", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(encabezado);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}