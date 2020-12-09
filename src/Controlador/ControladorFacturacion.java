/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Universidad
 */
public class ControladorFacturacion implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
    private void agregarProducto(ArrayList<Producto> lista){
        Iterator <Producto> iterador = lista.iterator();
        
        while(iterador.hasNext()){
            Producto producto = iterador.next();
            
        }
    } 
    
}
