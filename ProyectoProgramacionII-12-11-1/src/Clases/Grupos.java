package Clases;

import Conexion.Conectar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grupos {
    String nombre;
    int id_grupo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_grupo() {
        return id_grupo;
    }
    public int getID() {
        return id_grupo;
    }


    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public Grupos(String nombre, int id_grupo) {
        this.nombre = nombre;
        this.id_grupo = id_grupo;
    }

    public Grupos() {
    }

    public Grupos(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public Grupos(String nombre) {
        this.nombre = nombre;
    }


    public List<Grupos> ListarGrupos() {//metodo que devuelve la lista de los usuarios
        Conectar con = new Conectar();
        List<Grupos> listaGrup = new ArrayList<Grupos>();
        String sql = "SELECT nombre FROM grupos";
        PreparedStatement ps =null;
        ResultSet rs =null;

        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Grupos g= new Grupos();
                g.setNombre(rs.getString("nombre"));
                listaGrup.add(g);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca1");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca2");
        }
        return listaGrup;
    }

    public boolean RegistrarGrupo(Registros user){//metodo que guarda la informacion del registro

        PreparedStatement ps=null;
        Conectar con = new Conectar();

        String sql="INSERT INTO grupos (nombre)VALUES(?)";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    @FXML
    public ObservableList<Grupos> TipoUsuario() {//combo grupos
        Conectar con = new Conectar();
        ObservableList<Grupos> listaGrupos = FXCollections.observableArrayList();

        try {
            String sql = "SELECT id_grupo,nombre FROM grupos";
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listaGrupos.addAll(new Grupos(rs.getString("nombre"),rs.getInt("id_grupo")));



            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca2");
        }
        return listaGrupos;

    }

    public int existeGrupo(String user1){ //metodo para saber  si ya hay registros con ese nombre de usuario

        PreparedStatement ps=null;
        ResultSet rs= null;
        Conectar con = new Conectar();

        String sql="SELECT count(id_grupo)FROM grupos WHERE nombre=?";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setString(1, user1);
            rs =ps.executeQuery();//para que me devuelva datos porque es una consulta

            if(rs.next()){
                return rs.getInt(1);// me devuelve cuantos registros hay con ese nombre de grupo
            }
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }

    }

    public int MostrarIdGrupo(Grupos user1){ //metodo para saber  si ya hay registros con ese nombre de usuario

        PreparedStatement ps=null;
        ResultSet rs= null;
        Conectar con = new Conectar();

        String sql="SELECT nombre FROM grupos WHERE id_grupo=?";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setInt(1, user1.getId_grupo());
            rs =ps.executeQuery();//para que me devuelva datos porque es una consulta

            if(rs.next()){
                rs.getInt(1);// me devuelve cuantos registros hay con ese nombre de grupo
            }
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    @Override
    public String toString(){
        return getNombre();
    }
}
