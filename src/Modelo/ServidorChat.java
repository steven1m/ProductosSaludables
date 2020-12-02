package Modelo;

 import java.io.EOFException;
 import java.io.IOException;
 import java.io.ObjectInputStream;
 import java.io.ObjectOutputStream;
 import java.net.ServerSocket;
 import java.net.Socket;
 import java.awt.BorderLayout;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
 import javax.swing.JFrame;
import javax.swing.JOptionPane;
 import javax.swing.JScrollPane;
 import javax.swing.JTextArea;
 import javax.swing.JTextField;
 
 public class ServidorChat extends JFrame implements ActionListener
 {
    private JTextField campoIntroducir; // recibe como entrada un mensaje del usuario
    private JTextArea areaPantalla; // muestra información al usuario
    private ObjectOutputStream salida; // flujo de salida hacia el cliente
    private ObjectInputStream entrada; // flujo de entrada del cliente
    private ServerSocket servidor; // socket servidor
    private Socket conexion; // conexión al cliente
    private int contador = 1; // contador del número de conexiones
    //private FileWriter archivo;


    // establece la GUI
    public ServidorChat() throws IOException
    {
        super( "Servidor" );

        campoIntroducir = new JTextField(); // crea objeto campoIntroducir
        campoIntroducir.setEditable( false );
        campoIntroducir.addActionListener( this);
        add( campoIntroducir, BorderLayout.NORTH );
        areaPantalla = new JTextArea(); // crea objeto areaPantalla
        add( new JScrollPane( areaPantalla ), BorderLayout.CENTER );
        //this.archivo = new FileWriter("src/Archivos/chat-s.txt");

        setSize( 300, 150 ); 
        setVisible( true ); 
  } 
 
    
    public void ejecutarServidor()
    {
        try // establece el servidor para que reciba conexiones; procesa las conexiones
        {
            servidor = new ServerSocket( 12345, 100 ); // crea objeto ServerSocket
            while ( true ) {
                try
                {
                    esperarConexion(); // espera una conexión
                    obtenerFlujos(); // obtiene los flujos de entrada y salida
                    procesarConexion(); // procesa la conexión
                } catch ( EOFException excepcionEOF ){
                    mostrarMensaje( "\nServidor termino la conexion" );
                } // fin de catch
                finally {
                    cerrarConexion(); // cierra la conexión
                    contador++;
                } // fin de finally
             } // fin de while
       } catch ( IOException exepcionES ){
            exepcionES.printStackTrace();
       } // fin de catch
     } // fin del método ejecutarServidor
 
    
    // espera a que llegue una conexión, después muestra información sobre ésta
    private void esperarConexion() throws IOException
    {
        mostrarMensaje( "Esperando una conexion\n" );
        conexion = servidor.accept(); // permite al servidor aceptar la conexión
        mostrarMensaje( "Conexion " + contador + " recibida de: " + conexion.getInetAddress().getHostName() );
    } // fin del método esperarConexion

    // obtiene flujos para enviar y recibir datos
    private void obtenerFlujos() throws IOException
    {
        // establece el flujo de salida para los objetos
        salida = new ObjectOutputStream( conexion.getOutputStream() );
        salida.flush(); // vacía el búfer de salida para enviar información del encabezado

        // establece el flujo de entrada para los objetos
        entrada = new ObjectInputStream( conexion.getInputStream() );
        mostrarMensaje( "\nSe obtuvieron los flujos de E/S\n" );
    } // fin del método obtenerFlujos
 
 
 // manipula areaPantalla en el subproceso despachador de eventos
 private void mostrarMensaje( final String mensajeAMostrar )
 {
   
            areaPantalla.append( mensajeAMostrar ); // adjunta el mensaje
   
 } // fin del método mostrarMensaje
 
 
 // procesa la conexión con el cliente
    private void procesarConexion() throws IOException
    {
        
        String mensaje = "Conexion exitosa";
        enviarDatos( mensaje ); // envía mensaje de conexión exitosa
        // habilita campoIntroducir para que el usuario del servidor pueda enviar mensajes
        campoIntroducir.setEditable(true );
        do // procesa los mensajes enviados desde el cliente
        {
            try // lee el mensaje y lo muestra en pantalla
            {
                FileWriter archivo = new FileWriter("src/Archivos/chat-s.txt");
                PrintWriter pw = new PrintWriter(archivo);
                mensaje = ( String ) entrada.readObject(); // lee el nuevo mensaje
                
                
                
                pw.println("servidor: "+mensaje); // Escribir mensaje 
                
                if (archivo!=null){
                    archivo.close();
                }else {
                    JOptionPane.showMessageDialog(null, "archivo no existe");
                }
                
                mostrarMensaje( "\n" + mensaje ); // muestra el mensaje
            }catch ( ClassNotFoundException excepcionClaseNoEncontrada ) {
                mostrarMensaje( "\nSe recibio un tipo de objeto desconocido" );
            }catch (IOException e2){
                e2.printStackTrace();
            }
        } while ( !mensaje.equals( "CLIENTE>>> TERMINAR" ) );
    } // fin del método procesarConexion

    // cierra flujos y socket
    private void cerrarConexion()
    {   mostrarMensaje( "\nTerminando conexion\n" );
        campoIntroducir.setEditable(false ); // deshabilita campoIntroducir
        try
        {
            salida.close(); // cierra flujo de salida
            entrada.close(); // cierra flujo de entrada
            conexion.close(); // cierra el socket
        } catch ( IOException exepcionES ){
            exepcionES.printStackTrace();
        } // fin de catch
    } // fin del método cerrarConexion// fin de try
    
    // envía el mensaje al cliente
     private void enviarDatos( String mensaje )
     {
        try
        {
            salida.writeObject( "SERVIDOR>>> " + mensaje );
            
            //PrintWriter pw = new PrintWriter(this.archivo);
                
            //pw.println("servidor: "+mensaje); // Escribir mensaje 
            
            
            salida.flush(); // envía toda la salida al cliente
            mostrarMensaje( "\nSERVIDOR>>> " + mensaje );
        }catch ( IOException exepcionES ){
             areaPantalla.append( "\nError al escribir objeto" );
        } // fin de catch
     } // fin del método enviarDatos
 
    @Override
    public void actionPerformed(ActionEvent ae) {
        String m= campoIntroducir.getText();
        //enviarDatos( ae.getActionCommand()); 
         enviarDatos(m ); 
        
        campoIntroducir.setText( "" );
    }
      
public static void main(String[] args) throws IOException {
        ServidorChat aplicacion = new ServidorChat(); // crea el servidor
        aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        aplicacion.ejecutarServidor(); // ejecuta la aplicación servidor
    }

   
}
