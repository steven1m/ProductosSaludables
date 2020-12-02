package Controlador;

import Modelo.CategoriaMateriaPrima;
import Modelo.CategoriaMateriaPrimaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.PanelCategoriaMateriaPrima;
/**
 *
 * @author Steven
 */
public class ControladorCategoriaMateriaPrima implements ActionListener{
    
    private final CategoriaMateriaPrimaDAO modeloCategoria;
    private final PanelCategoriaMateriaPrima panelCategotia;

    public ControladorCategoriaMateriaPrima(CategoriaMateriaPrimaDAO modeloCategoria,
            PanelCategoriaMateriaPrima panelCategotia) {
        this.modeloCategoria = modeloCategoria;
        this.panelCategotia = panelCategotia;
        setListeners();
        buscar(0);
    }
    
    private void setListeners(){
        
        this.panelCategotia.agregarListener(this);
    }
    
    private void agregar (CategoriaMateriaPrima categoria){
       int resultado = modeloCategoria.crear(categoria);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCategotia.operacionesCrud("");
           buscar(0);
       }
    }
   
    private void editar (CategoriaMateriaPrima categoria){
       int resultado = modeloCategoria.actualizar(categoria);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCategotia.operacionesCrud("");
           buscar(0);
       }
    }
    
    private void eliminar (int codigo){
        int resultado = this.modeloCategoria.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCategotia.operacionesCrud("");
           buscar(0);
       }
    }
    
    private void buscar (int id){
        ArrayList<CategoriaMateriaPrima> lista = this.modeloCategoria.leer(id);
        this.panelCategotia.cargarTabla(lista);
        this.panelCategotia.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregar(this.panelCategotia.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editar(this.panelCategotia.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                
                try{
                    eliminar(Integer.valueOf
                                    (this.panelCategotia.getCrudCodigo()));
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "error " + ex.getMessage());
                
                }
             }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscar(0);
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
                try{
                    buscar(Integer.valueOf
                                    (this.panelCategotia.getCrudCodigo()));
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "error " + ex.getMessage());
                
                }
               
            }
           
        }
}
