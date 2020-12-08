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
public class ClienteDAO {
    public int crear (Cliente cliente){
          int resultado = 0;
        Connection con ;
        PreparedStatement ps;
        String sentencia = "INSERT INTO cliente( id, nombre, apellido, telefono,"
                + " direccion, correo, razon_social) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?);";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            ps.setInt(1, Integer.parseInt( cliente.getId() ));
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getCorreo());
            ps.setString(7, cliente.getRazonSocial());
            
            
            resultado = ps.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        
        return resultado;
    }
    
    public ArrayList <Cliente> leer (String clave, String valor){
        ArrayList <Cliente>  lista = new ArrayList<>();
        Connection con ;
        PreparedStatement ps;
        ResultSet rs;
        String sentencia = "";
        
        if ("".equals(clave) || "".equals(valor)){
                sentencia = "SELECT * FROM cliente;";
            }else if("nombre".equals(clave)) {
                sentencia = "SELECT * FROM cliente WHERE nombre=?";
                
            }else if("apellido".equals(clave)){
                sentencia = "SELECT * FROM cliente where apellido=?";
            }else if("codigo".equals(clave)){
                sentencia = "SELECT * FROM cliente where id=?";
            }
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            
            if ("codigo".equals(clave) && !"".equals(valor)){
                ps.setInt(1, Integer.parseInt(valor));
            } else if (!"".equals(clave) && !"".equals(valor) ){
                ps.setString(1, valor);
            }
            
            rs = ps.executeQuery();

            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getString("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setRazonSocial(rs.getString("razon_social")); 
                lista.add(cliente);
            }

        }catch(SQLException | NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return lista;
    }
    
    public int actualizar (Cliente cliente){
        int resultado = 0;
         Connection con ;
        PreparedStatement ps ;
        String sentencia = "UPDATE cliente SET nombre=?,apellido=?,telefono=?,direccion=?,correo=?,razon_social=?"
                +" WHERE id=?;";
        
        try{
            con = Conexion.getConnection();
            ps = con.prepareStatement(sentencia);
            //ps.setString(1, String.valueOf(cliente.getId()));
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getCorreo());
            ps.setString(6, cliente.getRazonSocial());
            ps.setInt(7, Integer.parseInt( cliente.getId() ));
            
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
        String sentencia = "DELETE FROM cliente"
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
