package Controlador;

import Modelo.MateriaPrimaDAO;
import Modelo.MateriaPrima;
import vista.PanelMateriaPrima;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class ControladorMateriaPrima implements ActionListener{
    
    private final MateriaPrimaDAO modeloMateriaPrima;
    private final PanelMateriaPrima panelMateriaPrima;

    public ControladorMateriaPrima(MateriaPrimaDAO modeloMateriaPrima, PanelMateriaPrima panelMateriaPrima) {
        this.modeloMateriaPrima = modeloMateriaPrima;
        this.panelMateriaPrima = panelMateriaPrima;
        setListeners();
        buscar("", "");
    }
    
    private void setListeners(){
        
        this.panelMateriaPrima.agregarListener(this);
    }
    
    private void agregar (MateriaPrima materiaPrima){
       int resultado = modeloMateriaPrima.crear(materiaPrima);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelMateriaPrima.operacionesCrud("");
           buscar("", "");
       }
    }
   
    private void editar (MateriaPrima materiaPrima){
       int resultado = modeloMateriaPrima.actualizar(materiaPrima);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelMateriaPrima.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void eliminar (int codigo){
        int resultado = this.modeloMateriaPrima.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelMateriaPrima.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void buscar (String clave, String valor){
        ArrayList<MateriaPrima> lista = this.modeloMateriaPrima.leer(clave, valor);
        this.panelMateriaPrima.cargarTabla(lista);
        this.panelMateriaPrima.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregar(this.panelMateriaPrima.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editar(this.panelMateriaPrima.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminar(Integer.valueOf(this.panelMateriaPrima.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscar("", "");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.panelMateriaPrima.datosBuscar();
                buscar(datosBuscar[0], datosBuscar[1]);
               
            }
           
        }
}
