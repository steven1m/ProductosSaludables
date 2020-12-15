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
public class CatalogoDAO {
    
    public int crear (Catalogo catalogo){
        
        int resultado = 0;
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.catalogo( id, descripcion, proveedor_id )"
                + " VALUES (?, ?, ?);";
        
        //System.out.println(catalogo.getProveedorId());
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, catalogo.getId());
            ps.setString(2, catalogo.getDescripcion());
            ps.setInt(3, catalogo.getProveedorId());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    public ArrayList <Catalogo> leer (String clave, String valor){
        
        ArrayList <Catalogo> lista = new ArrayList<>();
        
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia;
        if ("".equals(clave) || "".equals(valor) ){
            sentencia ="SELECT * FROM catalogo;";
        }else if (clave.equals("id")){
            sentencia ="SELECT * FROM catalogo WHERE id=?;";
        }else {
            sentencia ="SELECT * FROM catalogo WHERE proveedor_id=?;";
        }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            if (!clave.equals("") && !"".equals(valor)){
               ps.setInt(1, Integer.parseInt(valor));
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                Catalogo catalogo = new Catalogo();
                catalogo.setId(rs.getInt("id"));
                catalogo.setDescripcion(rs.getString("descripcion"));
                catalogo.setProveedorId(rs.getInt("proveedor_id"));
                lista.add(catalogo);
            }

        }catch(SQLException | NumberFormatException ex ){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return lista;
    }
     
    public int actualizar (Catalogo catalogo){
        
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.catalogo SET descripcion=? "
                + "WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            ps.setString(1, catalogo.getDescripcion());
            ps.setInt(2, catalogo.getId());
            
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
        String sentencia = "DELETE FROM public.catalogo "
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
