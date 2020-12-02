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
        String sentencia = "INSERT INTO public.catalogo( pedidoId,precio ,cantidad "
                + "producto_id ) "
                + "VALUES (?, ?, ?,?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, String.valueOf(detalle.getPedidoId()));
            ps.setString(1, String.valueOf(detalle.getProductoId()));
            ps.setString(3, String.valueOf(detalle.getCantidad()));
             ps.setString(3, String.valueOf(detalle.getPrecio()));
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    public ArrayList <DetallePedido> leer (int id){
        ArrayList <DetallePedido>  lista = new ArrayList<>();
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia;
        if (id == -1){
            sentencia ="SELECT * FROM detallePedido.catalogo;";
        }else {
            sentencia ="SELECT * FROM public.detallePedido WHERE pedidoId=?;";
        }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            if (id != -1){
               ps.setString(1, String.valueOf(id));
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                DetallePedido Dpedido = new DetallePedido();
                Dpedido.setPedidoId(rs.getInt("pedidoId"));
                Dpedido.setCantidad(rs.getInt("cantidad"));
                Dpedido.setPrecio(rs.getInt("precio"));
                Dpedido.setProductoId(rs.getInt("productoId"));
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
        String sentencia = "UPDATE public.detallePedido SET  precio=?, cantidad=? "
                + "productoId=? "
                + "WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, String.valueOf(detalle.getPedidoId()));
            ps.setString(2, String.valueOf(detalle.getProductoId()));
            ps.setString(3, String.valueOf(detalle.getPrecio()));
             ps.setString(4, String.valueOf(detalle.getCantidad()));
            
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
        String sentencia = "DELETE FROM detallePedido.catalogo "
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
