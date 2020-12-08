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
public class MateriaPrimaDAO {
    public int crear (MateriaPrima materia){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO materia_prima( id, nombre,descripcion,precio,cantidad,categoria_id,proveedor_id )"
                + " VALUES (?, ?, ?, ?, ?, ?, ?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, materia.getId());
            ps.setString(2, materia.getNombre());
            ps.setString(3, materia.getDescripcion());
            ps.setFloat(4, materia.getPrecio());
            ps.setInt(5, materia.getCantidad());
            ps.setInt(6, materia.getCategoriaId());
            ps.setInt(7, materia.getProveedorId());
            
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public ArrayList <MateriaPrima> leer (String clave, String valor){
        ArrayList <MateriaPrima>  lista = new ArrayList<>();
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            con = Conexion.getConnection();
            String sentencia;
            
            if ("".equals(clave) || "".equals(valor)){
                sentencia = "SELECT * FROM materia_prima;";
            }else if("nombre".equals(clave)) {
                sentencia = "SELECT * FROM materia_prima WHERE nombre = ?;";
                
            }else{
                sentencia = "SELECT * FROM materia_prima where id=?";
            }
            
            ps = con.prepareStatement(sentencia);
            
            if (!"".equals(clave) && !"".equals(valor) ){
                ps.setString(1, valor);
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                MateriaPrima materia = new MateriaPrima();
                materia.setId(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));
                materia.setDescripcion(rs.getString("descripcion"));
                materia.setPrecio(rs.getFloat("precio"));
                materia.setCantidad(rs.getInt("cantidad"));
                materia.setCategoriaId(rs.getInt("categoria_id"));
                materia.setProveedorId(rs.getInt("proveedor_id"));
                lista.add(materia);
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar (MateriaPrima materia){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE materia_prima SET id=?, nombre=?, descripcion=?, precio=?, cantidad=?"
                + " proveedor_id=?, categoriaId=? WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, String.valueOf(materia.getId()));
            ps.setString(2, materia.getNombre());
            ps.setString(3, materia.getDescripcion());
            ps.setString(4, String.valueOf(materia.getPrecio()));
            ps.setString(5,  String.valueOf(materia.getCategoriaId()));
            ps.setString(6, String.valueOf(materia.getCantidad()));
            ps.setString(7, String.valueOf(materia.getProveedorId()));
            
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
        String sentencia = "DELETE FROM materia_prima "
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