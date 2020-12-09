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
public class PedidoDAO {
    public int crear (Pedido pedido){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO pedido(id, fecha_pedido, fecha_despacho," 
                +" empleado_id, cliente_id, estado_pedido_id, factura_id)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, pedido.getId());
            ps.setString(2, pedido.getFechaPedido());
            ps.setString(3, pedido.getFechaDespacho());
            ps.setInt(4, pedido.getEmpleadoId());
            ps.setInt(5, Integer.valueOf( pedido.getClienteId() ));
            ps.setInt(6, pedido.getEstadoPedidoId());
            ps.setInt(7, pedido.getFacturaId());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
    
    public ArrayList <Pedido> leer (String clave, String valor){
        ArrayList <Pedido>  lista = new ArrayList<>();
         Connection con ;
        PreparedStatement ps ;
        ResultSet rs ;
        
        try{
            con = Conexion.getConnection();
            String sentencia;
            
            if ("".equals(clave) || "".equals(valor)){
                sentencia = "SELECT * FROM pedido;";
            }else if("nombre".equals(clave)) {
                sentencia = "SELECT * FROM pedido WHERE nombre =?;";
                
            }else{
                sentencia = "SELECT * FROM pedido where id=?";
            }
            
           
            ps = con.prepareStatement(sentencia);
            
            if (!"".equals(clave) && !"".equals(valor) ){
                ps.setString(1, valor);
            }
            rs = ps.executeQuery();
                        
            Pedido pedido;
            while(rs.next()){
                pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setFechaPedido(rs.getString("fecha_pedido"));
                pedido.setFechaDespacho(rs.getString("fecha_despacho"));
                pedido.setEmpleadoId(rs.getInt("empleado_id"));
                pedido.setClienteId(rs.getString("cliente_id"));
                pedido.setEstadoPedidoId(rs.getInt("estado_pedido"));
                pedido.setFacturaId(rs.getInt("factura_id"));
                lista.add(pedido);
                
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar (Pedido pedido){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE pedido SET"
                + " fecha_pedido=?, fecha_despacho=?, empleado_id=?, cliente_id=?, estado_pedido=?"
                + " WHERE id=?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            ps.setString(1, pedido.getFechaPedido());
            
            ps.setString(2, pedido.getFechaDespacho());
            
            ps.setInt(3, pedido.getEmpleadoId());
            ps.setInt(4, Integer.valueOf( pedido.getClienteId() ));
            ps.setInt(5, pedido.getEstadoPedidoId());
            
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
        String sentencia = "DELETE FROM pedido"
                + " WHERE id=?";
        
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
