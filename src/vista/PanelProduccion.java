package vista;
import Modelo.Produccion;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Universidad
 */
public class PanelProduccion extends javax.swing.JFrame {

    private final String[] datosProduccion;

    public PanelProduccion() {
        this.datosProduccion = new String[4];
        initComponents();
        iniciarVentana();
    }
    
    private void iniciarVentana(){
        this.setLocationRelativeTo(null);
        this.setTitle("Inventario de Produccion");
        operacionesCrud("");
        selecionTabla(); 
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public int getID(){
        return Integer.parseInt(txtCrudCodigo.getText());
    }
    
    public String getNombre(){
        return txtCrudNombre.getText();
    }
    
    public float getCosto(){
        return Float.parseFloat(txtCrudCosto.getText());
    }
    
    public int getIdProducto(){
        return Integer.parseInt(txtCrudIdProducto.getText());
    }
    // Fin de getter y setter
    public Produccion crearObjetoProduccion(){
        Produccion produccion = new Produccion();
        try{
            produccion.setId(Integer.parseInt(this.txtCrudCodigo.getText()));
            produccion.setNombre(this.txtCrudNombre.getText());
            produccion.setCosto(Float.parseFloat(this.txtCrudCosto.getText()));
            produccion.setProductoId(Integer.parseInt(this.txtCrudIdProducto.getText()));
        }catch (NumberFormatException ex ){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
       
        return produccion;
    }
    
    public void agregarlistenerAgregar(ActionListener listener){
        btnAgregar.addActionListener(listener);
    }
    
    public void agregarlistenerAplicar(ActionListener listener){
        btnCrudAplicar.addActionListener(listener);
    }
    
    public void agregarlistenerLeer(ActionListener listener){
        btnLeer.addActionListener(listener);
    }
    
    public void operacionesCrud(String operacion){
        switch (operacion){
            
            case "Agregar" -> {
                this.txtCrudCodigo.setEnabled(true);
                this.txtCrudNombre.setEnabled(true);
                this.txtCrudCosto.setEnabled(true);
                this.txtCrudIdProducto.setEnabled(true);
                
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            case "Editar" -> {
                this.txtCrudCodigo.setEnabled(false);
                this.txtCrudNombre.setEnabled(true);
                this.txtCrudCosto.setEnabled(true);
                this.txtCrudIdProducto.setEnabled(true);
                
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            case "Eliminar" -> {
                this.txtCrudCodigo.setEnabled(false);
                this.txtCrudNombre.setEnabled(false);
                this.txtCrudCosto.setEnabled(false);
                this.txtCrudIdProducto.setEnabled(false);
                
                this.btnCrudAplicar.setText(operacion);
                this.btnCrudAplicar.setEnabled(true);
                this.btnCrudCancelar.setEnabled(true);
            }
            
            default -> {
                this.txtCrudCodigo.setText("");
                this.txtCrudNombre.setText("");
                this.txtCrudCosto.setText("");
                this.txtCrudIdProducto.setText("");
                
                this.txtBuscar.setText("");
                this.txtAreaDescripcion.setText("");
                this.lblNombreSelectProd.setText("");
                this.lblCodigoSelectProd.setText("");
                
                this.txtCrudCodigo.setEnabled(false);
                this.txtCrudNombre.setEnabled(false);
                this.txtCrudCosto.setEnabled(false);
                this.txtCrudIdProducto.setEnabled(false);
                
                this.btnCrudAplicar.setEnabled(false);
                this.btnCrudCancelar.setEnabled(false);
                this.btnCrudAplicar.setText("Aplicar");
            }
        }
    }
    
    private void setearDatos(){
        this.txtCrudCodigo.setText(this.datosProduccion[0]);
        this.txtCrudNombre.setText(this.datosProduccion[1]);
        this.txtCrudCosto.setText(this.datosProduccion[2]);
        this.txtCrudIdProducto.setText(this.datosProduccion[3]);
    }
    
    public void cargarTablaProduccion(ArrayList<Produccion> lista){
        
        DefaultTableModel dtmProduccion =(DefaultTableModel)this.jTableProduccion.getModel();
        // eliminar los datos que exinten en el modelo de la tabla antes de agregar los datos 
        if (dtmProduccion.getRowCount() != 0)
        {
            int d = dtmProduccion.getRowCount();
            for (int y = 0; y < d; y++)
            {
                dtmProduccion.removeRow(0);
            }
        }
        this.jTableProduccion.setModel(dtmProduccion);
        
        Iterator <Produccion> iterador = lista.iterator();
        
        while(iterador.hasNext()){
            Produccion produccion = iterador.next();
            dtmProduccion.addRow(new Object[]
              {
              produccion.getId(),
              produccion.getNombre(),
              produccion.getCosto(),
              produccion.getProductoId()
              });
        }
        this.jTableProduccion.setModel(dtmProduccion);
    }
    
    private void selecionTabla(){
        // agregar metodeo de escucha a la tabla 
        this.jTableProduccion.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
           if (this.jTableProduccion.getSelectedRow()!= -1){
               int fila  = jTableProduccion.getSelectedRow();
               this.lblCodigoSelectProd.setText(this.jTableProduccion.getValueAt
                                               (fila, 0).toString());
               this.lblNombreSelectProd.setText(this.jTableProduccion.getValueAt
                                                (fila, 1).toString());
               this.txtAreaDescripcion.setText(this.jTableProduccion.getValueAt
                                                (fila, 2).toString());
               
               
               for (int i = 0; i < 4; i++){
                   this.datosProduccion[i] = 
                           this.jTableProduccion.getValueAt(fila, i).toString();
               }
              
               this.btnCrudAplicar.setEnabled(false);
               this.btnCrudCancelar.setEnabled(false);
               this.btnCrudAplicar.setText("Aplicar");
            }
       });
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

        jPanel11 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProduccion = new javax.swing.JTable();
        panelProductoSelect = new javax.swing.JPanel();
        lblCodigoSelect = new javax.swing.JLabel();
        lblNombreSelect = new javax.swing.JLabel();
        lblCodigoSelectProd = new javax.swing.JLabel();
        lblNombreSelectProd = new javax.swing.JLabel();
        jPanelCrud = new javax.swing.JPanel();
        lblCrudCodigo = new javax.swing.JLabel();
        txtCrudCodigo = new javax.swing.JTextField();
        lblCrudNombre = new javax.swing.JLabel();
        txtCrudNombre = new javax.swing.JTextField();
        lblCrudCantidad = new javax.swing.JLabel();
        lblCrudlPrecio = new javax.swing.JLabel();
        txtCrudIdProducto = new javax.swing.JTextField();
        btnCrudAplicar = new javax.swing.JButton();
        btnCrudCancelar = new javax.swing.JButton();
        txtCrudCosto = new javax.swing.JTextField();
        panelBuscar = new javax.swing.JPanel();
        cajaTipoBusquedad = new javax.swing.JComboBox<>();
        btn_buscarProd = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        panelDescripcion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescripcion = new javax.swing.JTextArea();
        btnLeer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel11.setBackground(new java.awt.Color(203, 240, 220));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Inventario de produccion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        btnEditar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setToolTipText("");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jScrollPane2.setToolTipText("");

        jTableProduccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableProduccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Costo", "Producto (Id)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProduccion.setToolTipText("Productos existente en nuestro inventario");
        jTableProduccion.setName("Inventario de zapatos"); // NOI18N
        jScrollPane2.setViewportView(jTableProduccion);
        if (jTableProduccion.getColumnModel().getColumnCount() > 0) {
            jTableProduccion.getColumnModel().getColumn(0).setResizable(false);
            jTableProduccion.getColumnModel().getColumn(1).setResizable(false);
            jTableProduccion.getColumnModel().getColumn(2).setResizable(false);
            jTableProduccion.getColumnModel().getColumn(3).setResizable(false);
        }

        panelProductoSelect.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Produccion selecionada", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        panelProductoSelect.setToolTipText("");

        lblCodigoSelect.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblCodigoSelect.setText("Código:");
        lblCodigoSelect.setToolTipText("");

        lblNombreSelect.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblNombreSelect.setText("Nombre:");
        lblNombreSelect.setToolTipText("");

        lblCodigoSelectProd.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblCodigoSelectProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombreSelectProd.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblNombreSelectProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelProductoSelectLayout = new javax.swing.GroupLayout(panelProductoSelect);
        panelProductoSelect.setLayout(panelProductoSelectLayout);
        panelProductoSelectLayout.setHorizontalGroup(
            panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductoSelectLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigoSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCodigoSelectProd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNombreSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreSelectProd, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelProductoSelectLayout.setVerticalGroup(
            panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductoSelectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigoSelect)
                    .addGroup(panelProductoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblCodigoSelectProd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreSelectProd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreSelect, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelCrud.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel de Opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanelCrud.setToolTipText("");

        lblCrudCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudCodigo.setText("Código:");

        txtCrudCodigo.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        txtCrudCodigo.setToolTipText("Codigo unico de cada producto");

        lblCrudNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudNombre.setText("Nombre:");

        txtCrudNombre.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudCantidad.setText("Ref. Producto (Id):");

        lblCrudlPrecio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudlPrecio.setText("Costo:");

        txtCrudIdProducto.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        btnCrudAplicar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnCrudAplicar.setText("Aplicar");
        btnCrudAplicar.setToolTipText("Aplicar la operacion selecionada ");

        btnCrudCancelar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnCrudCancelar.setText("cancelar");
        btnCrudCancelar.setToolTipText("Aplicar la operacion selecionada ");
        btnCrudCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudCancelarActionPerformed(evt);
            }
        });

        txtCrudCosto.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        txtCrudCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCrudCostoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCrudLayout = new javax.swing.GroupLayout(jPanelCrud);
        jPanelCrud.setLayout(jPanelCrudLayout);
        jPanelCrudLayout.setHorizontalGroup(
            jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(btnCrudAplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(btnCrudCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCrudNombre)
                            .addComponent(lblCrudlPrecio)
                            .addComponent(lblCrudCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCrudCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCrudCodigo)
                            .addComponent(txtCrudIdProducto)
                            .addComponent(txtCrudCosto)
                            .addComponent(txtCrudNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrudlPrecio)
                    .addComponent(txtCrudCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrudCancelar)
                    .addComponent(btnCrudAplicar))
                .addGap(30, 30, 30))
        );

        panelBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panelBuscar.setToolTipText("");

        cajaTipoBusquedad.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        cajaTipoBusquedad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Codigo Producto" }));

        btn_buscarProd.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btn_buscarProd.setText("Buscar");

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_buscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaTipoBusquedad, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addComponent(cajaTipoBusquedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_buscarProd)
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

        panelDescripcion.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripción del producto"));

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

        btnLeer.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnLeer.setText("Actualizar");
        btnLeer.setToolTipText("Agregar un nuevo producto al inventario");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(panelDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLeer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2)
                    .addComponent(panelProductoSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelCrud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelProductoSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLeer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar))
                            .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanelCrud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.getAccessibleContext().setAccessibleName("Inventario de produccion");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCrudCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCrudCostoActionPerformed

    }//GEN-LAST:event_txtCrudCostoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
        operacionesCrud("Agregar");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
        if (this.jTableProduccion.getSelectedRow()!= -1){
            operacionesCrud("Editar");
            setearDatos();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
        if (this.jTableProduccion.getSelectedRow()!= -1){
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
            java.util.logging.Logger.getLogger(PanelProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PanelProduccion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCrudAplicar;
    private javax.swing.JButton btnCrudCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLeer;
    private javax.swing.JButton btn_buscarProd;
    private javax.swing.JComboBox<String> cajaTipoBusquedad;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanelCrud;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProduccion;
    private javax.swing.JLabel lblCodigoSelect;
    private javax.swing.JLabel lblCodigoSelectProd;
    private javax.swing.JLabel lblCrudCantidad;
    private javax.swing.JLabel lblCrudCodigo;
    private javax.swing.JLabel lblCrudNombre;
    private javax.swing.JLabel lblCrudlPrecio;
    private javax.swing.JLabel lblNombreSelect;
    private javax.swing.JLabel lblNombreSelectProd;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelDescripcion;
    private javax.swing.JPanel panelProductoSelect;
    private javax.swing.JTextArea txtAreaDescripcion;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCrudCodigo;
    private javax.swing.JTextField txtCrudCosto;
    private javax.swing.JTextField txtCrudIdProducto;
    private javax.swing.JTextField txtCrudNombre;
    // End of variables declaration//GEN-END:variables
}
