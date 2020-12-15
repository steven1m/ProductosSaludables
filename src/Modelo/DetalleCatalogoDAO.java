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
    
    public int crear(DetalleCatalogo detalle){
        int resultado = 0;
        Connection con;
        PreparedStatement ps;
        String sentencia = "INSERT INTO detalle_catalogo( id, catalogo_id, nombre,"
                + " descripcion, precio, materia_prima_id) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, detalle.getId());
            ps.setInt(2,detalle.getCatalogoId());
            ps.setString(3, detalle.getNombre());
            ps.setString(4, detalle.getDescripcion());
            ps.setFloat(5, detalle.getPrecio());
            ps.setInt(6, detalle.getMateriaPrimaId());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        } 
        return resultado;
    }
    
    public ArrayList <DetalleCatalogo> leer (String clave, String valor){
        ArrayList <DetalleCatalogo>  lista = new ArrayList<>();
         
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia;
        
        if ("".equals(clave) || "".equals(valor)){
                sentencia = "SELECT * FROM detalle_catalogo;";
            }else if("nombre".equals(clave)) {
                sentencia = "SELECT * FROM detalle_catalogo WHERE nombre =?;";
                
            }else if("catalogo".equals(clave)){
                sentencia = "SELECT * FROM detalle_catalogo where catalogo_id=?";
            }else {
                sentencia = "SELECT * FROM detalle_catalogo where id=?";
            }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
           
            if (!"".equals(clave) && !"".equals(valor) && !"id".equals(clave)){
                ps.setString(1, valor);
            }else if (!"".equals(clave) && !"".equals(valor)){
                ps.setInt(1, Integer.parseInt(valor));
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                DetalleCatalogo detalle = new DetalleCatalogo();
                detalle.setId(rs.getInt("id"));
                detalle.setCatalogoId(rs.getInt("catalogo_id"));
                detalle.setNombre(rs.getString("nombre"));
                detalle.setDescripcion(rs.getString("descripcion"));
                detalle.setPrecio(rs.getFloat("precio"));
                detalle.setMateriaPrimaId(rs.getInt("materia_prima_id"));
                lista.add(detalle);
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar (DetalleCatalogo detalle){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE detalle_catalogo SET  nombre=?, descripcio=?, precio=? "
                + "catalogo_id=? " + "materia_prima_id=? "
                + "WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            ps.setString(1, detalle.getNombre());
            ps.setString(2, detalle.getDescripcion());
            ps.setFloat(3, detalle.getPrecio());
            ps.setInt(4, detalle.getCatalogoId());
            ps.setInt(5, detalle.getMateriaPrimaId());
            ps.setInt(6, detalle.getId());
            
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
        String sentencia = "DELETE FROM detalle_catalogo "
                + "WHERE id=?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1,id);
            
            resultado = ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
}
