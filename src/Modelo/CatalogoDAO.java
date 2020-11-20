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
    
    public int crearCatalogo (Catalogo catalogo){
        
        int resultado = 0;
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.catalogo( id, descripcion,"
                + "proveedor_id ) "
                + "VALUES (?, ?, ?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, String.valueOf(catalogo.getId()));
            ps.setString(2, catalogo.getDescripcion());
            ps.setString(3, String.valueOf(catalogo.getProveedorId()));
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    public ArrayList <Catalogo> leerCatalogo (int id){
        
        ArrayList <Catalogo> lista = new ArrayList<>();
        
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia;
        if (id == -1){
            sentencia ="SELECT * FROM public.catalogo;";
        }else {
            sentencia ="SELECT * FROM public.catalogo WHERE id=?;";
        }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            if (id != -1){
               ps.setString(1, String.valueOf(id));
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                Catalogo catalogo = new Catalogo();
                catalogo.setId(rs.getInt("id"));
                catalogo.setDescripcion("descripcion");
                catalogo.setProveedorId(rs.getInt("proveedor_id"));
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return lista;
    }
     
    public int actualizarCatalogo (Catalogo catalogo){
        
        int resultado = 0;
        Connection con = null;
        PreparedStatement ps = null;
        String sentencia = "UPDATE public.catalogo SET  descripcio=?;"
                + "WHERE id=?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, catalogo.getDescripcion());
            ps.setString(2, String.valueOf(catalogo.getId()));
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    public int eliminarCatalogo (int id){
        
        int resultado = 0;
        
        return resultado;
    }
    
}
