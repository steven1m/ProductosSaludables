package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.PanelCliente;
/**
 *
 * @author Steven
 */
public class ControladorClientes implements ActionListener{
    
    private ClienteDAO modeloCliente;
    private PanelCliente panelCliente;

    public ControladorClientes(ClienteDAO modeloCliente, PanelCliente panelCliente) {
        this.modeloCliente = modeloCliente;
        this.panelCliente = panelCliente;
        setListeners();
        
    }
    
    private void setListeners(){
        
        this.panelCliente.agregarListener(this);
    }
    
    private void agregarCliente(Cliente cliente){
       int resultado = modeloCliente.crearCliente(cliente);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCliente.operacionesCrud("");
           
       }
    }
   
    private void editarCliente (Cliente cliente){
       int resultado = modeloCliente.actualizarCliente(cliente);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCliente.operacionesCrud("");
         
       }
    }
    
    private void eliminarCliente (int codigo){
        int resultado = this.modeloCliente.borrarCliente(codigo);
       if (resultado != 0){
           JOptionPane.showMessageDialog(null, "Operacion Exitosa");
           this.panelCliente.operacionesCrud("");
           
       }
    }
    
    private void buscarCliente (String clave, String valor){
//        ArrayList<Producto> lista = this.modeloCliente.leer(clave, valor);
//        this.paneCliente.cargarTablaClientes(lista);
//        this.paneCliente.operacionesCrud("");
    }
    
    @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                
                agregarCliente(this.panelCliente.crearObjetoCliente());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
                editarCliente(this.panelCliente.crearObjetoCliente());
               
            }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
                eliminarCliente(Integer.valueOf(this.panelCliente.getCrudCodigo()));
               
            }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
               
               
            }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){
                
               String[] datosBuscar = this.panelCliente.datosBuscar();
              
               
            }
           
        }
}
