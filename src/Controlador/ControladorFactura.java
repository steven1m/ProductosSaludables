/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author ACER
 */

import Modelo.FacturaDAO;
import Modelo.Factura;
import vista.PanelFactura;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class ControladorFactura implements ActionListener{
    
    private final FacturaDAO modeloFactura;
    private final PanelFactura PanelFactura;

    public ControladorFactura(FacturaDAO modeloFactura, PanelFactura PanelFactura) {
        this.modeloFactura = modeloFactura;
        this.PanelFactura = PanelFactura;
        setListeners();
        buscarFactura("", "");
    }
    
    private void setListeners(){
        
        this.PanelFactura.agregarListener(this);
    }
    
    private void agregarFactura (Factura factura){
       int resultado = modeloFactura.crear(factura);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.PanelFactura.operacionesCrud("");
           buscarFactura("", "");
       }
    }
   
    private void editarFactura (Factura factura){
       int resultado = modeloFactura.actualizar(factura);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.PanelFactura.operacionesCrud("");
           buscarFactura("", "");
       }
    }
    
    private void eliminarlFactura (int codigo){
        int resultado = this.modeloFactura.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.PanelFactura.operacionesCrud("");
           buscarFactura("", "");
       }
    }
    
    private void buscarFactura (String clave, String valor){
        ArrayList<Factura> lista = this.modeloFactura.leer(clave, valor);
        this.PanelFactura.cargarTablaFactura(lista);
        this.PanelFactura.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregarFactura(this.PanelFactura.crearObjetoFactura());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editarFactura(this.PanelFactura.crearObjetoFactura());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminarlFactura(Integer.valueOf(this.PanelFactura.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscarFactura("", "");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.PanelFactura.datosBuscar();
                buscarFactura(datosBuscar[0], datosBuscar[1]);
               
            }
           
        }
}

