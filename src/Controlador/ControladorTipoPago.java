package Controlador;

import Modelo.TipoPago;
import Modelo.TipoPagoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.PanelTipoPago;
/**
 *
 * @author Steven
 */
public class ControladorTipoPago implements ActionListener{
    
    private final TipoPagoDAO modeloTipoPago;
    private final PanelTipoPago panelTipoPago;

    public ControladorTipoPago(TipoPagoDAO modeloTipoPago, PanelTipoPago panelTipoPago) {
        this.modeloTipoPago = modeloTipoPago;
        this.panelTipoPago = panelTipoPago;
        setListeners();
        buscarProducto();
    }
    
    private void setListeners(){
        
        this.panelTipoPago.agregarListener(this);
    }
    
    private void agregarProducto (TipoPago tipoPago){
       int resultado = modeloTipoPago.crear(tipoPago);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelTipoPago.operacionesCrud("");
           buscarProducto();
       }
    }
   
    private void editarProducto (TipoPago tipoPago){
       int resultado = modeloTipoPago.actualizar(tipoPago);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelTipoPago.operacionesCrud("");
           buscarProducto();
       }
    }
    
    private void eliminarProducto (int codigo){
        int resultado = this.modeloTipoPago.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelTipoPago.operacionesCrud("");
           buscarProducto();
       }
    }
    
    private void buscarProducto (){
        try{
            int id =0;
            if (!"".equals(this.panelTipoPago.getCrudCodigo())){
                id = Integer.parseInt(this.panelTipoPago.getCrudCodigo());
            }
            ArrayList<TipoPago> lista = this.modeloTipoPago.leer(id);
            this.panelTipoPago.cargarTablaProductos(lista);
            this.panelTipoPago.operacionesCrud("");
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Error");
        }
        
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregarProducto(this.panelTipoPago.crearObjetoProducto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editarProducto(this.panelTipoPago.crearObjetoProducto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminarProducto(Integer.valueOf(this.panelTipoPago.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscarProducto();
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               buscarProducto();
               
            }
           
        }
}
