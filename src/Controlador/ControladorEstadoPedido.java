package Controlador;

import Modelo.EstadoPedido;
import Modelo.EstadoPedidoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.PanelEstadoPedido;
/**
 *
 * @author Steven
 */
public class ControladorEstadoPedido implements ActionListener{
    
    private final EstadoPedidoDAO modeloEstadoPedido;
    private final PanelEstadoPedido panelEstadoPedido;

    public ControladorEstadoPedido(EstadoPedidoDAO modeloEstadoPedido,
            PanelEstadoPedido panelEstadoPedido) {
        this.modeloEstadoPedido = modeloEstadoPedido;
        this.panelEstadoPedido = panelEstadoPedido;
        setListeners();
        buscar(0);
    }
    
    private void setListeners(){
        
        this.panelEstadoPedido.agregarListener(this);
    }
    
    private void agregar (EstadoPedido estadoPedido){
       int resultado = modeloEstadoPedido.crear(estadoPedido);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelEstadoPedido.operacionesCrud("");
           buscar(0);
       }
    }
   
    private void editar (EstadoPedido estadoPedido){
       int resultado = modeloEstadoPedido.actualizar(estadoPedido);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelEstadoPedido.operacionesCrud("");
           buscar(0);
       }
    }
    
    private void eliminar (int codigo){
        int resultado = this.modeloEstadoPedido.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelEstadoPedido.operacionesCrud("");
           buscar(0);
       }
    }
    
    private void buscar (int id){
        ArrayList<EstadoPedido> lista = this.modeloEstadoPedido.leer(id);
        this.panelEstadoPedido.cargarTabla(lista);
        this.panelEstadoPedido.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregar(this.panelEstadoPedido.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editar(this.panelEstadoPedido.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                
                try{
                    eliminar(Integer.valueOf
                                    (this.panelEstadoPedido.getCrudCodigo()));
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "error " + ex.getMessage());
                
                }
             }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscar(0);
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
                try{
                    buscar(Integer.valueOf
                                    (this.panelEstadoPedido.getCrudCodigo()));
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "error " + ex.getMessage());
                
                }
               
            }
           
        }
}
