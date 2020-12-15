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
public class DetalleFacturaDAO {
    public int crear (DetalleFactura detalle){
        int resultado = 0;
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO public.detalle_factura (factura_id, producto_id, cantidad, precio)"
                + " VALUES (?, ?, ?, ?);";
         
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            ps.setInt(1, detalle.getIdFactura());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setFloat(4, detalle.getPrecio());
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    public ArrayList <DetalleFactura> leer ( int valor){
        ArrayList <DetalleFactura>  lista = new ArrayList<>();
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia = "SELECT * FROM detalle_factura  WHERE factura_id = ?;";
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, valor);
            rs = ps.executeQuery();

            while (rs.next()){
                DetalleFactura detalleFactura = new DetalleFactura();
                detalleFactura.setIdFactura(rs.getInt("factura_id"));
                detalleFactura.setIdProducto(rs.getInt("producto_id"));
                detalleFactura.setCantidad(rs.getInt("cantidad"));
                detalleFactura.setPrecio(rs.getFloat("precio"));
                lista.add(detalleFactura);
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return lista;
    }
    
    
    
    public int borrar (int idFactura){
        int resultado = 0;
         Connection con ;
        PreparedStatement ps ;
        String sentencia = "DELETE FROM detalle_factura "
                + " WHERE factura_id = ?";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, idFactura);
            
            resultado = ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return resultado;
    }
}
