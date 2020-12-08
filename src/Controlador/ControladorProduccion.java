package Controlador;

import Modelo.Produccion;
import Modelo.ProduccionDAO;
import vista.PanelProduccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class ControladorProduccion implements ActionListener{
    
    private final ProduccionDAO modeloProduccion;
    private final PanelProduccion vistaProduccion;

    public ControladorProduccion(ProduccionDAO modeloProduccion, PanelProduccion vistaProduccion) {
        this.modeloProduccion = modeloProduccion;
        this.vistaProduccion = vistaProduccion;
        
        this.vistaProduccion.agregarlistenerAgregar(this);
        vistaProduccion.agregarlistenerAplicar(this);
        vistaProduccion.agregarlistenerLeer(this);
        buscarProduccion("", "");
    }
    
    private void agregarProduccion (Produccion produccion){
        int resultado = modeloProduccion.crear(produccion);
        if (resultado != 0){
            JOptionPane.showMessageDialog(null, "Operacion Exitosa");
            this.vistaProduccion.operacionesCrud("");
            buscarProduccion("", "");
        }
    }
    
    private void buscarProduccion (String clave, String valor){
        ArrayList<Produccion> lista = modeloProduccion.leer(clave, valor);
        vistaProduccion.cargarTablaProduccion(lista);
        vistaProduccion.operacionesCrud("");
    }
    
    private void editarProduccion (Produccion producto){
            int resultado = modeloProduccion.actualizar(producto);
            if (resultado != 0){
                JOptionPane.showMessageDialog(null, "Operacion Exitosa");
                vistaProduccion.operacionesCrud("");
                buscarProduccion("", "");
            }
        }

    private void eliminarProduccion (int codigo){
        int resultado = modeloProduccion.borrar(codigo);
        if (resultado != 0){
            JOptionPane.showMessageDialog(null, "Operacion Exitosa");
            vistaProduccion.operacionesCrud("");
            buscarProduccion("", "");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("Agregar") ){

            agregarProduccion(this.vistaProduccion.crearObjetoProduccion());

        }else if (e.getActionCommand().equalsIgnoreCase("Editar")){
            editarProduccion(vistaProduccion.crearObjetoProduccion());

        }else if (e.getActionCommand().equalsIgnoreCase("Eliminar")){
            eliminarProduccion(Integer.valueOf(vistaProduccion.getID()));

        }else if (e.getActionCommand().equalsIgnoreCase("Actualizar")){
           buscarProduccion("", "");

        }else if (e.getActionCommand().equalsIgnoreCase("Buscar")){

           String[] datosBuscar = vistaProduccion.datosBuscar();
            buscarProduccion(datosBuscar[0], datosBuscar[1]);

        }
    }
}
