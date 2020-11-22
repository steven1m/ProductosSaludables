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
public class ProductoDAO {
    public int crear (Producto producto){
        int resultado = 0;
        
        Connection con;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.producto( id, nombre, descripcion, precio, cantidad, precio_venta"
                + "VALUES (?, ?, ?, ?, ?, ?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, String.valueOf(producto.getId()));
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setString(4, String.valueOf(producto.getPrecio()));
            ps.setString(5, String.valueOf(producto.getCantidad()));
            ps.setString(6, String.valueOf(producto.getPrecioVenta()));
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public ArrayList <Producto> leer (int id){
        ArrayList <Producto>  lista = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = Conexion.getConnection();
            String sentencia = "";
            
            sentencia = "SELECT * FROM producto ORDER BY id";            
                                 
            ps = con.prepareStatement(sentencia);
            
            rs = ps.executeQuery();
                        
            Producto producto = null;
            while(rs.next()){
                producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getInt("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecioVenta(rs.getInt("precio_venta"));
                lista.add(producto);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar (Producto producto){
        int resultado = 0;
        return resultado;
    }
    
    public int borrar (int id){
        int resultado = 0;
        return resultado;
    }
}
