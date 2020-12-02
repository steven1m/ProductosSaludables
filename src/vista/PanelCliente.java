
package vista;

import Modelo.Cliente;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Universidad
 */
public class PanelCliente extends javax.swing.JFrame {

    private final  String[] datosCliente;
    
    public PanelCliente() {
        this.datosCliente = new String[7];
        initComponents();
        iniciarVentana();
    }
    
    private void iniciarVentana(){
        this.setLocationRelativeTo(null);
        this.setTitle("Control de Clientes");
        operacionesCrud("");
        selecionTabla(); 
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void agregarListener( ActionListener listener){
        
        this.btnActualizar.addActionListener(listener);
        this.btnCrudAplicar.addActionListener(listener);
        this.btnBuscar.addActionListener(listener);
    }
    
    public String getCrudCodigo (){
       return this.txtCrudCodigo.getText();
    }
    
   
    
    public Cliente crearObjeto(){
        Cliente cliente = new Cliente();
        try{
            cliente.setId(this.txtCrudCodigo.getText());
            cliente.setNombre(this.txtCrudNombre.getText());
            cliente.setApellido(this.txtCrudApellido.getText());
            cliente.setTelefono(this.txtCrudTelefono.getText());
            cliente.setDireccion(this.txtCrudDireccion.getText());
            cliente.setCorreo(this.txtCrudCorreo.getText());
            cliente.setRazonSocial(this.txtCrudRazonSocial.getText());

        }catch (NumberFormatException ex ){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
       
        return cliente;
    }
    public void cargarTabla(ArrayList<Cliente> lista){
        
        DefaultTableModel dtmCliente =(DefaultTableModel)this.jTableClientes.getModel();
        // eliminar los datos que exinten en el modelo de la tabla antes de agregar los datos 
        if (dtmCliente.getRowCount() != 0)
            {
              int d = dtmCliente.getRowCount();
              for (int y = 0; y < d; y++)
                {
                  dtmCliente.removeRow(0);
                }
            }
        this.jTableClientes.setModel(dtmCliente);
        
        Iterator <Cliente> iterador = lista.iterator();
        
        while(iterador.hasNext()){
            Cliente cliente = iterador.next();
            dtmCliente.addRow(new Object[]
              {
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getCorreo(),
                cliente.getRazonSocial()
              });
        }
        this.jTableClientes.setModel(dtmCliente);
    }
    
    private void selecionTabla(){
        // agregar metodeo de escucha a la tabla 
        this.jTableClientes.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
           if (this.jTableClientes.getSelectedRow()!= -1){
               int fila  = jTableClientes.getSelectedRow();
               this.lblCodigoSelectCliente.setText(this.jTableClientes.getValueAt
                                               (fila, 0).toString());
               this.lblNombreSelectCliente.setText(this.jTableClientes.getValueAt
                                                (fila, 1).toString());
               
               
               
               for (int i = 0; i < 7; i++){
                   this.datosCliente[i] = 
                           this.jTableClientes.getValueAt(fila, i).toString();
               }
              
               this.btnCrudAplicar.setEnabled(false);
               this.btnCrudCancelar.setEnabled(false);
               this.btnCrudAplicar.setText("Aplicar");
            }
       });
    }
    
    public void operacionesCrud (String operacion){
        switch (operacion){
            
            case "Agregar" -> {
                this.txtCrudCodigo.setEnabled(true);
                this.txtCrudNombre.setEnabled(true);
                this.txtCrudApellido.setEnabled(true);
                this.txtCrudTelefono.setEnabled(true);
                this.txtCrudDireccion.setEnabled(true);
                this.txtCrudCorreo.setEnabled(true);
                this.txtCrudRazonSocial.setEnabled(true);
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            case "Editar" -> {
                this.txtCrudCodigo.setEnabled(false);
                this.txtCrudNombre.setEnabled(true);
                this.txtCrudApellido.setEnabled(true);
                this.txtCrudTelefono.setEnabled(true);
                this.txtCrudDireccion.setEnabled(true);
                this.txtCrudCorreo.setEnabled(true);
                this.txtCrudRazonSocial.setEnabled(true);
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            case "Eliminar" -> {
                this.txtCrudCodigo.setEnabled(false);
                this.txtCrudNombre.setEnabled(false);
                this.txtCrudApellido.setEnabled(false);
                this.txtCrudTelefono.setEnabled(false);
                this.txtCrudDireccion.setEnabled(false);
                this.txtCrudCorreo.setEnabled(false);
                this.txtCrudRazonSocial.setEnabled(false);
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            default -> {
                this.txtCrudCodigo.setText("");
                this.txtCrudNombre.setText("");
                this.txtCrudApellido.setText("");
                this.txtCrudTelefono.setText("");
                this.txtCrudDireccion.setText("");
                this.txtCrudCorreo.setText("");
                this.txtCrudRazonSocial.setText("");
                this.txtBuscar.setText("");
                
                this.lblNombreSelectCliente.setText("");
                this.lblCodigoSelectCliente.setText("");
                
                this.txtCrudCodigo.setEnabled(false);
                this.txtCrudNombre.setEnabled(false);
                this.txtCrudApellido.setEnabled(false);
                this.txtCrudTelefono.setEnabled(false);
                this.txtCrudDireccion.setEnabled(false);
                this.txtCrudCorreo.setEnabled(false);
                this.txtCrudRazonSocial.setEnabled(false);
                this.btnCrudAplicar.setEnabled(false);
                this.btnCrudCancelar.setEnabled(false);
                this.btnCrudAplicar.setText("Aplicar");
            }
        }
    }
    
    private void setearDatos(){
        this.txtCrudCodigo.setText(this.datosCliente[0]);
        this.txtCrudNombre.setText(this.datosCliente[1]);
        this.txtCrudApellido.setText(this.datosCliente[2]);
        this.txtCrudTelefono.setText(this.datosCliente[3]);
        this.txtCrudDireccion.setText(this.datosCliente[4]);
        this.txtCrudCorreo.setText(this.datosCliente[5]);
        this.txtCrudRazonSocial.setText(this.datosCliente[6]);
    }
    
    public String[] datosBuscar(){
        
        String[] datos = new String[2];
        String clave ;
        String valor = this.txtBuscar.getText();
        int op = this.cajaTipoBusquedad.getSelectedIndex();
        
        switch (op){
            case 0:
                    clave = "apellido";
                break;
                
            case 1:
                    clave = "nombre";
                break;
                
            default:
                    clave = "id";
                break;
        }
        
        datos[0] = clave;
        datos[1] = valor;
       
        return datos;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGeneral = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        panelProductoSelect = new javax.swing.JPanel();
        lblCodigoSelect = new javax.swing.JLabel();
        lblNombreSelect = new javax.swing.JLabel();
        lblCodigoSelectCliente = new javax.swing.JLabel();
        lblNombreSelectCliente = new javax.swing.JLabel();
        jPanelCrud = new javax.swing.JPanel();
        lblCrudCodigo = new javax.swing.JLabel();
        txtCrudCodigo = new javax.swing.JTextField();
        lblCrudNombre = new javax.swing.JLabel();
        txtCrudNombre = new javax.swing.JTextField();
        lblCrudCantidad = new javax.swing.JLabel();
        txtCrudCorreo = new javax.swing.JTextField();
        lblCrudlPrecio = new javax.swing.JLabel();
        txtCrudDireccion = new javax.swing.JTextField();
        lblCrudPrecioVenta = new javax.swing.JLabel();
        txtCrudTelefono = new javax.swing.JTextField();
        btnCrudAplicar = new javax.swing.JButton();
        btnCrudCancelar = new javax.swing.JButton();
        lblCrudDescripcion = new javax.swing.JLabel();
        txtCrudApellido = new javax.swing.JTextField();
        lblCrudPrecioVenta1 = new javax.swing.JLabel();
        txtCrudRazonSocial = new javax.swing.JTextField();
        panelBuscar = new javax.swing.JPanel();
        cajaTipoBusquedad = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelGeneral.setBackground(new java.awt.Color(203, 240, 220));
        panelGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Administrar Clientes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        btnEditar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jScrollPane2.setToolTipText("");

        jTableClientes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Apellido", "Telefono", "Dirección", "Correo", "Razon Social"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableClientes.setName("Inventario de zapatos"); // NOI18N
        jScrollPane2.setViewportView(jTableClientes);
        if (jTableClientes.getColumnModel().getColumnCount() > 0) {
            jTableClientes.getColumnModel().getColumn(0).setResizable(false);
            jTableClientes.getColumnModel().getColumn(1).setResizable(false);
            jTableClientes.getColumnModel().getColumn(2).setResizable(false);
            jTableClientes.getColumnModel().getColumn(3).setResizable(false);
            jTableClientes.getColumnModel().getColumn(4).setResizable(false);
            jTableClientes.getColumnModel().getColumn(5).setResizable(false);
            jTableClientes.getColumnModel().getColumn(6).setResizable(false);
        }
        jTableClientes.getAccessibleContext().setAccessibleName("");
        jTableClientes.getAccessibleContext().setAccessibleDescription("");

        panelProductoSelect.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Producto selecionado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        panelProductoSelect.setToolTipText("");

        lblCodigoSelect.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblCodigoSelect.setText("Código:");
        lblCodigoSelect.setToolTipText("");

        lblNombreSelect.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblNombreSelect.setText("Nombre:");
        lblNombreSelect.setToolTipText("");

        lblCodigoSelectCliente.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblCodigoSelectCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombreSelectCliente.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblNombreSelectCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelProductoSelectLayout = new javax.swing.GroupLayout(panelProductoSelect);
        panelProductoSelect.setLayout(panelProductoSelectLayout);
        panelProductoSelectLayout.setHorizontalGroup(
            panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductoSelectLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigoSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCodigoSelectCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNombreSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreSelectCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelProductoSelectLayout.setVerticalGroup(
            panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductoSelectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigoSelect)
                    .addGroup(panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblCodigoSelectCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreSelectCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreSelect, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCodigoSelectCliente.getAccessibleContext().setAccessibleDescription("");
        lblNombreSelectCliente.getAccessibleContext().setAccessibleDescription("");

        jPanelCrud.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel de Opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanelCrud.setToolTipText("");

        lblCrudCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudCodigo.setText("Código:");

        txtCrudCodigo.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudNombre.setText("Nombre:");

        txtCrudNombre.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudCantidad.setText("Dirección:");

        txtCrudCorreo.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudlPrecio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudlPrecio.setText("Telefono:");

        txtCrudDireccion.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudPrecioVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudPrecioVenta.setText("Correo:");

        txtCrudTelefono.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        btnCrudAplicar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnCrudAplicar.setText("Aplicar");

        btnCrudCancelar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnCrudCancelar.setText("Cancelar");
        btnCrudCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudCancelarActionPerformed(evt);
            }
        });

        lblCrudDescripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudDescripcion.setText("Apellido:");

        txtCrudApellido.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudPrecioVenta1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudPrecioVenta1.setText("Razon Social:");

        txtCrudRazonSocial.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanelCrudLayout = new javax.swing.GroupLayout(jPanelCrud);
        jPanelCrud.setLayout(jPanelCrudLayout);
        jPanelCrudLayout.setHorizontalGroup(
            jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCrudDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudlPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCrudTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudPrecioVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCrudCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCrudDescripcion)
                            .addComponent(lblCrudNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCrudApellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCrudNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCrudCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudPrecioVenta1)
                        .addGap(18, 18, 18)
                        .addComponent(txtCrudRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(btnCrudAplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCrudCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );
        jPanelCrudLayout.setVerticalGroup(
            jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrudDescripcion)
                    .addComponent(txtCrudApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrudlPrecio)
                    .addComponent(txtCrudTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudPrecioVenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudPrecioVenta1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrudAplicar)
                    .addComponent(btnCrudCancelar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        txtCrudCodigo.getAccessibleContext().setAccessibleName("");
        txtCrudCodigo.getAccessibleContext().setAccessibleDescription("");
        txtCrudNombre.getAccessibleContext().setAccessibleName("");
        txtCrudNombre.getAccessibleContext().setAccessibleDescription("");
        txtCrudCorreo.getAccessibleContext().setAccessibleName("");
        txtCrudCorreo.getAccessibleContext().setAccessibleDescription("");
        txtCrudDireccion.getAccessibleContext().setAccessibleName("");
        txtCrudDireccion.getAccessibleContext().setAccessibleDescription("");
        txtCrudTelefono.getAccessibleContext().setAccessibleName("");
        txtCrudTelefono.getAccessibleContext().setAccessibleDescription("");
        btnCrudAplicar.getAccessibleContext().setAccessibleName("");
        btnCrudAplicar.getAccessibleContext().setAccessibleDescription("");
        btnCrudCancelar.getAccessibleContext().setAccessibleName("");
        btnCrudCancelar.getAccessibleContext().setAccessibleDescription("");
        txtCrudApellido.getAccessibleContext().setAccessibleName("");
        txtCrudApellido.getAccessibleContext().setAccessibleDescription("");
        txtCrudRazonSocial.getAccessibleContext().setAccessibleName("");
        txtCrudRazonSocial.getAccessibleContext().setAccessibleDescription("");

        panelBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panelBuscar.setToolTipText("");

        cajaTipoBusquedad.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        cajaTipoBusquedad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Apellido", "Nombre", "Codigo" }));

        btnBuscar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cajaTipoBusquedad, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addComponent(cajaTipoBusquedad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addContainerGap())
        );

        cajaTipoBusquedad.getAccessibleContext().setAccessibleName("");
        cajaTipoBusquedad.getAccessibleContext().setAccessibleDescription("");
        btnBuscar.getAccessibleContext().setAccessibleName("");
        btnBuscar.getAccessibleContext().setAccessibleDescription("");
        txtBuscar.getAccessibleContext().setAccessibleName("");
        txtBuscar.getAccessibleContext().setAccessibleDescription("");

        btnAgregar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar ");

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelProductoSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralLayout.createSequentialGroup()
                        .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelCrud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelProductoSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelGeneralLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAgregar)
                                    .addComponent(btnEditar))
                                .addGap(27, 27, 27)
                                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnEliminar)
                                    .addComponent(btnActualizar))
                                .addGap(22, 22, 22))
                            .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanelCrud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnEditar.getAccessibleContext().setAccessibleName("");
        btnAgregar.getAccessibleContext().setAccessibleName("");
        btnAgregar.getAccessibleContext().setAccessibleDescription("");
        btnEliminar.getAccessibleContext().setAccessibleName("");
        btnActualizar.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(panelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
        operacionesCrud("Agregar");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
        if (this.jTableClientes.getSelectedRow()!= -1){
            operacionesCrud("Editar");
            setearDatos();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
        if (this.jTableClientes.getSelectedRow()!= -1){
            operacionesCrud("Eliminar");
            setearDatos();
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCrudCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudCancelarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
    }//GEN-LAST:event_btnCrudCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PanelCliente().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCrudAplicar;
    private javax.swing.JButton btnCrudCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cajaTipoBusquedad;
    private javax.swing.JPanel jPanelCrud;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JLabel lblCodigoSelect;
    private javax.swing.JLabel lblCodigoSelectCliente;
    private javax.swing.JLabel lblCrudCantidad;
    private javax.swing.JLabel lblCrudCodigo;
    private javax.swing.JLabel lblCrudDescripcion;
    private javax.swing.JLabel lblCrudNombre;
    private javax.swing.JLabel lblCrudPrecioVenta;
    private javax.swing.JLabel lblCrudPrecioVenta1;
    private javax.swing.JLabel lblCrudlPrecio;
    private javax.swing.JLabel lblNombreSelect;
    private javax.swing.JLabel lblNombreSelectCliente;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JPanel panelProductoSelect;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCrudApellido;
    private javax.swing.JTextField txtCrudCodigo;
    private javax.swing.JTextField txtCrudCorreo;
    private javax.swing.JTextField txtCrudDireccion;
    private javax.swing.JTextField txtCrudNombre;
    private javax.swing.JTextField txtCrudRazonSocial;
    private javax.swing.JTextField txtCrudTelefono;
    // End of variables declaration//GEN-END:variables
}