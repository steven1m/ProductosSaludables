package Controlador;

import Modelo.ListaIngredientesDAO;
import Modelo.ListaIngredientes;
import vista.PanelListaIngredientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class ControladorListaIngredientes implements ActionListener{
    
    private final ListaIngredientesDAO modeloListaIngredientes;
    private final PanelListaIngredientes panelListaIngredientes;

    public ControladorListaIngredientes(ListaIngredientesDAO modeloListaIngredientes, PanelListaIngredientes panelListaIngredientes) {
        this.modeloListaIngredientes = modeloListaIngredientes;
        this.panelListaIngredientes = panelListaIngredientes;
        setListeners();
        buscar("", "");
    }
    
    private void setListeners(){
        
        this.panelListaIngredientes.agregarListener(this);
    }
    
    private void agregar (ListaIngredientes listaIngredientes){
       int resultado = modeloListaIngredientes.crear(listaIngredientes);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelListaIngredientes.operacionesCrud("");
           buscar("", "");
       }
    }
   
    private void editar (ListaIngredientes listaIngredientes){
       int resultado = modeloListaIngredientes.actualizar(listaIngredientes);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelListaIngredientes.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void eliminar (int codigo){
        int resultado = this.modeloListaIngredientes.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelListaIngredientes.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void buscar (String clave, String valor){
        ArrayList<ListaIngredientes> lista = this.modeloListaIngredientes.leer(clave, valor);
        this.panelListaIngredientes.cargarTabla(lista);
        this.panelListaIngredientes.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregar(this.panelListaIngredientes.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editar(this.panelListaIngredientes.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminar(Integer.valueOf(this.panelListaIngredientes.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
                buscar("", "");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.panelListaIngredientes.datosBuscar();
                buscar(datosBuscar[0], datosBuscar[1]);
               
            }
           
        }
}
