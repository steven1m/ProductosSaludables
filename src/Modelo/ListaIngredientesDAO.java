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
public class ListaIngredientesDAO {
    public int crear (ListaIngredientes ingredientes){
        int resultado = 0;
        Connection con = null;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.ingredientes(producto_id, materia_prima_id, cantidad, costo) "
                + "VALUES (?, ?, ?, ?)";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, ingredientes.getProduccionId());
            ps.setInt(2, ingredientes.getMateriaPrimaId());
            ps.setFloat(3, ingredientes.getCantidad());
            ps.setFloat(4, ingredientes.getCosto());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public ArrayList<ListaIngredientes> leer(int id){
        ArrayList <ListaIngredientes>lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            con = Conexion.getConnection();
            String sentencia = "SELECT * FROM ingredientes ORDER BY id";            
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
                        
            ListaIngredientes ingredientes = null;
            while(rs.next()){
                ingredientes = new ListaIngredientes();
                ingredientes.setProduccionId(rs.getInt("producto_id"));
                ingredientes.setMateriaPrimaId(rs.getInt("materia_prima_id"));
                ingredientes.setCantidad(rs.getFloat("cantidad"));
                ingredientes.setCosto(rs.getFloat("costo"));
                lista.add(ingredientes);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar(ListaIngredientes ingredientes){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.ingredientes SET producto_id=?, "
                + "materia_prima_id=?, cantidad=?, costo=?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, ingredientes.getProduccionId());
            ps.setInt(2, ingredientes.getMateriaPrimaId());
            ps.setFloat(3, ingredientes.getCantidad());
            ps.setFloat(1, ingredientes.getCosto());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public int borrar(int id){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "DELETE FROM public.ingredientes "
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
