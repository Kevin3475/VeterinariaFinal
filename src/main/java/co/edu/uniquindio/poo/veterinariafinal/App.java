package co.edu.uniquindio.poo.veterinariafinal;

import co.edu.uniquindio.poo.veterinariafinal.model.Veterinaria;
import co.edu.uniquindio.poo.veterinariafinal.viewController.DuenoViewController;
import co.edu.uniquindio.poo.veterinariafinal.viewController.ReptilViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Crear la veterinaria compartida
            Veterinaria veterinaria = new Veterinaria("Patitas", "1203");

            // Cargar la vista del menú principal
            FXMLLoader loaderMenu = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/veterinariafinal/menu.fxml"));
            Parent rootMenu = loaderMenu.load();

            // Cargar DuenoView
            FXMLLoader loaderDueno = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/veterinariafinal/Dueno.fxml"));
            Parent rootDueno = loaderDueno.load();
            DuenoViewController duenoController = loaderDueno.getController();
            duenoController.setVeterinaria(veterinaria);

            // Cargar ReptilView
            FXMLLoader loaderReptil = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/veterinariafinal/Reptil.fxml"));
            Parent rootReptil = loaderReptil.load();
            ReptilViewController reptilController = loaderReptil.getController();
            reptilController.setVeterinaria(veterinaria);

            // Mostrar la escena del menú
            Scene scene = new Scene(rootMenu);
            primaryStage.setTitle("Menú Principal - Veterinaria");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
