package Conexion;


import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {

    static String bd="bolsadetrabajo";
    static String login="root";
    static  String password="";
    static String url="jdbc:mysql://localhost/"+bd;

    Connection connection=null;


    public Conectar(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connection=DriverManager.getConnection(url,login, password);
                if(connection!=null){
                    System.out.println("conexion exitosa");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection(){


        return connection;
    }

    public void desconectar(){

        try{

            connection.close();
        } catch (SQLException e) {

        }
    }

}



