package Controlador;

import Modelo.PedidoDAO;
import Modelo.Pedido;
import vista.PanelPedido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class ControladorPedido implements ActionListener{
    
    private final PedidoDAO modeloPedido;
    private final PanelPedido panelPedido;

    public ControladorPedido(PedidoDAO modeloPedido, PanelPedido panelPedido) {
        this.modeloPedido = modeloPedido;
        this.panelPedido = panelPedido;
        setListeners();
        buscar("", "");
    }
    
    private void setListeners(){
        
        this.panelPedido.agregarListener(this);
    }
    
    private void agregar (Pedido pedido){
       int resultado = modeloPedido.crear(pedido);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelPedido.operacionesCrud("");
           buscar("", "");
       }
    }
   
    private void editar (Pedido pedido){
       int resultado = modeloPedido.actualizar(pedido);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelPedido.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void eliminar (int codigo){
        int resultado = this.modeloPedido.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelPedido.operacionesCrud("");
           buscar("", "");
       }
    }
    
    private void buscar (String clave, String valor){
        ArrayList<Pedido> lista = this.modeloPedido.leer(clave, valor);
        this.panelPedido.cargarTabla(lista);
        this.panelPedido.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregar(this.panelPedido.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editar(this.panelPedido.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminar(Integer.valueOf(this.panelPedido.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscar("", "");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.panelPedido.datosBuscar();
                buscar(datosBuscar[0], datosBuscar[1]);
               
            }
           
        }
}
