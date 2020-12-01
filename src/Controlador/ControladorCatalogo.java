package Controlador;

import Modelo.Catalogo;
import Modelo.CatalogoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.PanelCatalogo;
/**
 *
 * @author Steven
 */
public class ControladorCatalogo implements ActionListener{
    
    private final CatalogoDAO modeloCatalogo;
    private final PanelCatalogo panelCatalogo;

    public ControladorCatalogo(CatalogoDAO modeloCatalogo, PanelCatalogo panelCatalogo) {
        this.modeloCatalogo = modeloCatalogo;
        this.panelCatalogo = panelCatalogo;
        setListeners();
        buscarCatalogo("", "");
    }
    
    private void setListeners(){
        
        this.panelCatalogo.agregarListener(this);
    }
    
    private void agregarCatalogo (Catalogo catalogo){
       int resultado = modeloCatalogo.crear(catalogo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCatalogo.operacionesCrud("");
           buscarCatalogo("", "");
       }
    }
   
    private void editarCatalogo (Catalogo catalogo){
       int resultado = modeloCatalogo.actualizar(catalogo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCatalogo.operacionesCrud("");
           buscarCatalogo("", "");
       }
    }
    
    private void eliminarCatalogo (int codigo){
        int resultado = this.modeloCatalogo.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCatalogo.operacionesCrud("");
           buscarCatalogo("", "");
       }
    }
    
    private void buscarCatalogo (String clave, String valor){
        ArrayList<Catalogo> lista = this.modeloCatalogo.leer(clave, valor);
        this.panelCatalogo.cargarTablaCatalogo(lista);
        this.panelCatalogo.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregarCatalogo(this.panelCatalogo.crearObjetoCatalogo());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editarCatalogo(this.panelCatalogo.crearObjetoCatalogo());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                
                try{
                    eliminarCatalogo(Integer.valueOf
                                    (this.panelCatalogo.getCrudCodigo()));
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "error " + ex.getMessage());
                
                }
                
                
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscarCatalogo("", "");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.panelCatalogo.datosBuscar();
                buscarCatalogo(datosBuscar[0], datosBuscar[1]);
               
            }
           
        }
}
