package Modelo;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClienteChat extends JFrame implements ActionListener{

    private JTextField campoIntroducir; // introduce la información del usuario
    private JTextArea areaPantalla; // muestra la información al usuario
    private ObjectOutputStream salida; // flujo de salida hacia el servidor
    private ObjectInputStream entrada; // flujo de entrada del servidor
    private String mensaje = ""; // mensaje del servidor
    private String servidorChat; // aloja al servidor para esta aplicación
    private Socket cliente;
    //private FileWriter archivo;
    
    // inicializa el objeto servidorChat y establece la GUI
    public ClienteChat( String host ) throws IOException
    {
        super( "ClienteChat" );
        servidorChat = host; // establece el servidor al que se conecta este cliente
        campoIntroducir = new JTextField(); // crea objeto campoIntroducir
        campoIntroducir.setEditable( false );
        campoIntroducir.addActionListener(this);
        add( campoIntroducir, BorderLayout.NORTH );
        areaPantalla = new JTextArea(); // crea objeto areaPantalla
        add( new JScrollPane( areaPantalla ), BorderLayout.CENTER );
        //archivo = new FileWriter("src/Archivos/chat-c.txt",true);

        setSize( 300, 150 ); 
        setVisible( true ); 
    }
    
    // se conecta al servidor, obtiene flujos, procesa la conexión
    public void ejecutarClienteChat()
    {
        try 
        {   
            conectarAlServidor(); // crea un objeto Socket para hacer la conexión
            obtenerFlujos(); // obtiene los flujos de entrada y salida
            procesarConexion(); // procesa la conexión
        }catch ( EOFException excepcionEOF ){
            mostrarMensaje( "\nClienteChat termino la conexion" );
        } // fin de catch
        catch ( IOException excepcionES ){
            excepcionES.printStackTrace();
        } // fin de catch
        finally {
            cerrarConexion(); // cierra la conexión
        } // fin de finally
    } // fin del método ejecutarClienteChat
            
           
    // se conecta al servidor
    private void conectarAlServidor() throws IOException {
        mostrarMensaje( "Intentando realizar conexion\n" );
        
        // crea objeto Socket para hacer conexión con el servidor
        cliente = new Socket( InetAddress.getByName( servidorChat ), 12345 );
        // muestra la información de la conexión
        mostrarMensaje( "Conectado a: " + cliente.getInetAddress().getHostName() );
    } // fin del método conectarAlServidor
       
    
    // obtiene flujos para enviar y recibir datos
    private void obtenerFlujos() throws IOException {
        // establece flujo de salida para los objetos 
        salida = new ObjectOutputStream( cliente.getOutputStream() );
        salida.flush(); // vacía el búfer de salida para enviar información de encabezado
        // establece flujo de entrada para los objetos 
        entrada = new ObjectInputStream( cliente.getInputStream() );
        mostrarMensaje( "\nSe obtuvieron los flujos de E/S\n" );
    } // fin del método obtenerFlujos
    
    // procesa la conexión con el servidor
    private void procesarConexion() throws IOException  {
        // habilita campoIntroducir para que el usuario cliente pueda enviar mensajes
        campoIntroducir.setEditable(true );
        do // procesa los mensajes que se envían desde el servidor
        {
            try // lee el mensaje y lo muestra
            {
                FileWriter archivo = new FileWriter("src/Archivo/chat-s.txt",true);
                
                mensaje = ( String ) entrada.readObject(); // lee nuevo mensaje
                
                PrintWriter pw = new PrintWriter(archivo);
                
                pw.println(mensaje); // Escribir mensaje 
                
                if (archivo!=null){
                    archivo.close();
                }else {
                    JOptionPane.showMessageDialog(null, "archivo no existe");
                }
                
                mostrarMensaje( "\n" + mensaje ); // muestra el mensaje
            } catch ( ClassNotFoundException excepcionClaseNoEncontrada ){
                mostrarMensaje( "nSe recibio un tipo de objeto desconocido" );
            }
        } while ( !mensaje.equals( "SERVIDOR>>> TERMINAR" ) );
    } // fin del método procesarConexion 
                
   // cierra flujos y socket
    private void cerrarConexion() {
        mostrarMensaje( "\nCerrando conexion" );
        campoIntroducir.setEditable( false ); // deshabilita campoIntroducir
        try {
            salida.close(); // cierra el flujo de salida
            entrada.close(); // cierra el flujo de entrada 1
            cliente.close(); // cierra el socket
        } catch ( IOException excepcionES ){
            excepcionES.printStackTrace();
        } // fin de catch
    } // fin del método cerrarConexion             
         
    private void enviarDatos( String mensaje ) {
        try // envía un objeto al servidor
        {
            salida.writeObject( "CLIENTE>>> " + mensaje );
            salida.flush(); // envía todos los datos a la salida
            mostrarMensaje( "\nCLIENTE>>> " + mensaje );
        } catch ( IOException excepcionES ) {
            areaPantalla.append( "\nError al escribir objeto" );
        } // fin de catch
    } // fin del método enviarDatos
    
     private void mostrarMensaje( final String mensajeAMostrar ) {
         areaPantalla.append( mensajeAMostrar ); // adjunta el mensaje
     } // fin del método mostrarMensaje
 

    @Override
    public void actionPerformed(ActionEvent ae) {
        String m= campoIntroducir.getText();
        //enviarDatos( ae.getActionCommand()); 
         enviarDatos(m );       
        campoIntroducir.setText( "" );
        
    }
    
     public static void main(String[] args) throws IOException {
        ClienteChat aplicacion; // declara la aplicación cliente
        // si no hay argumentos de línea de comandos
        if ( args.length == 0 )
            aplicacion = new ClienteChat( "127.0.0.1" ); // se conecta a localhost
        else
            aplicacion = new ClienteChat( args[ 0 ] ); // usa args para conectarse
        
        aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        aplicacion.ejecutarClienteChat(); // ejecuta la aplicación cliente
    }
}
