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
public class CategoriaMateriaPrimaDAO {
    
    public int crear (CategoriaMateriaPrima categoria){
         int resultado = 0;
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.categoriaMateriPrima( id, descripcion,"
                + "VALUES (?, ?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, String.valueOf(categoria.getId()));
            ps.setString(2, categoria.getDescripcion());
            
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    public ArrayList <CategoriaMateriaPrima> leer (int id){
        ArrayList <CategoriaMateriaPrima>  lista = new ArrayList<>();
         Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia;
        if (id == 0){
            sentencia ="SELECT * FROM public.CategoriaMateriaPrima;";
        }else {
            sentencia ="SELECT * FROM public.CategoriaMateriaPrima WHERE id=?;";
        }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            if (id != -1){
               ps.setString(1, String.valueOf(id));
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                CategoriaMateriaPrima categoria = new CategoriaMateriaPrima();
                categoria.setId(rs.getInt("id"));
                categoria.setDescripcion("descripcion");
               
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar (CategoriaMateriaPrima categoria){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.CategoriaMateriaPrima SET  descripcio=?, "            
                + "WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, categoria.getDescripcion());
            ps.setString(3, String.valueOf(categoria.getId()));
            
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
        String sentencia = "DELETE FROM public.CategproaMateriaPrima "
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
