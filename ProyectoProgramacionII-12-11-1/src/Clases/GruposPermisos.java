package Clases;

import Conexion.Conectar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GruposPermisos {
public int id_grupo;
public int id_permiso;
boolean alta;
boolean baja;
boolean modificar;
boolean listar;
String nombre;
String nombre_permiso;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getPermiso() {
        return nombre_permiso;
    }

    public void setPermiso(String permiso) {
        this.nombre_permiso = permiso;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    public boolean isListar() {
        return listar;
    }

    public void setListar(boolean listar) {
        this.listar = listar;
    }



    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public boolean RegistrarPermiso(GruposPermisos gp){

        Conectar con = new Conectar();

        String sql="INSERT INTO grupos_permisos (id_grupo,id_permiso) VALUES (?,?)";
        PreparedStatement ps1=null;
        try {
            ps1 = con.getConnection().prepareStatement(sql);
            ps1.setInt(1,getId_grupo());
            ps1.setInt(2,getId_permiso());
            ps1.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }




    @FXML
    public ObservableList<GruposPermisos> listarPermisos() {//combo grupos
        Conectar con = new Conectar();
        ObservableList<GruposPermisos> listaPermisos = FXCollections.observableArrayList();

        try {
            String sql = "SELECT g.id_grupo, gr.nombre,g.id_permiso,u.nombre_permiso,g.alta,g.baja,g.modificar,g.listar FROM  permisos AS u JOIN grupos_permisos AS g ON u.id_permiso=g.id_permiso JOIN grupos AS gr ON gr.id_grupo= g.id_grupo ";
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GruposPermisos gp= new GruposPermisos();
                gp.setId_permiso(rs.getInt("id_permiso"));
                gp.setId_grupo(rs.getInt("id_grupo"));
                gp.setPermiso(rs.getString("nombre_permiso"));
                gp.setNombre(rs.getString("nombre"));
                gp.setAlta(rs.getBoolean("alta"));
                gp.setBaja(rs.getBoolean("baja"));
                gp.setModificar(rs.getBoolean("modificar"));
                gp.setListar(rs.getBoolean("listar"));
                listaPermisos.addAll(gp);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca2");
        }
        return listaPermisos;

    }


}
