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
public class DetallePedidoDAO {
    public int crear (DetallePedido detalle){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.detalle_pedido( pedido_id, producto_id, cantidad, precio"
                + " VALUES (?, ?, ?, ?);";
         
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            ps.setInt(1, detalle.getPedidoId());
            ps.setInt(2, detalle.getProductoId());
            ps.setInt(3, detalle.getCantidad());
            ps.setFloat(4, detalle.getPrecio());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    public ArrayList <DetallePedido> leer (String clave, String valor){
        ArrayList <DetallePedido>  lista = new ArrayList<>();
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia;
        
        if ("".equals(clave) || "".equals(valor)){
                sentencia = "SELECT * FROM detalle_pedido;";
            }else if("pedido id".equals(clave)) {
                sentencia = "SELECT * FROM detalle_pedido WHERE pedido_id = ?;";
                
            }else{
                sentencia = "SELECT * FROM detalle_pedido WHERE producto_id = ?;";
            }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            
            rs = ps.executeQuery();

            while (rs.next()){
                DetallePedido Dpedido = new DetallePedido();
                Dpedido.setPedidoId(rs.getInt("pedido_id"));
                Dpedido.setProductoId(rs.getInt("producto_id"));
                Dpedido.setCantidad(rs.getInt("cantidad"));
                Dpedido.setPrecio(rs.getFloat("precio"));
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar (DetallePedido detalle){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE public.detalle_pedido SET pedido_id, producto_id, cantidad, precio"
                + " WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, detalle.getPedidoId());
            ps.setInt(2, detalle.getProductoId());
            ps.setInt(3, detalle.getCantidad());
            ps.setFloat(4, detalle.getPrecio());
            
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
        String sentencia = "DELETE FROM detalle_pedido"
                + " WHERE id=?";
        
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
