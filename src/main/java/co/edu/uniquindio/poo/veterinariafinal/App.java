// App.java CORREGIDO
package co.edu.uniquindio.poo.veterinariafinal;

import co.edu.uniquindio.poo.veterinariafinal.model.Veterinaria;
import co.edu.uniquindio.poo.veterinariafinal.viewController.MenuViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private Veterinaria veterinaria;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Inicializar la veterinaria
        veterinaria = new Veterinaria("Veterinaria Patitas", "123456789");

        // 🔧 CORREGIR: Cargar el menú principal con la ruta correcta
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/veterinariafinal/menu.fxml"));

        // 🔧 ALTERNATIVA 1: Si la anterior no funciona
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));

        // 🔧 ALTERNATIVA 2: Ruta absoluta desde resources
        // FXMLLoader loader = new FXMLLoader(App.class.getResource("/MenuView.fxml"));

        Parent root = loader.load();

        // Pasar la veterinaria al controlador del menú
        MenuViewController menuController = loader.getController();
        menuController.setVeterinaria(veterinaria);

        // Configurar la ventana principal
        primaryStage.setTitle("Sistema Veterinaria - Patitas");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}