/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Servicios.Conexion;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Universidad
 */
public class EmpleadoDAO {
    public int crear (Empleado empleado){
        int resultado = 0;
         Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.empleado( id, nombre,apellido,telefono,direccion,correo,cargo,salario"
            
                + "VALUES (?, ?, ?,?,?,?,?,?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, String.valueOf(empleado.getId()));
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellido());
            ps.setString(4, empleado.getDireccion());
            ps.setString(5, empleado.getCargo());
            ps.setString(6, empleado.getCorreo());
            ps.setString(7, empleado.getTelefono());
            ps.setString(8, String.valueOf(empleado.getSalario()));
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public ArrayList <Empleado> leer (int id){
        ArrayList <Empleado>  lista = new ArrayList<>();
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia;
        if (id == -1){
            sentencia ="SELECT * FROM public.emplado;";
        }else {
            sentencia ="SELECT * FROM public.empleado WHERE id=?;";
        }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            if (id != -1){
               ps.setString(1, String.valueOf(id));
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setNombre("descripcion");
                empleado.setApellido("apellido");
                empleado.setCorreo("correo");
                empleado.setDireccion("direccion");
                empleado.setCargo("cargi");
                empleado.setTelefono("telefono");
                empleado.setSalario(rs.getFloat("salario"));
                lista.add(empleado);
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar (Empleado empleado){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.empleado SET  descripcio=?, "
               
                + "WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, empleado.getNombre());
            ps.setString(2, String.valueOf(empleado.getId()));
            ps.setString(3, empleado.getApellido());
            ps.setString(4, empleado.getDireccion());
            ps.setString(5, empleado.getTelefono());
            ps.setString(6, empleado.getCargo());
            ps.setFloat(7, empleado.getSalario());
            ps.setString(8, empleado.getCorreo());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public int borrar (int id){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "DELETE FROM public.empleado "
                + "WHERE id=?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, String.valueOf(id));
            
            resultado = ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
}
