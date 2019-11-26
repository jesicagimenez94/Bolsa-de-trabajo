package Clases;

import Conexion.Conectar;

import java.security.spec.KeySpec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.*;

import Conexion.Hash;
import Vistas.ControllerMenuAlumnos;
import Vistas.ControllerMenuBusquedas;
import javafx.scene.control.Button;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;

public class Alumnos {
    int id_alumnos;
    String nombre,apellido,mail,mensaje;
    String dni;
    byte[] Archivo_cv;
    String nom_archivo;
    Button cv;


    public Alumnos() {

    }

    public Alumnos(int id_alumnos, String nombre, String apellido, String mail, String mensaje, String dni, byte[] Archivo_cv, String nom_archivo) {
        this.id_alumnos = id_alumnos;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.mensaje = mensaje;
        this.dni = dni;
        this.Archivo_cv = Archivo_cv;
        this.nom_archivo = nom_archivo;
    }





    public String getNom_archivo() {
        return nom_archivo;
    }

    public void setNom_archivo(String nom_archivo) {
        this.nom_archivo = nom_archivo;
    }



    public int getId_alumnos() {
        return id_alumnos;
    }

    public void setId_alumnos(int id_alumnos) {
        this.id_alumnos = id_alumnos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDni() {
        return dni;
    }
    public String getMail() {
        return mail;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public byte[] getArchivo_cv() {
        return Archivo_cv;
    }

    public void setArchivo_cv(byte[] archivo_cv) {
        Archivo_cv = archivo_cv;
    }

    public Button getButton() {
        return cv;
    }



    public  String ENCRYPT_KEY = "clave";

    public  String encript(String text) throws Exception {
        //Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

        KeySpec spec = new PBEKeySpec("password".toCharArray(), ENCRYPT_KEY.getBytes(), 65536, 256); // AES-256
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] key = f.generateSecret(spec).getEncoded();
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");


        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] encrypted = cipher.doFinal(text.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public  String decrypt(String encrypted) throws Exception {
        byte[] encryptedBytes=Base64.getDecoder().decode( encrypted.replace("\n", "") );

        //Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

        KeySpec spec = new PBEKeySpec("password".toCharArray(), ENCRYPT_KEY.getBytes(), 65536, 256); // AES-256
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] key = f.generateSecret(spec).getEncoded();
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");


        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        String decrypted = new String(cipher.doFinal(encryptedBytes));

        return decrypted;
    }





    public void insertarCV(Alumnos alumnos){  //metodo donde insertamos los datos del alumno a la base de datos


        Conectar con= new Conectar();
        String sql="INSERT INTO alumnos(nombre,apellido,mail,dni,nom_archivo,archivo_cv,mensaje)VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps=null;

        try{
            ps=con.getConnection().prepareStatement(sql);
            ps.setString(1,alumnos.getNombre());
            ps.setString(2,alumnos.getApellido());
            ps.setString(3,alumnos.getMail());
            ps.setString(4,alumnos.getDni());
            ps.setString(5, alumnos.getNom_archivo());
            ps.setBytes(6,alumnos.getArchivo_cv());
            ps.setString(7,alumnos.getMensaje());
            ps.executeUpdate();

        }catch (SQLException e){

            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                ps.close();
                con.desconectar();
            }catch (SQLException e){}
        }

    }


    public List<Alumnos> ListarCurriculum() {//metodo que devuelve la lista de los curriculums
        Conectar con = new Conectar();
        List<Alumnos> listaAlum = new ArrayList<Alumnos>();
        String sql = "SELECT nombre,apellido,mail,dni,archivo_cv,mensaje FROM alumnos";
        PreparedStatement ps =null;
        ResultSet rs =null;

        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Alumnos alu= new Alumnos();
                alu.setNombre(rs.getString("nombre"));
                alu.setApellido(rs.getString("apellido"));
                alu.setMail(decrypt(rs.getString("mail")));
                alu.setDni(rs.getString("dni"));
                        alu.setArchivo_cv(rs.getBytes("archivo_cv"));
                        alu.setMensaje(rs.getString("mensaje"));

                        listaAlum.add(alu);
                        }
                        } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("aca1");
                        } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("aca2");
                        }
                        return listaAlum;
                        }


public void ejecutarArchivo(){
    Conectar con = new Conectar();
    byte[] b = null;
    String sql= "SELECT archivo_cv FROM alumnos WHERE id_alumno=?";
    PreparedStatement ps= null;
    ResultSet rs=null;
    try{
        Alumnos al= new Alumnos();
        ps = con.getConnection().prepareStatement(sql);
        rs= ps.executeQuery();
        ps.setInt(1,al.getId_alumnos());

        while(rs.next()){
          b= rs.getBytes(1);

        }
        InputStream bos= new ByteArrayInputStream(b);

        int tamanoInput = bos.available();
        byte[] datosPDF = new byte[tamanoInput];
        bos.read(datosPDF,0,tamanoInput);


        OutputStream out = new FileOutputStream("new.pdf");
        out.write(datosPDF);


    }catch (IOException | NumberFormatException | SQLException ex) {
        System.out.println("error al abrir pdf"+ex.getMessage());


}

}

}
