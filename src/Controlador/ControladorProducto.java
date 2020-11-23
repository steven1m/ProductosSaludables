package Controlador;

import Modelo.ProductoDAO;
import Modelo.Producto;
import vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class ControladorProducto {
    
    private ProductoDAO modeloProducto;
    private AddProducto addProducto;
    private ReadProducto readProducto;
    private UpdateProducto updateProducto;
    private DeleteProducto deleteProducto;

    public ControladorProducto(ProductoDAO modeloProducto, AddProducto addProducto) {
        this.modeloProducto = modeloProducto;
        this.addProducto = addProducto;
        this.addProducto.agregarlistenergrabar(new ListenerProducto());
        this.addProducto.agregarlistenercancelar(new ListenerProducto());
    }

    public ControladorProducto(ProductoDAO modeloProducto, ReadProducto readProducto) {
        this.modeloProducto = modeloProducto;
        this.readProducto = readProducto;
        this.readProducto.agregarlistenerconsultar(new ListenerProducto());
        this.readProducto.agregarlistenerlimpiar(new ListenerProducto());
    }

    public ControladorProducto(ProductoDAO modeloProducto, UpdateProducto updateProducto) {
        this.modeloProducto = modeloProducto;
        this.updateProducto = updateProducto;
    }

    public ControladorProducto(ProductoDAO modeloProducto, DeleteProducto deleteProducto) {
        this.modeloProducto = modeloProducto;
        this.deleteProducto = deleteProducto;
    }
    
    public Boolean verificarTexto(String texto){
        if (texto.length()<=0){
             
             return false;
        }else {
             return true;
        }
    }
    
    class ListenerProducto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Grabar") ){
                JOptionPane.showMessageDialog(null, "se van a grabar los datos.");
                crear();
            }else if (e.getActionCommand().equalsIgnoreCase("Consultar")){
                JOptionPane.showMessageDialog(null, "se van a cargar los datos.");
                leer();
            }
            
                
            //throw new UnsupportedOperationException("Not supported yet.");
        }
        
        private void crear(){
            if(verificarTexto(String.valueOf(addProducto.getID())) && verificarTexto(addProducto.getNombre()) && verificarTexto(addProducto.getDescripcion())
                    && verificarTexto(String.valueOf(addProducto.getPrecio())) && verificarTexto(String.valueOf(addProducto.getCantidad())) 
                            && verificarTexto(String.valueOf(addProducto.getPrecioVenta())))
            {
                Producto producto = new Producto();
                producto.setId(addProducto.getID());
                producto.setNombre(addProducto.getNombre());
                producto.setDescripcion(addProducto.getDescripcion());
                producto.setPrecio(addProducto.getPrecio());
                producto.setCantidad(addProducto.getCantidad());
                producto.setPrecioVenta(addProducto.getPrecioVenta());
               
                int resultado = 0;
                resultado = modeloProducto.crear(producto);
                if(resultado == 1){ 
                    JOptionPane.showMessageDialog(null, "Producto Grabado! " +resultado);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No Grabado! " +resultado);
                }
            }else {
                JOptionPane.showMessageDialog(null, "verificacion fallida ");
            }
            // end if verificarTexto()
        }
        
        private void leer(){
            ArrayList<Producto> listadoProductos;
            listadoProductos = modeloProducto.leer(0);
            readProducto.cargarProductos(listadoProductos);
        }
        
    }
}
