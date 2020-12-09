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
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Universidad
 */
public class FacturaDAO {
    public int crear (Factura factura){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.factura(id, cliente_id, empleado_id, pago_id, tipo_venta_id, fecha) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, factura.getId());
            ps.setString(2, factura.getClienteId());
            ps.setInt(3, factura.getEmpleadoId());
            ps.setFloat(4, factura.getPadoId());
            ps.setInt(5, factura.getTipoVentaId());
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(factura.getFecha());
            Date fecha = new Date();
            ps.setString(6, currentTime);
            ps.setDate(6, (java.sql.Date) fecha);
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public ArrayList <Factura> leer (String clave, String valor){
        ArrayList <Factura>  lista = new ArrayList<>();
        Connection con ;
        PreparedStatement ps ;
        ResultSet rs ;
        
        try{
            con = Conexion.getConnection();
            String sentencia;
            
            if ("".equals(clave) || "".equals(valor)){
                sentencia = "SELECT * FROM factura;";
            }else if("nombre".equals(clave)) {
                sentencia = "SELECT * FROM factura WHERE nombre =?;";
                
            }else{
                sentencia = "SELECT * FROM factura where id=?";
            }
            
           
            ps = con.prepareStatement(sentencia);
            
            if (!"".equals(clave) && !"".equals(valor) ){
                ps.setString(1, valor);
            }
            rs = ps.executeQuery();
                        
            Factura factura;
            while(rs.next()){
                factura = new Factura();
                factura.setId(rs.getInt("id"));
                factura.setClienteId(rs.getString("cliente_id"));
                factura.setEmpleadoId(rs.getInt("empleado_id"));
                factura.setPadoId(rs.getInt("pago_id"));
                factura.setTipoVentaId(rs.getInt("tipo_venta_id"));
                factura.setFecha(rs.getDate("fecha"));
                lista.add(factura);
                
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar (Factura factura){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.factura SET "
                + "id=?, cliente_id=?, empleado_id=?, pago_id=?, tipo_venta_id=?, fecha=? "
                + "WHERE id=?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            ps.setInt(1, factura.getId());
            ps.setString(2, factura.getClienteId());
            ps.setInt(3, factura.getEmpleadoId());
            ps.setInt(4, factura.getPadoId());
            ps.setFloat(5, factura.getTipoVentaId());
            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(factura.getFecha());
            
            ps.setString(6, currentTime);
            ps.setInt(7, factura.getId());
            
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
        String sentencia = "DELETE FROM public.factura "
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
