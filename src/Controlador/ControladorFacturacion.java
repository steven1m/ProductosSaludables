/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Producto;
import Modelo.ProductoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.Facturacion;

/**
 *
 * @author Universidad
 */
public class ControladorFacturacion implements ActionListener{

    
    private final Facturacion facturacion;

    public ControladorFacturacion(Facturacion facturacion) {
        this.facturacion = facturacion;
        setListeners();
    }
   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equalsIgnoreCase("Finalizar Venta") ){
           
            
        }else if (e.getActionCommand().equalsIgnoreCase("Agregar")){
            String clave = this.facturacion.getCodigoProducto();
            int cantidad = this.facturacion.getCantidad();
           
            agregarProducto (buscarProducto("id", clave), cantidad);
            
        }else if (e.getActionCommand().equalsIgnoreCase("Buscar Prod.")){

            
        }else if (e.getActionCommand().equalsIgnoreCase("Cargar venta")){

            
        }
    }
    
    private void setListeners (){
        this.facturacion.setListeners(this);
    }
    
    private ArrayList<Producto> buscarProducto(String clave, String valor){
        ProductoDAO modeloProducto = new ProductoDAO();
        ArrayList<Producto> lista = modeloProducto.leer(clave, valor);
        return lista;
    }
    
    
    private void agregarProducto(ArrayList<Producto> lista, int cantidad){
        
        if (!lista.isEmpty()){
            
            Producto producto = lista.get(0);
            if (producto.getCantidad() > cantidad && cantidad > 0){
                
                this.facturacion.agregarProductoTabla(producto, cantidad);
                
            }else{
                JOptionPane.showMessageDialog(null, "Stock insuficiente");
            }
            
        }else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
            
    } 
    
}
