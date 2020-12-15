/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DetalleFactura;
import Modelo.DetalleFacturaDAO;
import Modelo.Factura;
import Modelo.FacturaDAO;
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
    private final DetalleFacturaDAO modeloDetalle;
    private final FacturaDAO modeloFactura;

    public ControladorFacturacion(Facturacion facturacion, DetalleFacturaDAO modeloDetalle, FacturaDAO modeloFactura) {
        this.facturacion = facturacion;
        this.modeloDetalle = modeloDetalle;
        this.modeloFactura = modeloFactura;
        setListeners();
    }
    
    
    private void finalizarVenta(Factura factura, ArrayList<DetalleFactura> listaDetalle){
        int resultado = this.modeloFactura.crear(factura);
        
        if (resultado != 0 )
        {
            listaDetalle.forEach(detalle -> {
                this.modeloDetalle.crear(detalle);
            });
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
    
    
        @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equalsIgnoreCase("Finalizar Venta") ){
           
            finalizarVenta(this.facturacion.crearObjetoFactura(), 
                    this.facturacion.listaDetalleFactura());
            
        }else if (e.getActionCommand().equalsIgnoreCase("Agregar")){
            
            String clave = this.facturacion.getCodigoProducto();
            int cantidad = this.facturacion.getCantidad();
           
            agregarProducto (buscarProducto("id", clave), cantidad);
            
        }else if (e.getActionCommand().equalsIgnoreCase("Buscar Prod.")){

            
        }else if (e.getActionCommand().equalsIgnoreCase("Cargar venta")){

            
        }else if (e.getActionCommand().equalsIgnoreCase("aumentar")){
            
            if (this.facturacion.getjTablePrincipal().getSelectedRow()!= -1){
               
               int fila  = this.facturacion.getjTablePrincipal().getSelectedRow();
               String clave = this.facturacion.getjTablePrincipal().
                                        getValueAt(fila, 0).toString();
                
                int cantidad = this.facturacion.getCantidadIncremento();
                agregarProducto (buscarProducto("id", clave), 1);
            }
            
            
        }
    }
}
