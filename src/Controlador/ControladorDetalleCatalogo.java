package Controlador;

import Modelo.DetalleCatalogo;
import Modelo.DetalleCatalogoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.PanelDetalleCatalogo;
/**
 *
 * @author Steven
 */
public class ControladorDetalleCatalogo implements ActionListener{
    
    private final DetalleCatalogoDAO modeloDetalleCatalogo;
    private final PanelDetalleCatalogo panelDetalleCatalogo;

    public ControladorDetalleCatalogo(DetalleCatalogoDAO modeloDetalleCatalogo, 
                                        PanelDetalleCatalogo panelDetalleCatalogo) {
        
        this.modeloDetalleCatalogo = modeloDetalleCatalogo;
        this.panelDetalleCatalogo = panelDetalleCatalogo;
        setListeners();
        buscar("", "");
    }
    
    private void setListeners(){
        
        this.panelDetalleCatalogo.agregarListener(this);
    }
    
    private void agregar (DetalleCatalogo detalle){
       int resultado = modeloDetalleCatalogo.crear(detalle);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelDetalleCatalogo.operacionesCrud("");
           buscar("", "");
       }
    }
   
    private void editar (DetalleCatalogo detalle){
       int resultado = modeloDetalleCatalogo.actualizar(detalle);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelDetalleCatalogo.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void eliminar (int codigo){
        int resultado = this.modeloDetalleCatalogo.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelDetalleCatalogo.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void buscar (String clave, String valor){
        ArrayList<DetalleCatalogo> lista = this.modeloDetalleCatalogo.leer(clave, valor);
        this.panelDetalleCatalogo.cargarTabla(lista);
        this.panelDetalleCatalogo.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregar(this.panelDetalleCatalogo.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editar(this.panelDetalleCatalogo.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminar(Integer.valueOf(this.panelDetalleCatalogo.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscar("", "");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.panelDetalleCatalogo.datosBuscar();
                buscar(datosBuscar[0], datosBuscar[1]);
               
            }
           
        }
}
