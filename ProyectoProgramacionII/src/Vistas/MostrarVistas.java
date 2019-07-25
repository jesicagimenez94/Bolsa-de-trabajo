package Vistas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MostrarVistas {

    public void mostrarVistaAlumnos(){

        try{

            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("MenuAlumnos.fxml"));//trae la ventana de Alumnos
            Parent root= (Parent) fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception e)
        {e.printStackTrace();
            System.out.println(e);
        }
    }

    public void mostrarVistaEmpresas(){

        try{

            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("MenuEmpresas.fxml"));//trae la ventana de emresas
            Parent root= (Parent) fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root));
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
            stage.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarVistaLogin(){


        try{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("VistaLogin.fxml"));//trae la ventana de registros
            Parent root3= (Parent) fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root3));
            stage.show();

        }catch(IOException e){
            e.printStackTrace();
        }

    }



    public void mostrarPropuestas() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VistaPropuestas.fxml"));//trae la ventana de Propuestas Laborales
            Parent root3 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root3));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mostrarVistaMenuAlumnos() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuAlumnos.fxml"));//trae la ventana de Propuestas Laborales
            Parent root3 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root3));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    }