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
public class TipoVentaDAO {
    public int crear (TipoVenta tipo){
        int resultado = 0;
        Connection con = null;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.tipo_pago(id, descripcion) "
                + "VALUES (?, ?)";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, tipo.getId());
            ps.setString(2, tipo.getDescripcion());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public ArrayList <TipoVenta> leer (int id){
        ArrayList <TipoVenta>  lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = Conexion.getConnection();
            String sentencia = "";
            
            sentencia = "SELECT * FROM tipo_pago ORDER BY id";            
                                 
            ps = con.prepareStatement(sentencia);
            
            rs = ps.executeQuery();
                        
            TipoVenta tipo = null;
            while(rs.next()){
                tipo = new TipoVenta();
                tipo.setId(rs.getInt("id"));
                tipo.setDescripcion(rs.getString("descripcion"));
                lista.add(tipo);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        
        return lista;
    }
    
    public int actualizar (TipoVenta tipo){
        int resultado = 0;
         Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.tipo_pago SET id=?, descripcion=?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, tipo.getId());
            ps.setString(2, tipo.getDescripcion());
            
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
        String sentencia = "DELETE FROM public.tipo_pago "
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

