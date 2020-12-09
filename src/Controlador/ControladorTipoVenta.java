package Controlador;

import Modelo.TipoVenta;
import Modelo.TipoVentaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.PanelTipoVenta;
/**
 *
 * @author Steven
 */
public class ControladorTipoVenta implements ActionListener{
    
    private final TipoVentaDAO modeloTipoVenta;
    private final PanelTipoVenta panelTipoVenta;

    public ControladorTipoVenta(TipoVentaDAO modeloTipoVenta, PanelTipoVenta panelTipoVenta) {
        this.modeloTipoVenta = modeloTipoVenta;
        this.panelTipoVenta = panelTipoVenta;
        setListeners();
        buscar();
    }
    
    private void setListeners(){
        
        this.panelTipoVenta.agregarListener(this);
    }
    
    private void agregar (TipoVenta tipoVenta){
       int resultado = modeloTipoVenta.crear(tipoVenta);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelTipoVenta.operacionesCrud("");
           buscar();
       }
    }
   
    private void editar (TipoVenta tipoVenta){
       int resultado = modeloTipoVenta.actualizar(tipoVenta);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelTipoVenta.operacionesCrud("");
           buscar();
       }
    }
    
    private void borrar (int codigo){
        int resultado = this.modeloTipoVenta.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelTipoVenta.operacionesCrud("");
           buscar();
       }
    }
    
    private void buscar (){
        try{
            int id =0;
            if (!"".equals(this.panelTipoVenta.getCrudCodigo())){
                id = Integer.parseInt(this.panelTipoVenta.getCrudCodigo());
            }
            ArrayList<TipoVenta> lista = this.modeloTipoVenta.leer(id);
            this.panelTipoVenta.cargarTabla(lista);
            this.panelTipoVenta.operacionesCrud("");
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Error");
        }
        
       
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregar(this.panelTipoVenta.crearObjetoProducto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editar(this.panelTipoVenta.crearObjetoProducto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                borrar(Integer.valueOf(this.panelTipoVenta.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscar();
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               buscar();
               
            }
           
        }
}
