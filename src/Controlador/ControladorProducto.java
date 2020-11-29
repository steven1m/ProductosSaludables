package Controlador;

import Modelo.ProductoDAO;
import Modelo.Producto;
import vista.PanelProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class ControladorProducto implements ActionListener{
    
    private final ProductoDAO modeloProducto;
    private final PanelProducto panelProducto;

    public ControladorProducto(ProductoDAO modeloProducto, PanelProducto panelProducto) {
        this.modeloProducto = modeloProducto;
        this.panelProducto = panelProducto;
        setListeners();
        buscarProducto("", "");
    }
    
    private void setListeners(){
        
        this.panelProducto.agregarListener(this);
    }
    
    private void agregarProducto (Producto producto){
       int resultado = modeloProducto.crear(producto);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelProducto.operacionesCrud("");
           buscarProducto("", "");
       }
    }
   
    private void editarProducto (Producto producto){
       int resultado = modeloProducto.actualizar(producto);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelProducto.operacionesCrud("");
           buscarProducto("", "");
       }
    }
    
    private void eliminarProducto (int codigo){
        int resultado = this.modeloProducto.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelProducto.operacionesCrud("");
           buscarProducto("", "");
       }
    }
    
    private void buscarProducto (String clave, String valor){
        ArrayList<Producto> lista = this.modeloProducto.leer(clave, valor);
        this.panelProducto.cargarTablaProductos(lista);
        this.panelProducto.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregarProducto(this.panelProducto.crearObjetoProducto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editarProducto(this.panelProducto.crearObjetoProducto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminarProducto(Integer.valueOf(this.panelProducto.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscarProducto("", "");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.panelProducto.datosBuscar();
                buscarProducto(datosBuscar[0], datosBuscar[1]);
               
            }
           
        }
}
