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
public class ControladorProducto {
    
    private ProductoDAO modeloProducto;
    private PanelProducto panelProducto;

    public ControladorProducto(ProductoDAO modeloProducto, PanelProducto panelProducto) {
        this.modeloProducto = modeloProducto;
        this.panelProducto = panelProducto;
    }
   
    
    class listenerProducto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Grabar") ){
                JOptionPane.showMessageDialog(null, "se van a grabar los datos.");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Consultar")){
                JOptionPane.showMessageDialog(null, "se van a cargar los datos.");
               
            }
            
                
            //throw new UnsupportedOperationException("Not supported yet.");
        }
        
        
        
        
    }
}
