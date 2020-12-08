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

import Modelo.EmpleadoDAO;
import Modelo.Empleado;
import vista.PanelEmpleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class ControladorEmpleado implements ActionListener{
    
    private final EmpleadoDAO modeloEmpleado;
    private final PanelEmpleado panelEmpleado;

    public ControladorEmpleado(EmpleadoDAO modeloEmpleado, PanelEmpleado panelEmpleado) {
        this.modeloEmpleado = modeloEmpleado;
        this.panelEmpleado = panelEmpleado;
        setListeners();
        buscarEmpleado(0);
    }
    
    private void setListeners(){
        this.panelEmpleado.agregarListener(this);
    }
    
    private void agregarEmpleado (Empleado empleado){
       int resultado = modeloEmpleado.crear(empleado);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelEmpleado.operacionesCrud("");
           buscarEmpleado(0);
       }
    }
   
    private void editarEmpleado (Empleado empleado){
       int resultado = modeloEmpleado.actualizar(empleado);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelEmpleado.operacionesCrud("");
           buscarEmpleado(0);
       }
    }
    
    private void eliminarEmpleado (int codigo){
        int resultado = this.modeloEmpleado.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelEmpleado.operacionesCrud("");
           buscarEmpleado(0);
       }
    }
    
    private void buscarEmpleado (int id){
        ArrayList<Empleado> lista = this.modeloEmpleado.leer( id );
        this.panelEmpleado.cargarTablaEmpleado(lista);
        this.panelEmpleado.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregarEmpleado(this.panelEmpleado.crearObjetoEmpleado());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editarEmpleado(this.panelEmpleado.crearObjetoEmpleado());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminarEmpleado(Integer.valueOf(this.panelEmpleado.getId()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscarEmpleado(0);
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               //String[] datosBuscar = this.panelEmpleado.datosBuscar();
                buscarEmpleado(0);
               
            }
           
        }
}
