package Vistas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;





public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MenuBusquedas.fxml"));
        Image icon = new Image(getClass().getResourceAsStream("BW-Automotive-bolsa-icono.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Bolsa de Trabajo");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
