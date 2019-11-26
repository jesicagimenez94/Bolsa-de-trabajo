package Clases;

import Conexion.Conectar;
import Vistas.ControllerMenuBusquedas;
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

public class Permisos  {
      int id_permiso;
      String nombre_permiso;



    public int getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public String getNombre_permiso() {
        return nombre_permiso;
    }

    public void setNombre_permiso(String nombre_permiso) {
        this.nombre_permiso = nombre_permiso;
    }

    public Permisos(int id_permiso, String nombre_permiso) {
        this.id_permiso = id_permiso;
        this.nombre_permiso = nombre_permiso;
    }

    @Override
    public String toString(){
        return getNombre_permiso();
    }

    public Permisos() {
    }

    public Permisos(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public Permisos(String nombre_permiso) {
        this.nombre_permiso = nombre_permiso;
    }


    public boolean RegistrarPermiso(Permisos per){//metodo que guarda la informacion del registro

        PreparedStatement ps=null;
        Conectar con = new Conectar();

        String sql="INSERT INTO permisos(nombre_permiso)VALUES(?)";
        try {
            ps = con.getConnection().prepareStatement(sql);
            ps.setString(1, per.getNombre_permiso());
            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public int existePermiso(String user1){ //metodo para saber  si ya hay registros con ese nombre de usuario

        PreparedStatement ps=null;
        ResultSet rs= null;
        Conectar con = new Conectar();

        String sql="SELECT count(id_permiso)FROM permisos WHERE nombre_permiso=?";
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

    public List<Permisos> ListarPermisos() {//metodo que devuelve la lista de los usuarios
        Conectar con = new Conectar();
        List<Permisos> listaPer = new ArrayList<Permisos>();
        String sql = "SELECT id_permiso, nombre_permiso FROM permisos";
        PreparedStatement ps =null;
        ResultSet rs =null;

        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Permisos p= new Permisos();
                p.setId_permiso(rs.getInt("id_permiso"));
                p.setNombre_permiso(rs.getString("nombre_permiso"));
                listaPer.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca1");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca2");
        }
        return listaPer;
    }

    @FXML
    public ObservableList<Permisos> TipoPermiso() {//combo permisos
        Conectar con = new Conectar();
        ObservableList<Permisos> listaPermisos = FXCollections.observableArrayList();

        try {
            String sql = "SELECT id_permiso, nombre_permiso FROM permisos";
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listaPermisos.addAll(new Permisos(rs.getInt("id_permiso"), rs.getString("nombre_permiso")));



            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca2");
        }
        return listaPermisos;

    }

    @FXML
    public ObservableList<Permisos> TipoPermiso1() {//combo permisos
        Conectar con = new Conectar();
        ObservableList<Permisos> listaPermisos = FXCollections.observableArrayList();

        try {
            String sql = "SELECT id_permiso, nombre_permiso FROM permisos";
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listaPermisos.addAll(new Permisos( rs.getString("nombre_permiso")));



            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca2");
        }
        return listaPermisos;

    }



}
