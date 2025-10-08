// MenuController.java
package co.edu.uniquindio.poo.veterinariafinal.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controlador lógico que gestiona la apertura de las ventanas principales del sistema.
 */
public class MenuController {

    /**
     * Método general para abrir una ventana FXML con su título.
     * @param fxml ruta del archivo FXML
     * @param titulo título de la ventana
     */
    public void abrirVentana(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/veterinariafinal/" + fxml));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}