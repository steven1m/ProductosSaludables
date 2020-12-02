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
        buscar("", "");
    }
    
    private void setListeners(){
        
        this.panelProducto.agregarListener(this);
    }
    
    private void agregar (Producto producto){
       int resultado = modeloProducto.crear(producto);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelProducto.operacionesCrud("");
           buscar("", "");
       }
    }
   
    private void editar (Producto producto){
       int resultado = modeloProducto.actualizar(producto);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelProducto.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void eliminar (int codigo){
        int resultado = this.modeloProducto.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelProducto.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void buscar (String clave, String valor){
        ArrayList<Producto> lista = this.modeloProducto.leer(clave, valor);
        this.panelProducto.cargarTabla(lista);
        this.panelProducto.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregar(this.panelProducto.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editar(this.panelProducto.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminar(Integer.valueOf(this.panelProducto.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscar("", "");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.panelProducto.datosBuscar();
                buscar(datosBuscar[0], datosBuscar[1]);
               
            }
           
        }
}
