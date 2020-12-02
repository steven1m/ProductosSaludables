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
        String sentencia = "INSERT INTO public.materia( id, nombre,descripcion,precio,cantidad,categoriaId,proveedorId"
                 + "categoriaId ) " + "proveedorId ) "
                + "VALUES (?, ?, ?,?,?,?,?);";
        
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
    
    public ArrayList <MateriaPrima> leer (int id){
        ArrayList <MateriaPrima>  lista = new ArrayList<>();
         Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia;
        if (id == -1){
            sentencia ="SELECT * FROM public.materiaPrima;";
        }else {
            sentencia ="SELECT * FROM public.materiaPrima WHERE id=?;";
        }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            if (id != -1){
               ps.setString(1, String.valueOf(id));
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                MateriaPrima materia = new MateriaPrima();
                materia.setId(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));
                materia.setDescripcion(rs.getString("descripcion"));
                materia.setPrecio(rs.getFloat("precio"));
                materia.setCantidad(rs.getFloat("cantidad"));
                materia.setCategoriaId(rs.getInt("categoriaId"));
                materia.setProveedorId(rs.getInt("proveedorId"));  
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
        String sentencia = "UPDATE public.cliente SET  id=?, nombre=?,descripcion=?,precio=?,cantidad=? "
                + "proveedorId=? "+ "categoriaId=? "
                + "WHERE id=?;";
        
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
        String sentencia = "DELETE FROM public.MateriaPrima "
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