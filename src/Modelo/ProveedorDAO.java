/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import Servicios.Conexion;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Universidad
 */
public class ProveedorDAO {
    public int crearProveedor (Proveedor proveedor){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.pro( id,nit,razonSocial,direccion,correo,telefono,descripcion,encargado"
                + "VALUES (?,?,?,?,?,?,?,?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, proveedor.getId());
            ps.setString(2, proveedor.getNit());
            ps.setString(3, proveedor.getRazonSocial());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getCorreo());
            ps.setString(6, proveedor.getTelefono());
            ps.setString(7, proveedor.getDescripcion());
            ps.setString(8, proveedor.getEncargado());
            
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    
    public ArrayList <Proveedor> leerProveedor (int id){
         ArrayList <Proveedor> lista = new ArrayList<>();
        
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia;
        if (id == -1){
            sentencia ="SELECT * FROM public.proveedor;";
        }else {
            sentencia ="SELECT * FROM public.proveedor WHERE id=?;";
        }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            if (id != -1){
               ps.setString(1, String.valueOf(id));
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNit("nit");
                proveedor.setRazonSocial("razonSocial");
                proveedor.setDireccion("direccion");
                proveedor.setCorreo("correo");
                proveedor.setTelefono("telefono");
                proveedor.setDescripcion("descripcion");
                proveedor.setEncargado("encargo");
                
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return lista;
    }
     
    
    
    public int actualizarProveedor (Proveedor proveedor){
         
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.proveedor SET  descripcio=?, "
                + "nit=? "+"razonSocial=?"+"direccion=?"+"correo=?"+"telefono=?"+"encargo=?"
                + "WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, proveedor.getId());
            ps.setString(2, proveedor.getNit());
            ps.setString(3, proveedor.getRazonSocial());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getCorreo());
            ps.setString(6, proveedor.getTelefono());
            ps.setString(7, proveedor.getDescripcion());
            ps.setString(8, proveedor.getEncargado());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    
    public int borrarProveedor (int id){
        
           
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "DELETE FROM public.proveedor "
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
