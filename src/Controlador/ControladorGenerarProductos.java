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
import vista.GenerarProductos;

/**
 *
 * @author Universidad
 */
public class ControladorGenerarProductos {
    
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
    }
    
    
    
}
