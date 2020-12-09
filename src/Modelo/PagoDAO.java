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
public class PagoDAO {
    public int crear (Pago pago){
        int resultado = 0;
        Connection con = null;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.pago(id, valor, fecha, tipo_pago_id, empleado_id) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, pago.getId());
            ps.setFloat(2, pago.getValor());
            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(pago.getFecha());
            
            ps.setString(3, currentTime);
            
            ps.setInt(4, pago.getTipoPagoId());
            ps.setInt(5, pago.getEmpleadoId());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public ArrayList <Pago> leer (String clave, String valor){
        ArrayList <Pago>  lista = new ArrayList<>();
        Connection con ;
        PreparedStatement ps ;
        ResultSet rs ;
        
        try{
            con = Conexion.getConnection();
            String sentencia;
            
            if ("".equals(clave) || "".equals(valor)){
                sentencia = "SELECT * FROM pago;";
            }else if("nombre".equals(clave)) {
                sentencia = "SELECT * FROM pago WHERE nombre =?;";
                
            }else{
                sentencia = "SELECT * FROM pago where id=?";
            }
            
           
            ps = con.prepareStatement(sentencia);
            
            if (!"".equals(clave) && !"".equals(valor) ){
                ps.setString(1, valor);
            }
            rs = ps.executeQuery();
                        
            Pago pago;
            while(rs.next()){
                pago = new Pago();
                pago.setId(rs.getInt("id"));
                pago.setValor(rs.getFloat("valor"));
                
                pago.setFecha(rs.getDate("fecha"));
                
                pago.setTipoPagoId(rs.getInt("tipo_pago_id"));
                pago.setEmpleadoId(rs.getInt("empleado_id"));
                lista.add(pago);
                
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar (Pago pago){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.pago SET  "
                + "id=?, valor=?, fecha=?, tipo_pago_id=?, empleado_id=? "
                + "WHERE id=?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            ps.setInt(1, pago.getId());
            ps.setFloat(2, pago.getValor());
            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(pago.getFecha());
            ps.setString(3, currentTime);
            
            ps.setInt(4, pago.getTipoPagoId());
            ps.setFloat(5, pago.getEmpleadoId());
            ps.setInt(6, pago.getId());
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
        String sentencia = "DELETE FROM public.pago "
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
