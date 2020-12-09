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
        String sentencia = "INSERT INTO empleado( id, nombre, apellido, telefono, direccion, correo, cargo, salario)"
                           +" VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            ps.setInt(1, empleado.getId());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellido());
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getDireccion());
            ps.setString(6, empleado.getCorreo());
            ps.setString(7, empleado.getCargo());
            ps.setFloat(8, empleado.getSalario());
            
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
        
        if (id == 0){
            sentencia ="SELECT * FROM empleado;";
        }else {
            sentencia ="SELECT * FROM empleado where id=?;";
        }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            if (id != 0){
               ps.setInt(1, id);
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setNombre("nombre");
                empleado.setApellido("apellido");
                empleado.setCorreo("telefono");
                empleado.setDireccion("direccion");
                empleado.setCargo("correo");
                empleado.setTelefono("cargo");
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
        String sentencia = "UPDATE public.empleado SET" 
                            +" nombre=?, apellido=?, telefono=?, direccion=?, correo=?, cargo=?, salario=?"
                            +" WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getTelefono());
            ps.setString(4, empleado.getDireccion());
            ps.setString(5, empleado.getCorreo());
            ps.setString(6, empleado.getCargo());
            ps.setFloat(7, empleado.getSalario());
            ps.setInt(7, empleado.getId());
            
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
