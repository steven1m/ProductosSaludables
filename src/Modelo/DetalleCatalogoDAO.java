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
public class DetalleCatalogoDAO {
    public int crearDCatalogo (DetalleCatalogo detalle){
        int resultado = 0;
        Connection con;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.catalogo( id, nombre,descripcion,precio"
                + "catalogoId ) "+ "materiaPrimaId ) "
                + "VALUES (?, ?, ?,?, ?, ?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, String.valueOf(detalle.getId()));
            ps.setString(2, detalle.getNombre());
            ps.setString(2, detalle.getDescripcion());
            ps.setString(3, String.valueOf(detalle.getPrecio()));
            ps.setString(3, String.valueOf(detalle.getCatalogoId()));
            ps.setString(3, String.valueOf(detalle.getMateriaPrimaId()));
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        } 
        return resultado;
    }
    
    public ArrayList <DetalleCatalogo> leerDCatalogo (int id){
        ArrayList <DetalleCatalogo>  lista = new ArrayList<>();
         
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia;
        if (id == -1){
            sentencia ="SELECT * FROM public.DetalleCatalogo;";
        }else {
            sentencia ="SELECT * FROM public.DetalleCatalogo WHERE id=?;";
        }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            if (id != -1){
               ps.setString(1, String.valueOf(id));
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                DetalleCatalogo Dcatalogo = new DetalleCatalogo();
                Dcatalogo.setId(rs.getInt("id"));
                Dcatalogo.setCatalogoId(rs.getInt("catalogoId"));
                Dcatalogo.setNombre(rs.getString("nombre"));
                Dcatalogo.setDescripcion(rs.getString("descripcion"));
                Dcatalogo.setPrecio(rs.getFloat("precio"));
                Dcatalogo.setMateriaPrimaId(rs.getInt("materiaPrimaId"));
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return lista;
    }
    
    public int actualizarDCatalogo (DetalleCatalogo detalle){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.detalleCatalogo SET  nombre=?, descripcio=?, precio=? "
                + "catalogoId=? " + "materiaPrimaId=? "
                + "WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, detalle.getId());
            ps.setString(2, detalle.getNombre());
            ps.setString(2, detalle.getDescripcion());
            ps.setFloat(3, detalle.getPrecio());
            ps.setInt(3, detalle.getCatalogoId());
            ps.setInt(3, detalle.getMateriaPrimaId());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public int borrarDCatalogo (int id){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "DELETE FROM public.detalleCatalogo "
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
