package Controlador;

import Modelo.PagoDAO;
import Modelo.Pago;
import vista.PanelPagos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class ControladorPago implements ActionListener{
    
    private final PagoDAO modeloPago;
    private final PanelPagos panelPago;

    public ControladorPago(PagoDAO modeloPago, PanelPagos panelPago) {
        this.modeloPago = modeloPago;
        this.panelPago = panelPago;
        setListeners();
        buscarPago("", "");
    }
    
    private void setListeners(){
        
        this.panelPago.agregarListener(this);
    }
    
    private void agregarPago (Pago pago){
       int resultado = modeloPago.crear(pago);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelPago.operacionesCrud("");
           buscarPago("", "");
       }
    }
   
    private void editarPago (Pago pago){
       int resultado = modeloPago.actualizar(pago);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelPago.operacionesCrud("");
           buscarPago("", "");
       }
    }
    
    private void eliminarPago (int codigo){
        int resultado = this.modeloPago.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelPago.operacionesCrud("");
           buscarPago("", "");
       }
    }
    
    private void buscarPago (String clave, String valor){
        ArrayList<Pago> lista = this.modeloPago.leer(clave, valor);
        this.panelPago.cargarTabla(lista);
        this.panelPago.operacionesCrud("");
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregarPago(this.panelPago.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editarPago(this.panelPago.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminarPago(Integer.valueOf(this.panelPago.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscarPago("", "");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.panelPago.datosBuscar();
                buscarPago(datosBuscar[0], datosBuscar[1]);
               
            }
           
        }
}
