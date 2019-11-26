package Vistas;

import Clases.Registros;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MostrarVistas {


    public void mostrarVistaEmpresas(){
        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("MenuBusquedas.fxml"));//trae la ventana de emresas
            Parent root= (Parent) fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root));
            Image icon = new Image(getClass().getResourceAsStream("BW-Automotive-bolsa-icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Empresas");
            Registros reg= new Registros();
            System.out.println( "valor grupo"+reg.getNombre_grupo());
            ControllerMenuBusquedas vista= fxmlLoader.getController();
            vista.traerPermisos("administrador");
            stage.show();

        }catch (Exception e)
        {e.printStackTrace();
            System.out.println(e);
        }



    }


    public void mostrarVistaRegistros(){

        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("RegistroInicio.fxml"));//trae la ventana de registros
            Parent root3= (Parent) fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root3));
            Image icon = new Image(getClass().getResourceAsStream("BW-Automotive-bolsa-icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Inicio");

            stage.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }





    public void mostrarVistaMenuAlumnos() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuAlumnos.fxml"));//trae la ventana de Propuestas Laborales
            Parent root3 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root3));
            Image icon = new Image(getClass().getResourceAsStream("BW-Automotive-bolsa-icono.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Alumnos");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    }