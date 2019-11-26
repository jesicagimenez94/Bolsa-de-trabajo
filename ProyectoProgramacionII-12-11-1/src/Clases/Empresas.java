package Clases;

import Conexion.Conectar;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Empresas {
    private final SimpleIntegerProperty id_empresas   ;

    private final  SimpleStringProperty Nombre_empresa;
    private final SimpleStringProperty Puesto;
    private final SimpleStringProperty Area;
    private final SimpleStringProperty Sector;
    private final SimpleStringProperty Detalle;
    private final SimpleStringProperty Industria;

//    private  Date fecha;


    public Empresas()
    {
        this.Nombre_empresa= new SimpleStringProperty("");
        this.Sector= new SimpleStringProperty("");
        this.Area= new SimpleStringProperty("");
        this.Detalle= new SimpleStringProperty("");
        this.Industria= new SimpleStringProperty("");
        this.id_empresas = new SimpleIntegerProperty(0);
        this.Puesto= new SimpleStringProperty("");
    }

    public Empresas(int id_empresas,String nombre_empresa, String puesto, String area, String sector, String detalle, String industria) {
        this.id_empresas =  new SimpleIntegerProperty (id_empresas);
        this.Nombre_empresa = new SimpleStringProperty(nombre_empresa);
        this.Puesto = new SimpleStringProperty(puesto);
        this.Area = new SimpleStringProperty(area);
       this.Sector = new SimpleStringProperty(sector);
        this.Detalle = new SimpleStringProperty( detalle);
        this.Industria = new SimpleStringProperty(industria);
    }

    public SimpleIntegerProperty getId_empresas() {
        return id_empresas;
    }

    public void setId_empresas(int id_empresas) {
        this.id_empresas.set(id_empresas);
    }


    public String getNombre_empresa() {
        return Nombre_empresa.get();
    }


    public void setNombre_empresa(String xnombre_empresa) {
        Nombre_empresa.set(xnombre_empresa);
    }

    public String getPuesto() {
        return Puesto.get();
    }

    public void setPuesto(String zpuesto) {
        Puesto.set(zpuesto);
    }

    public String getArea() {
        return Area.get();
    }

    public SimpleStringProperty areaProperty() {
        return Area;
    }

    public void setArea(String area) {
        this.Area.set(area);
    }

    public String getSector() {
        return Sector.get();
    }



    public void setSector(String sector) {
        this.Sector.set(sector);
    }

    public String getDetalle() {
        return Detalle.get();
    }


    public void setDetalle(String detalle) {
        this.Detalle.set(detalle);
    }

    public String getIndustria() {
        return Industria.get();
    }


    public void setIndustria(String industria) {
        this.Industria.set(industria);
    }

  /*  public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
*/




    public void insertarPropuesta(Empresas empresas){//guarda los datos de la empresa en la base de datos


        Conectar con= new Conectar();
        String sql="INSERT INTO empresas(nombre_empresa,puesto,industria,area,sector,detalle)VALUES(?,?,?,?,?,?)";
        PreparedStatement ps=null;

        try{
            ps=con.getConnection().prepareStatement(sql);
            ps.setString(1,empresas.getNombre_empresa());
            ps.setString(2,empresas.getPuesto());
            ps.setString(3,empresas.getIndustria());
            ps.setString(4,empresas.getArea());
            ps.setString(5, empresas.getSector());
            ps.setString(6,empresas.getDetalle());
  //          ps.setDate(7, (java.sql.Date)empresas.getFecha());
            ps.executeUpdate();

        }catch (SQLException e){

            System.out.println(e.getMessage());
        }finally {
            try{
                ps.close();
                con.desconectar();
            }catch (SQLException e){}
        }

    }


    public void mostrarDatos(Empresas empresas){//guarda los datos de la empresa en la base de datos


        Conectar con= new Conectar();
        String sql = "SELECT nombre_empresa,puesto,industria,sector,detalle,area FROM empresas";
        PreparedStatement ps=null;
        ResultSet rs =null;

        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Empresas emp = new Empresas();
                emp.setNombre_empresa(rs.getString("Nombre_empresa"));
                emp.setPuesto(rs.getString("Puesto"));
                emp.setIndustria(rs.getString("Industria"));
                emp.setArea(rs.getString("Sector"));
                emp.setSector(rs.getString("Detalle"));
                emp.setDetalle(rs.getString("Area"));
            }

            }catch (SQLException e){

            System.out.println(e.getMessage());
        }finally {
            try{
                ps.close();
                con.desconectar();
            }catch (SQLException e){}
        }

    }

    public List<Empresas> ListarPropuestas() {//metodo que devuelve la lista de las propuestas
        Conectar con = new Conectar();
        List<Empresas> listaEmp = new ArrayList<Empresas>();
        String sql = "SELECT nombre_empresa,puesto,industria,sector,detalle,area FROM empresas";
        PreparedStatement ps =null;
        ResultSet rs =null;

        try {
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Empresas emp= new Empresas();
                emp.setNombre_empresa(rs.getString("Nombre_empresa"));
                emp.setPuesto(rs.getString("Puesto"));
                emp.setIndustria(rs.getString("Industria"));
                emp.setArea(rs.getString("Sector"));
                emp.setSector(rs.getString("Detalle"));
                emp.setDetalle(rs.getString("Area"));

                listaEmp.add(emp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca1");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("aca2");
        }
        return listaEmp;
    }


}
