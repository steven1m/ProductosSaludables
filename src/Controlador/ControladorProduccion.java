package Controlador;
import Modelo.Produccion;
import Modelo.ProduccionDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.PanelProduccion;
/**
 *
 * @author Steven
 */
public class ControladorProduccion {
    private ProduccionDAO modeloProduccion;
    private PanelProduccion vistaProduccion;

    public ControladorProduccion(ProduccionDAO modeloProduccion, PanelProduccion vistaProduccion) {
        this.modeloProduccion = modeloProduccion;
        this.vistaProduccion = vistaProduccion;
        vistaProduccion.agregarlistenerAgregar(new ListenerProduccion());
        vistaProduccion.agregarlistenerAplicar(new ListenerProduccion());
        vistaProduccion.agregarlistenerLeer(new ListenerProduccion());
    }
    
    class ListenerProduccion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equalsIgnoreCase("Agregar") ){
                vistaProduccion.nuevaAccion();
            }else if (e.getActionCommand().equalsIgnoreCase("Aplicar")){
                JOptionPane.showMessageDialog(null, "se van a introducir los datos.");
                crear();
            }else if (e.getActionCommand().equalsIgnoreCase("Leer")){
                JOptionPane.showMessageDialog(null, "se van a cargar los datos.");
                leer();
            }
        }
        
        private void crear(){
            Produccion produccion = new Produccion();
            produccion.setId(vistaProduccion.getID());
            produccion.setNombre(vistaProduccion.getNombre());
            produccion.setCosto(vistaProduccion.getCosto());
            produccion.setProductoId(vistaProduccion.getIdProducto());

            int resultado = 0;
            resultado = modeloProduccion.crear(produccion);
            if(resultado == 1){ 
                JOptionPane.showMessageDialog(null, "Produccion Grabada! " +resultado);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se Grabó! " +resultado);
            }
        }
        
        private void leer(){
            ArrayList<Produccion> listadoProduccion;
            listadoProduccion = modeloProduccion.leer(0);
            vistaProduccion.cargarProduccion(listadoProduccion);
        }
        
        
        
    }
    
}
