/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author ACER
 */
import Modelo.Empleado;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
public class PanelEmpleado extends javax.swing.JFrame {

    /**
     * Creates new form PanelEmpleado
     */
    private final  String[] datosProducto;
    
    public PanelEmpleado() {
        this.datosProducto = new String[6];
        initComponents();
        iniciarVentana();
    }
     private void iniciarVentana(){
        this.setLocationRelativeTo(null);
        this.setTitle("Inventario de Productos");
        operacionesCrud("");
        selecionTabla(); 
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void agregarListener( ActionListener listener){
        
        
         this.btnCrudAplicar.addActionListener(listener);
        this.btnBuscar.addActionListener(listener);
        this.btnActualizar.addActionListener(listener);
        this.btnEditar.addActionListener(listener);
        this.btnEliminar.addActionListener(listener);
        this.btnCrudCancelar.addActionListener(listener);
        this.btnAgregar.addActionListener(listener);
    }
      public String getCrudId (){
       return this.txtId.getText();
    }
    
    public String getNombre (){
       return this.txtNombre.getText();
    }
     public String getApellido (){
       return this.txtApellido.getText();
    }
      public String getDireccion (){
       return this.txtDireccion.getText();
    }
       public String getTelefono (){
       return this.txtTelefono.getText();
    }
        public String getCorreo (){
       return this.txtCorreo.getText();
    }
         public String getCargo (){
       return this.txtCargo.getText();
    }
          public String getSalario (){
       return this.txtSalario.getText();
    }
    
    public Empleado crearObjetoEmpleado(){
        Empleado empleado = new Empleado();
        try{
            
            empleado.setId(Integer.parseInt(this.txtId.getText()));
            empleado.setNombre(this.txtNombre.getText());
            empleado.setApellido(this.txtApellido.getText());
            empleado.setDireccion(this.txtDireccion.getText());
            empleado.setTelefono(this.txtTelefono.getText());
            empleado.setCargo(this.txtCargo.getText());
            empleado.setCorreo(this.txtCorreo.getText());
            empleado.setSalario(Float.valueOf(this.txtSalario.getText()));
        }catch (NumberFormatException ex ){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
       
        return empleado;
    }
     public void cargarTablaEmpleado(ArrayList<Empleado> lista){
        
        DefaultTableModel dtmEmpleado =(DefaultTableModel)this.jTableEmpleado.getModel();
        // eliminar los datos que exinten en el modelo de la tabla antes de agregar los datos 
        if (dtmEmpleado.getRowCount() != 0)
            {
              int d = dtmEmpleado.getRowCount();
              for (int y = 0; y < d; y++)
                {
                  dtmEmpleado.removeRow(0);
                }
            }
        this.jTableEmpleado.setModel(dtmEmpleado);
        
        Iterator <Empleado> iterador = lista.iterator();
        
        while(iterador.hasNext()){
            Empleado empleado = iterador.next();
            dtmEmpleado.addRow(new Object[]
              {
              empleado.getId(),
              empleado.getNombre(),
              empleado.getApellido(),
              empleado.getDireccion(),
              empleado.getTelefono(),
               empleado.getCargo(),
                empleado.getSalario(),
              empleado.getCorreo()
              });
        }
        this.jTableEmpleado.setModel(dtmEmpleado);
    }
      private void selecionTabla(){
        // agregar metodeo de escucha a la tabla 
        this.jTableEmpleado.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
           if (this.jTableEmpleado.getSelectedRow()!= -1){
               int fila  = jTableEmpleado.getSelectedRow();
               this.txtSeleccionarId.setText(this.jTableEmpleado.getValueAt
                                               (fila, 0).toString());
               this.txtSeleccionarNombre.setText(this.jTableEmpleado.getValueAt
                                                (fila, 1).toString());
               /*this.txtAreaDescripcion.setText(this.jTableEmpleado.getValueAt
                                                (fila, 2).toString());*/
               
               
               for (int i = 0; i < 6; i++){
                   this.datosProducto[i] = 
                           this.jTableEmpleado.getValueAt(fila, i).toString();
               }
              
               this.btnCrudAplicar.setEnabled(false);
               this.btnCrudCancelar.setEnabled(false);
               this.btnCrudAplicar.setText("Aplicar");
            }
       });
    }
       public void operacionesCrud (String operacion){
        switch (operacion){
            
            case "Agregar" : {
                this.txtId.setEnabled(false);
                this.txtNombre.setEnabled(false);
                this.txtApellido.setEnabled(false);
                this.txtDireccion.setEnabled(false);
                this.txtTelefono.setEnabled(false);
                this.txtCargo.setEnabled(false);
                this.txtCorreo.setEnabled(false);
                this.txtSalario.setEnabled(false);
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            case "Editar" : {
                this.txtId.setEnabled(false);
                this.txtNombre.setEnabled(false);
                this.txtApellido.setEnabled(false);
                this.txtDireccion.setEnabled(false);
                this.txtTelefono.setEnabled(false);
                this.txtCargo.setEnabled(false);
                this.txtCorreo.setEnabled(false);
                this.txtSalario.setEnabled(false);
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            case "Eliminar" : {
                this.txtId.setEnabled(false);
                this.txtNombre.setEnabled(false);
                this.txtApellido.setEnabled(false);
                this.txtDireccion.setEnabled(false);
                this.txtTelefono.setEnabled(false);
                this.txtCargo.setEnabled(false);
                this.txtCorreo.setEnabled(false);
                this.txtSalario.setEnabled(false);
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            default : {
                this.txtId.setText("");
                this.txtNombre.setText("");
                this.txtApellido.setText("");
                this.txtDireccion.setText("");
                this.txtTelefono.setText("");
                this.txtCargo.setText("");
                this.txtCorreo.setText("");
                this.txtSalario.setText("");
                this.txtSeleccionarNombre.setText("");
                this.txtSeleccionarId.setText("");
                
                this.txtId.setEnabled(false);
                this.txtNombre.setEnabled(false);
                this.txtApellido.setEnabled(false);
                this.txtDireccion.setEnabled(false);
                this.txtTelefono.setEnabled(false);
                this.txtCargo.setEnabled(false);
                this.txtCorreo.setEnabled(false);
                this.txtSalario.setEnabled(false);
                this.btnCrudAplicar.setEnabled(false);
                this.btnCrudCancelar.setEnabled(false);
                this.btnCrudAplicar.setText("Aplicar");
                this.btnActualizar.setActionCommand("Actualizar");
            }
        }
    }
        private void setearDatos(){
        this.txtId.setText(this.datosProducto[0]);
        this.txtNombre.setText(this.datosProducto[1]);
        this.txtApellido.setText(this.datosProducto[2]);
        this.txtDireccion.setText(this.datosProducto[3]);
        this.txtTelefono.setText(this.datosProducto[4]);
        this.txtCorreo.setText(this.datosProducto[5]);
        this.txtCargo.setText(this.datosProducto[6]);
        this.txtSalario.setText(this.datosProducto[7]);
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
        jTableEmpleado = new javax.swing.JTable();
        panelProductoSelect = new javax.swing.JPanel();
        lblCodigoSelect = new javax.swing.JLabel();
        lblNombreSelect = new javax.swing.JLabel();
        txtSeleccionarId = new javax.swing.JLabel();
        txtSeleccionarNombre = new javax.swing.JLabel();
        panelBuscar = new javax.swing.JPanel();
        cajaTipoBusquedad = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jPanelCrud = new javax.swing.JPanel();
        lblCrudCodigo = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblCrudNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblCrudCantidad = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        lblCrudlPrecio = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblCrudPrecioVenta = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnCrudAplicar = new javax.swing.JButton();
        btnCrudCancelar = new javax.swing.JButton();
        lblCrudDescripcion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSalario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelGeneral.setBackground(new java.awt.Color(203, 240, 220));
        panelGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Empleados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        btnEditar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setToolTipText("");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jScrollPane2.setToolTipText("");

        jTableEmpleado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido", "Direccion", "Telefono", "Correo", "Cargo", "Salario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEmpleado.setToolTipText("Productos existente en nuestro inventario");
        jTableEmpleado.setName("Inventario de zapatos"); // NOI18N
        jScrollPane2.setViewportView(jTableEmpleado);

        panelProductoSelect.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Empleado selecionado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        panelProductoSelect.setToolTipText("");

        lblCodigoSelect.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblCodigoSelect.setText("Id");
        lblCodigoSelect.setToolTipText("");

        lblNombreSelect.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblNombreSelect.setText("Nombre:");
        lblNombreSelect.setToolTipText("");

        txtSeleccionarId.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        txtSeleccionarId.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtSeleccionarNombre.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        txtSeleccionarNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelProductoSelectLayout = new javax.swing.GroupLayout(panelProductoSelect);
        panelProductoSelect.setLayout(panelProductoSelectLayout);
        panelProductoSelectLayout.setHorizontalGroup(
            panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductoSelectLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblCodigoSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(txtSeleccionarId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombreSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(txtSeleccionarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
        );
        panelProductoSelectLayout.setVerticalGroup(
            panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductoSelectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigoSelect)
                    .addGroup(panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtSeleccionarId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSeleccionarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreSelect, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addContainerGap())
        );

        btnAgregar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setToolTipText("Agregar un nuevo producto al inventario");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar ");
        btnActualizar.setToolTipText("");

        jPanelCrud.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel de Opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanelCrud.setToolTipText("");

        lblCrudCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudCodigo.setText("Id");

        txtId.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        txtId.setToolTipText("Codigo unico de cada producto");

        lblCrudNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudNombre.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudCantidad.setText("Correo");

        txtCargo.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudlPrecio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudlPrecio.setText("Telefono");

        txtCorreo.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudPrecioVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudPrecioVenta.setText("Cargo");

        txtTelefono.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        btnCrudAplicar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnCrudAplicar.setText("Aplicar");
        btnCrudAplicar.setToolTipText("Aplicar la operacion selecionada ");

        btnCrudCancelar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnCrudCancelar.setText("Cancelar");
        btnCrudCancelar.setToolTipText("Aplicar la operacion selecionada ");
        btnCrudCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudCancelarActionPerformed(evt);
            }
        });

        lblCrudDescripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudDescripcion.setText("Direccion");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Apellido");

        jLabel2.setText("Salario");

        javax.swing.GroupLayout jPanelCrudLayout = new javax.swing.GroupLayout(jPanelCrud);
        jPanelCrud.setLayout(jPanelCrudLayout);
        jPanelCrudLayout.setHorizontalGroup(
            jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudPrecioVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addComponent(btnCrudAplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCrudCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudDescripcion)
                        .addGap(27, 27, 27)
                        .addComponent(txtDireccion))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40)
                        .addComponent(txtApellido))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelCrudLayout.createSequentialGroup()
                                .addComponent(lblCrudlPrecio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCrudLayout.createSequentialGroup()
                                .addComponent(lblCrudCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(48, 48, 48)
                        .addComponent(txtSalario)))
                .addContainerGap())
        );
        jPanelCrudLayout.setVerticalGroup(
            jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudNombre))
                .addGap(18, 18, 18)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCrudDescripcion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrudlPrecio)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudPrecioVenta))
                .addGap(18, 58, Short.MAX_VALUE)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrudAplicar)
                    .addComponent(btnCrudCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelProductoSelect, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelCrud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelProductoSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGeneralLayout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizar))
                            .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
        if (this.jTableEmpleado.getSelectedRow()!= -1){
            operacionesCrud("Editar");
            setearDatos();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
        operacionesCrud("Agregar");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
        if (this.jTableEmpleado.getSelectedRow()!= -1){
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelEmpleado().setVisible(true);
            }
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelCrud;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableEmpleado;
    private javax.swing.JLabel lblCodigoSelect;
    private javax.swing.JLabel lblCrudCantidad;
    private javax.swing.JLabel lblCrudCodigo;
    private javax.swing.JLabel lblCrudDescripcion;
    private javax.swing.JLabel lblCrudNombre;
    private javax.swing.JLabel lblCrudPrecioVenta;
    private javax.swing.JLabel lblCrudlPrecio;
    private javax.swing.JLabel lblNombreSelect;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JPanel panelProductoSelect;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JLabel txtSeleccionarId;
    private javax.swing.JLabel txtSeleccionarNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
