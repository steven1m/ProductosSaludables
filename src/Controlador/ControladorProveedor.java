/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.ProveedorDAO;
import Modelo.Proveedor;
import vista.PanelProveedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class ControladorProveedor implements ActionListener{
    
    private final ProveedorDAO modeloProveedor;
    private final PanelProveedor panelProveedor;

    public ControladorProveedor(ProveedorDAO modeloProveedor, PanelProveedor panelProveedor) {
        this.modeloProveedor = modeloProveedor;
        this.panelProveedor = panelProveedor;
        setListeners();
        buscarProveedor("", "");
    }
    
    private void setListeners(){
        this.panelProveedor.agregarListener(this);
    }
    
    private void agregarProveedor (Proveedor proveedor){
       int resultado = modeloProveedor.crear(proveedor);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelProveedor.operacionesCrud("");
           buscarProveedor("", "");
       }
    }
   
    /**
     * @param proveedor objeto Proveedor a actualizar
     * @param nit nit del proveedor que se va actualizar
     */
    private void editarProveedor (Proveedor proveedor, String nit){ 
       int resultado = modeloProveedor.actualizar(proveedor, nit);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelProveedor.operacionesCrud("");
           buscarProveedor("", "");
       }
    }
    
    private void eliminarProveedor (int codigo){
        int resultado = this.modeloProveedor.borrar(codigo);
        if (resultado != 0){
            JOptionPane.showMessageDialog(null, "Operacion Exitosa");
            this.panelProveedor.operacionesCrud("");
            buscarProveedor("", "");
       }
    }
    
    private void buscarProveedor (String clave, String valor){
        ArrayList<Proveedor> lista = this.modeloProveedor.leer(clave, valor);
        this.panelProveedor.cargarTablaProveedor(lista);
        this.panelProveedor.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregarProveedor(this.panelProveedor.crearObjetoProveedor());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                String nit = JOptionPane.showInputDialog(null, "Ingrese Nit del proveedor que solicita actualizar");
                editarProveedor(this.panelProveedor.crearObjetoProveedor(),nit);
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminarProveedor(Integer.valueOf(this.panelProveedor.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
                JOptionPane.showMessageDialog(null, "se va actualizar la tabla");
                buscarProveedor("", "");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                String[] datosBuscar = this.panelProveedor.datosBuscar();
                buscarProveedor(datosBuscar[0], datosBuscar[1]);
            }
        }
}

