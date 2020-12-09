/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Modelo.Factura;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ACER
 */
public class PanelFactura extends javax.swing.JFrame {

      private final  String[] datosFactura;
      
    public PanelFactura() {
         
         this.datosFactura = new String[5];
        initComponents();
        iniciarVentana();
    }
      private void iniciarVentana(){
        this.setLocationRelativeTo(null);
        this.setTitle("Facturas");
        operacionesCrud("");
        selecionTabla(); 
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
     public void agregarListener( ActionListener listener){
       
        this.BtnAplicar.addActionListener(listener);
        this.btnBuscar.addActionListener(listener);
        this.btnActualizar.addActionListener(listener);
    }
      public String getCrudCodigo (){
       return this.txtCrudId.getText();
    }
    
    public String getCrudNombre (){
       return this.txtCrudId.getText();
    }
    public Factura crearObjetoFactura(){
        Factura factura = new Factura();
        try{
            
            factura.setId(Integer.valueOf(this.txtCrudId.getText()));
            factura.setClienteId(this.txtCrudClienteId.getText());
            factura.setEmpleadoId(Integer.valueOf(this.txtCrudEmpleadoId.getText()));
            factura.setPadoId(Integer.valueOf(this.txtCrudPagoId.getText()));
            factura.setTipoVentaId(Integer.valueOf(this.txtCrudTipoVentaId.getText()));
            factura.setFecha(this.jDateCrudFecha.getDate());
            
        }catch (NumberFormatException ex ){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
       
        return factura;
    }
     public void cargarTablaFactura(ArrayList<Factura> lista){
        
        DefaultTableModel dtmProductos =(DefaultTableModel)this.jTableFactura.getModel();
        // eliminar los datos que exinten en el modelo de la tabla antes de agregar los datos 
        if (dtmProductos.getRowCount() != 0)
            {
              int d = dtmProductos.getRowCount();
              for (int y = 0; y < d; y++)
                {
                  dtmProductos.removeRow(0);
                }
            }
        this.jTableFactura.setModel(dtmProductos);
        
        Iterator <Factura> iterador = lista.iterator();
        
        while(iterador.hasNext()){
            Factura factura = iterador.next();
            dtmProductos.addRow(new Object[]
              {
              factura.getId(),
              factura.getClienteId(),
              factura.getEmpleadoId(),
              factura.getFecha(),
              factura.getPadoId(),
              factura.getTipoVentaId()
              });
        }
        this.jTableFactura.setModel(dtmProductos);
    }
      private void selecionTabla(){
        // agregar metodeo de escucha a la tabla 
        this.jTableFactura.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
           if (this.jTableFactura.getSelectedRow()!= -1){
               int fila  = jTableFactura.getSelectedRow();
               this.lblCodigoSelectProd.setText(this.jTableFactura.getValueAt
                                               (fila, 0).toString());
               this.lblNombreSelectProd.setText(this.jTableFactura.getValueAt
                                                (fila, 1).toString());
               this.txtAreaDescripcion.setText(this.jTableFactura.getValueAt
                                                (fila, 2).toString());
               
               
               for (int i = 0; i < 6; i++){
                   this.datosFactura[i] = 
                           this.jTableFactura.getValueAt(fila, i).toString();
               }
              
               this.BtnAplicar.setEnabled(false);
               this.btnCancelar.setEnabled(false);
               this.BtnAplicar.setText("Aplicar");
            }
       });
    }
      public void operacionesCrud (String operacion){
       
          switch (operacion){
            
              case "Eliminar" : {
                this.txtCrudId.setEnabled(false);
                this.txtCrudClienteId.setEnabled(false);
                this.txtCrudEmpleadoId.setEnabled(false);
                this.txtCrudPagoId.setEnabled(false);
                this.txtCrudTipoVentaId.setEnabled(false);
                this.BtnAplicar.setText(operacion);
                this.BtnAplicar.setEnabled(true);
                this.btnCancelar.setEnabled(true);
            }
            
            default : {
                this.txtCrudId.setText("");
                this.txtCrudClienteId.setText("");
                this.txtCrudEmpleadoId.setText("");
                this.txtCrudPagoId.setText("");
                this.txtCrudTipoVentaId.setText("");
                this.txtBuscar.setText("");
                this.txtAreaDescripcion.setText("");
                this.lblNombreSelectProd.setText("");
                this.lblCodigoSelectProd.setText("");
                
                this.txtCrudId.setEnabled(false);
                this.txtCrudClienteId.setEnabled(false);
                this.txtCrudEmpleadoId.setEnabled(false);
                this.txtCrudPagoId.setEnabled(false);
                this.txtCrudTipoVentaId.setEnabled(false);
                this.BtnAplicar.setEnabled(false);
                this.btnCancelar.setEnabled(false);
                this.BtnAplicar.setText("Aplicar");
            }
        }
    }
       private void setearDatos(){
        this.txtCrudId.setText(this.datosFactura[0]);
        this.txtCrudClienteId.setText(this.datosFactura[1]);
        this.txtCrudEmpleadoId.setText(this.datosFactura[2]);
        this.txtCrudPagoId.setText(this.datosFactura[3]);
        this.txtCrudTipoVentaId.setText(this.datosFactura[4]);
        this.jDateCrudFecha.setDateFormatString(this.datosFactura[5]);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFactura = new javax.swing.JTable();
        panelFacturaSelect = new javax.swing.JPanel();
        lblCodigoSelect = new javax.swing.JLabel();
        lblNombreSelect = new javax.swing.JLabel();
        lblCodigoSelectProd = new javax.swing.JLabel();
        lblNombreSelectProd = new javax.swing.JLabel();
        jPanelCrud = new javax.swing.JPanel();
        lblCrudCodigo = new javax.swing.JLabel();
        txtCrudId = new javax.swing.JTextField();
        lblCrudNombre = new javax.swing.JLabel();
        txtCrudClienteId = new javax.swing.JTextField();
        lblCrudCantidad = new javax.swing.JLabel();
        txtCrudTipoVentaId = new javax.swing.JTextField();
        lblCrudlPrecio = new javax.swing.JLabel();
        txtCrudPagoId = new javax.swing.JTextField();
        lblCrudPrecioVenta = new javax.swing.JLabel();
        txtCrudEmpleadoId = new javax.swing.JTextField();
        BtnAplicar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblCrudPrecioVenta1 = new javax.swing.JLabel();
        jDateCrudFecha = new com.toedter.calendar.JDateChooser();
        panelBuscar = new javax.swing.JPanel();
        cajaTipoBusquedad = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        panelDescripcion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescripcion = new javax.swing.JTextArea();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel11.setBackground(new java.awt.Color(203, 240, 220));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Factura", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jScrollPane2.setToolTipText("");

        jTableFactura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Cliente id", "Empleado id", "Pago id", "Tipo de venta id", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFactura.setToolTipText("facturas registradas");
        jTableFactura.setName("Inventario de zapatos"); // NOI18N
        jScrollPane2.setViewportView(jTableFactura);

        panelFacturaSelect.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Seleccionar factura", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        panelFacturaSelect.setToolTipText("");

        lblCodigoSelect.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblCodigoSelect.setText("Id:");
        lblCodigoSelect.setToolTipText("");

        lblNombreSelect.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblNombreSelect.setText("Id tipo de venta:");
        lblNombreSelect.setToolTipText("");

        lblCodigoSelectProd.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblCodigoSelectProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombreSelectProd.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        lblNombreSelectProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelFacturaSelectLayout = new javax.swing.GroupLayout(panelFacturaSelect);
        panelFacturaSelect.setLayout(panelFacturaSelectLayout);
        panelFacturaSelectLayout.setHorizontalGroup(
            panelFacturaSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFacturaSelectLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigoSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCodigoSelectProd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNombreSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombreSelectProd, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        panelFacturaSelectLayout.setVerticalGroup(
            panelFacturaSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFacturaSelectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFacturaSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigoSelect)
                    .addGroup(panelFacturaSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblCodigoSelectProd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreSelectProd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreSelect, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelCrud.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel de Opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanelCrud.setToolTipText("");

        lblCrudCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudCodigo.setText("Id:");

        txtCrudId.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        txtCrudId.setToolTipText("Codigo unico de cada producto");

        lblCrudNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudNombre.setText("Id Cliente:");

        txtCrudClienteId.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudCantidad.setText("Id Pago:");

        txtCrudTipoVentaId.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudlPrecio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudlPrecio.setText("Id Empleado:");

        txtCrudPagoId.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        lblCrudPrecioVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudPrecioVenta.setText("Id Tipo Venta:");

        txtCrudEmpleadoId.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N

        BtnAplicar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        BtnAplicar.setText("Aplicar");
        BtnAplicar.setToolTipText("Aplicar la operacion selecionada ");

        btnCancelar.setFont(new java.awt.Font("Aharoni", 0, 14)); // NOI18N
        btnCancelar.setText("cancelar");
        btnCancelar.setToolTipText("Aplicar la operacion selecionada ");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblCrudPrecioVenta1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCrudPrecioVenta1.setText("Fecha:");

        javax.swing.GroupLayout jPanelCrudLayout = new javax.swing.GroupLayout(jPanelCrud);
        jPanelCrud.setLayout(jPanelCrudLayout);
        jPanelCrudLayout.setHorizontalGroup(
            jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addComponent(BtnAplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCrudClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCrudPagoId, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCrudPrecioVenta)
                            .addComponent(lblCrudPrecioVenta1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateCrudFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCrudTipoVentaId, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addComponent(lblCrudCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCrudId, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCrudLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblCrudlPrecio)
                        .addGap(11, 11, 11)
                        .addComponent(txtCrudEmpleadoId, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelCrudLayout.setVerticalGroup(
            jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrudlPrecio)
                    .addComponent(txtCrudEmpleadoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudPagoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrudTipoVentaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCrudPrecioVenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCrudPrecioVenta1)
                    .addComponent(jDateCrudFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(BtnAplicar))
                .addGap(30, 30, 30))
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
                .addComponent(cajaTipoBusquedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addContainerGap())
        );

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

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelFacturaSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(panelDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addComponent(panelFacturaSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnActualizar)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
        if (this.jTableFactura.getSelectedRow()!= -1){
            operacionesCrud("Eliminar");
            setearDatos();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        operacionesCrud("");
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(PanelFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAplicar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cajaTipoBusquedad;
    private com.toedter.calendar.JDateChooser jDateCrudFecha;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanelCrud;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableFactura;
    private javax.swing.JLabel lblCodigoSelect;
    private javax.swing.JLabel lblCodigoSelectProd;
    private javax.swing.JLabel lblCrudCantidad;
    private javax.swing.JLabel lblCrudCodigo;
    private javax.swing.JLabel lblCrudNombre;
    private javax.swing.JLabel lblCrudPrecioVenta;
    private javax.swing.JLabel lblCrudPrecioVenta1;
    private javax.swing.JLabel lblCrudlPrecio;
    private javax.swing.JLabel lblNombreSelect;
    private javax.swing.JLabel lblNombreSelectProd;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelDescripcion;
    private javax.swing.JPanel panelFacturaSelect;
    private javax.swing.JTextArea txtAreaDescripcion;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCrudClienteId;
    private javax.swing.JTextField txtCrudEmpleadoId;
    private javax.swing.JTextField txtCrudId;
    private javax.swing.JTextField txtCrudPagoId;
    private javax.swing.JTextField txtCrudTipoVentaId;
    // End of variables declaration//GEN-END:variables
}
