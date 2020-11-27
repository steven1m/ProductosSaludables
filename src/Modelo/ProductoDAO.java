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
    public int crear(Producto producto){
        int resultado = 0;
        
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.producto(id, nombre, descripcion, precio, cantidad, precio_venta) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, producto.getId());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setFloat(4, producto.getPrecio());
            ps.setInt(5, producto.getCantidad());
            ps.setFloat(6, producto.getPrecioVenta());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public ArrayList<Producto> leer(String clave, String valor){
        ArrayList<Producto>  lista = new ArrayList<>();
        
        Connection con ;
        PreparedStatement ps ;
        ResultSet rs ;
        
        try{
            con = Conexion.getConnection();
            String sentencia;
            
            if ("".equals(clave)|| "".equals(valor)){
                sentencia = "SELECT * FROM producto;";
            }else if("nombre".equals(clave)) {
                sentencia = "SELECT * FROM producto WHERE nombre =?;";
                
            }else{
                sentencia = "SELECT * FROM producto where id=?";
            }
            
           
            ps = con.prepareStatement(sentencia);
            
            if (!"".equals(clave) && !"".equals(valor) ){
                ps.setInt(1, Integer.parseInt(valor));
            }
            rs = ps.executeQuery();
                        
            Producto producto;
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
    
    public int actualizar(Producto producto){
        int resultado = 0;
        
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.producto SET  "
                + "nombre=?, descripcion=?, precio=?, cantidad=?, precio_venta=? "
                + "WHERE id=?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3, producto.getPrecio());
            ps.setInt(4, producto.getCantidad());
            ps.setFloat(5, producto.getPrecioVenta());
            ps.setInt(6, producto.getId());
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    public int borrar(int id){
        int resultado = 0;
        
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "DELETE FROM public.producto "
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
