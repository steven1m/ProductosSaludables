
package vista;

import Modelo.Pedido;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Universidad
 */
public class PanelPedido extends javax.swing.JFrame {

    private final  String[] datosParaMostrar;
    
    public PanelPedido() {
        this.datosParaMostrar = new String[6];
        initComponents();
        iniciarVentana();
    }
    
    private void iniciarVentana(){
        this.setLocationRelativeTo(null);
        this.setTitle("Inventario de Pedidos");
        operacionesCrud("");
        selecionTabla(); 
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void agregarListener( ActionListener listener){
        
        this.btnCrudAplicar.addActionListener(listener);
        this.btnBuscar.addActionListener(listener);
        this.btnActualizar.addActionListener(listener);
    }
    
    public String getCrudCodigo (){
       return this.txtCrudCodigo.getText();
    }
    
    public Pedido crearObjeto(){
        Pedido pedido = new Pedido();
        try{
            
            pedido.setId(Integer.parseInt(this.txtCrudCodigo.getText()));
            pedido.setFechaPedido(String.valueOf( this.jDatePedido.getDate() ));
            pedido.setFechaDespacho(String.valueOf( this.jDateDespacho.getDate() ));
            pedido.setEmpleadoId(Integer.parseInt(this.txtCrudEmpleadoId.getText()));
            pedido.setClienteId(this.txtCrudClienteId.getText());
            pedido.setEstadoPedidoId(Integer.parseInt(this.txtCrudEstadoPedido.getText()));
            pedido.setFacturaId(Integer.parseInt(this.txtCrudFacturaId.getText()));
            
        }catch (NumberFormatException ex ){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
       
        return pedido;
    }
    public void cargarTabla(ArrayList<Pedido> lista){
        
        DefaultTableModel dtmTabla =(DefaultTableModel)this.jTablePrincipal.getModel();
        // eliminar los datos que exinten en el modelo de la tabla antes de agregar los datos 
        if (dtmTabla.getRowCount() != 0)
            {
              int d = dtmTabla.getRowCount();
              for (int y = 0; y < d; y++)
                {
                  dtmTabla.removeRow(0);
                }
            }
        this.jTablePrincipal.setModel(dtmTabla);
        
        Iterator <Pedido> iterador = lista.iterator();
        
        while(iterador.hasNext()){
            Pedido pedido = iterador.next();
            dtmTabla.addRow(new Object[]
              {
              pedido.getId(),
              pedido.getFechaPedido(),
              pedido.getFechaDespacho(),
              pedido.getEmpleadoId(),
              pedido.getClienteId(),
              pedido.getEstadoPedidoId(),
              pedido.getFacturaId()
              });
        }
        this.jTablePrincipal.setModel(dtmTabla);
    }
    
    private void selecionTabla(){
        // agregar metodeo de escucha a la tabla 
        this.jTablePrincipal.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
           if (this.jTablePrincipal.getSelectedRow()!= -1){
               int fila  = jTablePrincipal.getSelectedRow();
               this.etiquetaCodigoSelect.setText(this.jTablePrincipal.getValueAt
                                               (fila, 0).toString());
               this.lblNombreSelect.setText(this.jTablePrincipal.getValueAt
                                                (fila, 1).toString());
               this.txtAreaDescripcion.setText(this.jTablePrincipal.getValueAt
                                                (fila, 2).toString());
               
               
               for (int i = 0; i < 6; i++){
                   this.datosParaMostrar[i] = 
                           this.jTablePrincipal.getValueAt(fila, i).toString();
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
                this.jDatePedido.setEnabled(true);
                this.jDateDespacho.setEnabled(true);
                this.txtCrudEmpleadoId.setEnabled(true);
                this.txtCrudClienteId.setEnabled(true);
                this.txtCrudEstadoPedido.setEnabled(true);
                this.txtCrudFacturaId.setEnabled(true);
                
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            case "Editar" -> {
                this.txtCrudCodigo.setEnabled(false);
                this.jDatePedido.setEnabled(true);
                this.jDateDespacho.setEnabled(true);
                this.txtCrudEmpleadoId.setEnabled(true);
                this.txtCrudClienteId.setEnabled(true);
                this.txtCrudEstadoPedido.setEnabled(true);
                this.txtCrudFacturaId.setEnabled(false);
                
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            case "Eliminar" -> {
                this.txtCrudCodigo.setEnabled(false);
                this.jDatePedido.setEnabled(false);
                this.jDateDespacho.setEnabled(false);
                this.txtCrudEmpleadoId.setEnabled(false);
                this.txtCrudClienteId.setEnabled(false);
                this.txtCrudEstadoPedido.setEnabled(false);
                this.txtCrudFacturaId.setEnabled(false);
                
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            default -> {
                this.txtCrudCodigo.setText("");
                //this.jDatePedido.set;
                //this.jDateDespacho.set;
                this.txtCrudEmpleadoId.setText("");
                this.txtCrudClienteId.setText("");
                this.txtCrudFacturaId.setText("");
                this.txtCrudFacturaId.setText("");
                
                this.txtBuscar.setText("");
                this.txtAreaDescripcion.setText("");
                this.lblNombreSelect.setText("");
                this.etiquetaCodigoSelect.setText("");
                
                this.txtCrudCodigo.setEnabled(false);
                this.jDatePedido.setEnabled(false);
                this.jDateDespacho.setEnabled(false);
                this.txtCrudEmpleadoId.setEnabled(false);
                this.txtCrudClienteId.setEnabled(false);
                this.txtCrudEstadoPedido.setEnabled(false);
                this.txtCrudFacturaId.setEnabled(false);
                
                this.btnCrudAplicar.setEnabled(false);
                this.btnCrudCancelar.setEnabled(false);
                this.btnCrudAplicar.setText("Aplicar");
                this.btnActualizar.setActionCommand("Actualizar");
            }
        }
    }
    
    private void setearDatos(){
        this.txtCrudCodigo.setText(this.datosParaMostrar[0]);
        this.jDatePedido.setDateFormatString(this.datosParaMostrar[1]);
        this.jDateDespacho.setDateFormatString(this.datosParaMostrar[2]);
        this.txtCrudEmpleadoId.setText(this.datosParaMostrar[3]);
        this.txtCrudClienteId.setText(this.datosParaMostrar[4]);
        this.txtCrudEstadoPedido.setText(this.datosParaMostrar[5]);
        this.txtCrudFacturaId.setText(this.datosParaMostrar[6]);
    }
    
    public String[] datosBuscar(){
        
        String[] datos = new String[2];
        String clave ;
        String valor = this.txtBuscar.getText();
        if (this.cajaTipoBusquedad.getSelectedIndex() == 1){
            clave = "id";
        }else {
            clave = "nombre";
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
        jTablePrincipal = new javax.swing.JTable();
        panelProductoSelect = new javax.swing.JPanel();
        lblCodigoSelect = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        etiquetaCodigoSelect = new javax.swing.JLabel();
        lblNombreSelect = new javax.swing.JLabel();
        jPanelCrud = new javax.swing.JPanel();
        lblCrudCodigo = new javax.swing.JLabel();
        txtCrudCodigo = new javax.swing.JTextField();
        lblCrudNombre = new javax.swing.JLabel();
        lblCrudCantidad = new javax.swing.JLabel();
        txtCrudClienteId = new javax.swing.JTextField();
        lblCrudlPrecio = new javax.swing.JLabel();
        txtCrudEmpleadoId = new javax.swing.JTextField();
        lblCrudPrecioVenta = new javax.swing.JLabel();
        btnCrudAplicar = new javax.swing.JButton();
        btnCrudCancelar = new javax.swing.JButton();
        lblCrudPrecioVenta1 = new javax.swing.JLabel();
        txtCrudEstadoPedido = new javax.swing.JTextField();
        lblCrudPrecioVenta2 = new javax.swing.JLabel();
        txtCrudFacturaId = new javax.swing.JTextField();
        jDatePedido = new com.toedter.calendar.JDateChooser();
        jDateDespacho = new com.toedter.calendar.JDateChooser();
        panelBuscar = new javax.swing.JPanel();
        cajaTipoBusquedad = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        panelDescripcion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescripcion = new javax.swing.JTextArea();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelGeneral.setBackground(new java.awt.Color(203, 240, 220));
        panelGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Panel Principal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        btnEditar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jScrollPane2.setToolTipText("");

        jTablePrincipal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTablePrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Fecha pedido", "Fecha despacho", "Id Empleado", "Id Cliente", "Estado Pedido", "Id Factura"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePrincipal.setName("Inventario de zapatos"); // NOI18N
        jScrollPane2.setViewportView(jTablePrincipal);
        if (jTablePrincipal.getColumnModel().getColumnCount() > 0) {
            jTablePrincipal.getColumnModel().getColumn(0).setResizable(false);
            jTablePrincipal.getColumnModel().getColumn(4).setResizable(false);
            jTablePrincipal.getColumnModel().getColumn(5).setResizable(false);
        }

        panelProductoSelect.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Selección ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        panelProductoSelect.setToolTipText("");

        lblCodigoSelect.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblCodigoSelect.setText("Código:");
        lblCodigoSelect.setToolTipText("");

        etiquetaNombre.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        etiquetaNombre.setText("Nombre:");
        etiquetaNombre.setToolTipText("");

        etiquetaCodigoSelect.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        etiquetaCodigoSelect.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombreSelect.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblNombreSelect.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelProductoSelectLayout = new javax.swing.GroupLayout(panelProductoSelect);
        panelProductoSelect.setLayout(panelProductoSelectLayout);
        panelProductoSelectLayout.setHorizontalGroup(
            panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductoSelectLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigoSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etiquetaCodigoSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(etiquetaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelProductoSelectLayout.setVerticalGroup(
            panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductoSelectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigoSelect)
                    .addGroup(panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(etiquetaCodigoSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etiquetaNombre, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelCrud.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel de Opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanelCrud.setToolTipText("");

        lblCrudCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudCodigo.setText("Código:");

        txtCrudCodigo.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudNombre.setText("Fecha pedido:");

        lblCrudCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudCantidad.setText("Empleado:");

        txtCrudClienteId.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudlPrecio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudlPrecio.setText("Fecha despacho:");

        txtCrudEmpleadoId.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudPrecioVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudPrecioVenta.setText("Cliente:");

        btnCrudAplicar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnCrudAplicar.setText("Aplicar");

        btnCrudCancelar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnCrudCancelar.setText("Cancelar");
        btnCrudCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudCancelarActionPerformed(evt);
            }
        });

        lblCrudPrecioVenta1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudPrecioVenta1.setText("Estado pedido:");

        txtCrudEstadoPedido.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudPrecioVenta2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudPrecioVenta2.setText("Id de factura:");

        txtCrudFacturaId.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        jDatePedido.setDateFormatString("dd/MM/yyyy");

        jDateDespacho.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanelCrudLayout = new javax.swing.GroupLayout(jPanelCrud);
        jPanelCrud.setLayout(jPanelCrudLayout);
        jPanelCrudLayout.setHorizontalGroup(
            jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addComponent(btnCrudAplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCrudCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudPrecioVenta1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCrudEstadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudPrecioVenta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCrudFacturaId, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCrudCodigo)
                            .addComponent(lblCrudNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDatePedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCrudCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCrudPrecioVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCrudCantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelCrudLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblCrudlPrecio)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateDespacho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCrudEmpleadoId, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(txtCrudClienteId, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelCrudLayout.setVerticalGroup(
            jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCrudCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCrudCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCrudNombre))
                    .addComponent(jDatePedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCrudlPrecio)
                    .addComponent(jDateDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudEmpleadoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudPrecioVenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudEstadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudPrecioVenta1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudFacturaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudPrecioVenta2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrudAplicar)
                    .addComponent(btnCrudCancelar))
                .addContainerGap())
        );

        panelBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panelBuscar.setToolTipText("");

        cajaTipoBusquedad.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        cajaTipoBusquedad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Codigo Producto" }));

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

        panelDescripcion.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripción"));

        txtAreaDescripcion.setColumns(20);
        txtAreaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtAreaDescripcion);

        javax.swing.GroupLayout panelDescripcionLayout = new javax.swing.GroupLayout(panelDescripcion);
        panelDescripcion.setLayout(panelDescripcionLayout);
        panelDescripcionLayout.setHorizontalGroup(
            panelDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDescripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDescripcionLayout.setVerticalGroup(
            panelDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDescripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        btnActualizar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar ");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelProductoSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralLayout.createSequentialGroup()
                        .addComponent(panelDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizar))
                            .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanelCrud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        if (this.jTablePrincipal.getSelectedRow()!= -1){
            operacionesCrud("Editar");
            setearDatos();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
        if (this.jTablePrincipal.getSelectedRow()!= -1){
            operacionesCrud("Eliminar");
            setearDatos();
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCrudCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudCancelarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
    }//GEN-LAST:event_btnCrudCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(PanelPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PanelPedido().setVisible(true);
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
    private javax.swing.JLabel etiquetaCodigoSelect;
    private javax.swing.JLabel etiquetaNombre;
    private com.toedter.calendar.JDateChooser jDateDespacho;
    private com.toedter.calendar.JDateChooser jDatePedido;
    private javax.swing.JPanel jPanelCrud;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePrincipal;
    private javax.swing.JLabel lblCodigoSelect;
    private javax.swing.JLabel lblCrudCantidad;
    private javax.swing.JLabel lblCrudCodigo;
    private javax.swing.JLabel lblCrudNombre;
    private javax.swing.JLabel lblCrudPrecioVenta;
    private javax.swing.JLabel lblCrudPrecioVenta1;
    private javax.swing.JLabel lblCrudPrecioVenta2;
    private javax.swing.JLabel lblCrudlPrecio;
    private javax.swing.JLabel lblNombreSelect;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelDescripcion;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JPanel panelProductoSelect;
    private javax.swing.JTextArea txtAreaDescripcion;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCrudClienteId;
    private javax.swing.JTextField txtCrudCodigo;
    private javax.swing.JTextField txtCrudEmpleadoId;
    private javax.swing.JTextField txtCrudEstadoPedido;
    private javax.swing.JTextField txtCrudFacturaId;
    // End of variables declaration//GEN-END:variables
}
