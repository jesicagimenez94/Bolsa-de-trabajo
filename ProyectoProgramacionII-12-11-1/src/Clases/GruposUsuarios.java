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

public class GruposUsuarios {
    int id_grupo;
    int id_usuario;
    int grupo;
    int usuario;



    public int getId_grupo() {
        return this.id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getId_usuario() {
        return this.id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public GruposUsuarios() {
    }

    public GruposUsuarios(int id_grupo, int id_usuario) {
        this.id_grupo = id_grupo;
        this.id_usuario = id_usuario;
    }

    public boolean RegistrarGrupo(GruposUsuarios gr){//metodo que guarda la informacion del registro

        Conectar con = new Conectar();

        String sql="INSERT INTO grupos_usuarios (id_grupo,id_usuario) VALUES (?,?)";
        PreparedStatement ps1=null;
        try {
            ps1 = con.getConnection().prepareStatement(sql);
            ps1.setInt(1, gr.getId_grupo());
            ps1.setInt(2,gr.getId_usuario());
            ps1.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }




    }


    @FXML
    public ObservableList<GruposUsuarios> listarGruposUsuarios() {//combo grupos
        Conectar con = new Conectar();
        ObservableList<GruposUsuarios> listaUsuarios = FXCollections.observableArrayList();

        try {
            String sql = "SELECT id_usuario,id_grupo FROM grupos_usuarios";
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GruposUsuarios gp= new GruposUsuarios();
                gp.setId_usuario(rs.getInt("id_usuario"));
                gp.setId_grupo(rs.getInt("id_grupo"));

                listaUsuarios.addAll(gp);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca2");
        }
        return listaUsuarios;

    }







}
