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
    public int crear (Proveedor proveedor){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO proveedor"
                +" ( razon_social,direccion,correo,telefono,descripcion,encargado,catalogo_id,nit )"
                + " VALUES (?,?,?,?,?,?,?,?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            //ps.setInt(1, proveedor.getId());
            ps.setString(1, proveedor.getRazonSocial());
            ps.setString(2, proveedor.getDireccion());
            ps.setString(3, proveedor.getCorreo());
            ps.setString(4, proveedor.getTelefono());
            ps.setString(5, proveedor.getDescripcion());
            ps.setString(6, proveedor.getEncargado());
            ps.setInt(7, proveedor.getCatalogoID());
            ps.setString(8, proveedor.getNit());
            
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    
    public ArrayList <Proveedor> leer (String clave, String valor){
        ArrayList <Proveedor> lista = new ArrayList<>();
        
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            con = Conexion.getConnection();
            String sentencia;
        
            if ("".equals(clave) || "".equals(valor)){
                    sentencia = "SELECT * FROM proveedor;";
            }else if("nombre".equals(clave)) {
                sentencia = "SELECT * FROM proveedor WHERE razon_social = ?;";
            }else {
                sentencia = "SELECT * FROM proveedor WHERE id=?";
            }
            
            ps = con.prepareStatement(sentencia);
            
            if (!"".equals(clave) && !"".equals(valor) ){
                ps.setString(1, valor);
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
                proveedor.setEncargado("encargado");
                proveedor.setCatalogoID(rs.getInt("catalogo_id"));
                lista.add(proveedor);
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return lista;
    }
     
    
    
    public int actualizar(Proveedor proveedor, String nit){
         
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE proveedor SET razon_social=?, "
                + "direccion=?, correo=?, telefono=?, descripcion=?, encargado=?"
                + " WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            //ps.setInt(1, proveedor.getId());
            //ps.setString(2, proveedor.getNit());
            ps.setString(1, proveedor.getRazonSocial());
            ps.setString(2, proveedor.getDireccion());
            ps.setString(3, proveedor.getCorreo());
            ps.setString(4, proveedor.getTelefono());
            ps.setString(5, proveedor.getDescripcion());
            ps.setString(6, proveedor.getEncargado());
            ps.setString(7, nit);
            
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
        String sentencia = "DELETE FROM proveedor "
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
