/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ListaIngredientesDAO;
import Modelo.MateriaPrimaDAO;
import Modelo.ProduccionDAO;
import Modelo.ProductoDAO;
import Modelo.Producto;
import Modelo.ListaIngredientes;
import Modelo.MateriaPrima;
import Modelo.Produccion;
import vista.GenerarProductos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Universidad
 */
public class ControladorGenerarProductos implements ActionListener{
    
    private final GenerarProductos vista;
    private final ProductoDAO modeloProducto;
    private final MateriaPrimaDAO modeloMateriaPrima;
    private final ProduccionDAO modeloProduccion;
    private final ListaIngredientesDAO modeloIngredientes;

    public ControladorGenerarProductos(GenerarProductos vista, ProductoDAO modeloProducto,
                                       MateriaPrimaDAO modeloMateriaPrima, ProduccionDAO modeloProduccion,
                                       ListaIngredientesDAO modeloIngredientes) {
        this.vista = vista;
        this.modeloProducto = modeloProducto;
        this.modeloMateriaPrima = modeloMateriaPrima;
        this.modeloProduccion = modeloProduccion;
        this.modeloIngredientes = modeloIngredientes;
        setListeners();
    }
    
    private void setListeners (){
        this.vista.setListeners(this);
    }
    
    private boolean verificarCantidadMateriaPrima(String clave, String valor){
        int claveId = Integer.parseInt(clave);
        
        //int cantidad = 0;
        boolean siCantidad = false;
        
        ArrayList<MateriaPrima> lista = modeloMateriaPrima.leer(clave, valor);
        
        for (int i=0; i<lista.size(); i++){
            
            if( lista.get(i).getId() == claveId && lista.get(i).getCantidad()>=1){
                siCantidad = true;
            } else {
                siCantidad = false;
            }
        }
        return siCantidad;
    }
    
    /*private int verificarIngredientes(String clave, String valor){
        ListaIngredientesDAO modeloListaIngredientes = new ListaIngredientesDAO();
        
        
        ArrayList<Producto> lista = modeloProducto.leer(clave, valor);
        return 0;
    } */
    
    private ArrayList<Producto> buscarProducto(String clave, String valor){
        ProductoDAO modeloProducto = new ProductoDAO();
        ArrayList<Producto> lista = modeloProducto.leer(clave, valor);
        return lista;
    }
    
    private void agregarProducto(ArrayList<Producto> lista, int cantidad){
        
        if (!lista.isEmpty()){
            
            Producto producto = lista.get(0);
            if (producto.getCantidad() > cantidad && cantidad > 0){
                
                this.vista.agregarProduccionTabla(producto, cantidad);
                
            }else{
                JOptionPane.showMessageDialog(null, "Stock insuficiente");
            }
            
        }else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
            
    } 
    
    private void agregarCantidadProducto(int id, int cantidadProducir){
        ProductoDAO modelProducto = new ProductoDAO();
        int cantidadActual = modelProducto.leerCantidad(id);
        cantidadProducir = cantidadActual + cantidadProducir;
        int resultado = modelProducto.actualizarCantidad(id, cantidadProducir);
        if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Se agregaron los Productos");
        }
    }
    
    private void disminuirCantidadMateriaPrima(int id, int cantidad){
        MateriaPrimaDAO modelMateriaPrima = new MateriaPrimaDAO();
        int cantidadActual = modelMateriaPrima.leerCantidad(id);
        cantidad = cantidadActual - cantidad;
        int resultado = modelMateriaPrima.actualizarCantidad(id, cantidad);
        if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Se actualizó Materia Prima");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equalsIgnoreCase("Agregar")){
            
            String clave = this.vista.getCodigoProducto();
            int cantidad = this.vista.getCantidad();
            agregarProducto(buscarProducto("id", clave), cantidad);
            
        }else if (e.getActionCommand().equalsIgnoreCase("Producir")){
            
            int cantidadProducir = Integer.parseInt( this.vista.getTablaProduccion().getValueAt(0, 0).toString());
            int id = Integer.parseInt(this.vista.getCodigoProducto());
            agregarCantidadProducto(id,cantidadProducir);
            disminuirCantidadMateriaPrima(id,1);
            
        }
        
    }
    
}
