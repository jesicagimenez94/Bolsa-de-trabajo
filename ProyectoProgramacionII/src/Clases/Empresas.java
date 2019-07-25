package Clases;

import Conexion.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;



public class Empresas {
    int id_empresas;
    String nombre_empresa,puesto,industria,area,sector,detalle;


    Date fecha;

    public int getId_empresas() {
        return id_empresas;
    }

    public void setId_empresas(int id_empresas) {
        this.id_empresas = id_empresas;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getIndustria() {
        return industria;
    }

    public void setIndustria(String industria) {
        this.industria = industria;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }





    public void insertarPropuesta(Empresas empresas){//guarda los datos de la empresa en la base de datos


        Conectar con= new Conectar();
        String sql="INSERT INTO empresas(nombre_empresa,puesto,industria,area,sector,detalle,fecha)VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps=null;

        try{
            ps=con.getConnection().prepareStatement(sql);
            ps.setString(1,empresas.getNombre_empresa());
            ps.setString(2,empresas.getPuesto());
            ps.setString(3,empresas.getIndustria());
            ps.setString(4,empresas.getArea());
            ps.setString(5, empresas.getSector());
            ps.setString(6,empresas.getDetalle());
            ps.setDate(7, (java.sql.Date)empresas.getFecha());
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




    public ArrayList<Empresas> ListarPropuestas() {//metodo que devuelve la lista de las propuestas
        ArrayList<Empresas> listaEmp = new ArrayList<Empresas>();
        Conectar conec = new Conectar();
        String sql = "SELECT nombre_empresa,puesto,industria,area,sector,detalle FROM empresas";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresas emp = new Empresas();
                emp.setNombre_empresa(rs.getString(1));
                emp.setPuesto(rs.getString(2));
                emp.setIndustria(rs.getString(3));
                emp.setArea(rs.getString(4));
                emp.setSector(rs.getString(5));
                emp.setDetalle(rs.getString(6));

                listaEmp.add(emp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
        return listaEmp;
    }


}
