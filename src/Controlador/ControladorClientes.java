package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.PanelCliente;
/**
 *
 * @author Steven
 */
public class ControladorClientes implements ActionListener{
    
    private final ClienteDAO modeloCliente;
    private final PanelCliente panelCliente;

    public ControladorClientes(ClienteDAO modeloCliente, PanelCliente panelCliente) {
        this.modeloCliente = modeloCliente;
        this.panelCliente = panelCliente;
        setListeners();
        buscar("","");
    }
    
    private void setListeners(){
        
        this.panelCliente.agregarListener(this);
    }
    
    private void agregar(Cliente cliente){
       int resultado = modeloCliente.crear(cliente);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCliente.operacionesCrud("");
            buscar("","");
       }
    }
   
    private void editar (Cliente cliente){
       int resultado = modeloCliente.actualizar(cliente);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCliente.operacionesCrud("");
          buscar("","");
       }
    }
    
    private void eliminar (int codigo){
        int resultado = this.modeloCliente.borrar(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCliente.operacionesCrud("");
            buscar("","");
       }
    }
    
    private void buscar (String clave, String valor){
        ArrayList<Cliente> lista = this.modeloCliente.leer(clave, valor);
        this.panelCliente.cargarTabla(lista);
        this.panelCliente.operacionesCrud("");
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregar(this.panelCliente.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editar(this.panelCliente.crearObjeto());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminar(Integer.valueOf(this.panelCliente.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               buscar("","");
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.panelCliente.datosBuscar();
                buscar(datosBuscar[0], datosBuscar[1]);
               
            }
           
        }
}
