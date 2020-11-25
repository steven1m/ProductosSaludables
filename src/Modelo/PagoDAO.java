/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Servicios.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    public ArrayList <Pago> leer (int id){
        ArrayList <Pago>  lista = new ArrayList<>();
        
        return lista;
    }
    
    public int actualizar (Pago pago){
        int resultado = 0;
        return resultado;
    }
    
    public int borrar (int id){
        int resultado = 0;
        return resultado;
    }
}
