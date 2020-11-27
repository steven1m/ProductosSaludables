/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Servicios.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Universidad
 */
public class ProduccionDAO {
    public int crear(Produccion produccion){
        int resultado = 0;
        
        Connection con = null;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.produccion(id, nombre, costo, producto_id) "
                + "VALUES (?, ?, ?, ?)";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, produccion.getId());
            ps.setString(2, produccion.getNombre());
            ps.setFloat(3, produccion.getCosto());
            ps.setInt(4, produccion.getProductoId());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public ArrayList<Produccion> leer(int id){
        ArrayList<Produccion>  lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = Conexion.getConnection();
            String sentencia = "";
            
            sentencia = "SELECT * FROM produccion ORDER BY id";            
                                 
            ps = con.prepareStatement(sentencia);
            
            rs = ps.executeQuery();
                        
            Produccion produccion = null;
            while(rs.next()){
                produccion = new Produccion();
                produccion.setId(rs.getInt("id"));
                produccion.setNombre(rs.getString("nombre"));
                produccion.setCosto(rs.getFloat("costo"));
                produccion.setProductoId(rs.getInt("producto_id"));
                lista.add(produccion);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar(Produccion produccion){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.produccion SET id=?, "
                + "nombre=?, costo=?, producto_id=?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, produccion.getId());
            ps.setString(2, produccion.getNombre());
            ps.setFloat(3, produccion.getCosto());
            ps.setInt(1, produccion.getProductoId());
            
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
        String sentencia = "DELETE FROM public.produccion "
                + "WHERE id=?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, id);
            resultado = ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
}
