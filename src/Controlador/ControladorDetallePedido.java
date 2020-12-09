package Controlador;

import Modelo.DetallePedidoDAO;
import Modelo.DetallePedido;
import vista.PanelDetallePedido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class ControladorDetallePedido implements ActionListener{
    
    private final DetallePedidoDAO modeloDetallePedido;
    private final PanelDetallePedido panelDetallePedido;

    public ControladorDetallePedido(DetallePedidoDAO modeloDetallePedido, PanelDetallePedido panelDetallePedido) {
        this.modeloDetallePedido = modeloDetallePedido;
        this.panelDetallePedido = panelDetallePedido;
        setListeners();
        buscar("", "");
    }
    
    private void setListeners(){
        
        this.panelDetallePedido.agregarListener(this);
    }
    
    private void agregar (DetallePedido detallePedido){
       int resultado = modeloDetallePedido.crear(detallePedido);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelDetallePedido.operacionesCrud("");
           buscar("", "");
       }
    }
   
    private void editar (DetallePedido detallePedido){
       int resultado = modeloDetallePedido.actualizar(detallePedido);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelDetallePedido.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void eliminar (int codigo){
        int resultado = this.modeloDetallePedido.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelDetallePedido.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void buscar (String clave, String valor){
        ArrayList<DetallePedido> lista = this.modeloDetallePedido.leer(clave, valor);
        this.panelDetallePedido.cargarTabla(lista);
        this.panelDetallePedido.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregar(this.panelDetallePedido.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editar(this.panelDetallePedido.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminar(Integer.valueOf(this.panelDetallePedido.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscar("", "");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.panelDetallePedido.datosBuscar();
                buscar(datosBuscar[0], datosBuscar[1]);
               
            }
           
        }
}
