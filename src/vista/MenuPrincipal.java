package vista;

import Controlador.ControladorCatalogo;
import Controlador.ControladorCategoriaMateriaPrima;
import Controlador.ControladorClientes;
import Controlador.ControladorDetalleCatalogo;
import Controlador.ControladorProduccion;
import Controlador.ControladorProducto;
import Controlador.ControladorTipoPago;
import Controlador.ControladorProveedor;
import Controlador.ControladorFactura;
import Controlador.ControladorListaIngredientes;
import Controlador.ControladorMateriaPrima;
import Controlador.ControladorPedido;
import Controlador.ControladorEmpleado;
import Controlador.ControladorEstadoPedido;
import Controlador.ControladorPago;

import Modelo.ProveedorDAO;
import Modelo.ProduccionDAO;
import Modelo.CatalogoDAO;
import Modelo.CategoriaMateriaPrimaDAO;
import Modelo.ClienteDAO;
import Modelo.DetalleCatalogoDAO;
import Modelo.ProductoDAO;
import Modelo.ServidorChat;
import Modelo.TipoPagoDAO;
import Modelo.FacturaDAO;
import Modelo.ListaIngredientesDAO;
import Modelo.MateriaPrimaDAO;
import Modelo.PedidoDAO;
import Modelo.EmpleadoDAO;
import Modelo.EstadoPedidoDAO;
import Modelo.PagoDAO;
        
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

/**
 *
 * @authors
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jFrame1 = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnchat = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuControlProductos = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        MenuItemProduccion = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        menuControlMateriaPrima = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemPagos = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        AdminProveedores = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItemEmpleados = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));

        btnchat.setText("Lanzar chat");
        btnchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnchat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnchat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Ventas"));

        jButton1.setText("ventas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        menuControlProductos.setText("Control de Productos");

        jMenuItem1.setText("Producto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuControlProductos.add(jMenuItem1);

        MenuItemProduccion.setText("Produccion");
        MenuItemProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemProduccionActionPerformed(evt);
            }
        });
        menuControlProductos.add(MenuItemProduccion);

        jMenuItem2.setText("Ingredientes");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuControlProductos.add(jMenuItem2);

        jMenuItem3.setText("jMenuItem3");
        menuControlProductos.add(jMenuItem3);

        jMenuBar1.add(menuControlProductos);

        menuControlMateriaPrima.setText("Control Materia Prima");

        jMenuItem4.setText("Materia Prima");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuControlMateriaPrima.add(jMenuItem4);

        jMenuBar1.add(menuControlMateriaPrima);

        jMenu1.setText("Herramientas");

        jMenu2.setText("Administrar");

        jMenu3.setText("Pagos");

        jMenuItemPagos.setText("Pagos");
        jMenuItemPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPagosActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemPagos);

        jMenuItem6.setText("Tipo de Pagos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenu2.add(jMenu3);

        jMenu4.setText("Materia Prima");

        jMenuItem7.setText("Categoria Materia Prima");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenu2.add(jMenu4);

        jMenu5.setText("Proveedores");

        AdminProveedores.setText("Administrar Proveedores");
        AdminProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminProveedoresActionPerformed(evt);
            }
        });
        jMenu5.add(AdminProveedores);

        jMenuItem5.setText("Catalogos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuItem9.setText("Detalle Catalogos");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenu2.add(jMenu5);

        jMenuItem8.setText("Clientes");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem10.setText("Facturas");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenu6.setText("Pedidos");

        jMenuItem11.setText("Pedidos");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuItem12.setText("Estado Pedidos");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenu2.add(jMenu6);

        jMenuItemEmpleados.setText("Empleados");
        jMenuItemEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEmpleadosActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemEmpleados);

        jMenu1.add(jMenu2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuItemProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemProduccionActionPerformed
        // TODO add your handling code here:
        PanelProduccion vistaProduccion = new PanelProduccion();
        ProduccionDAO modeloProduccion = new ProduccionDAO();
        ControladorProduccion controladorProduccion = new ControladorProduccion(modeloProduccion, vistaProduccion);
        //vistaProduccion.setVisible(true);
    }//GEN-LAST:event_MenuItemProduccionActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        PanelProducto vista = new PanelProducto();
        ProductoDAO modelo = new ProductoDAO();
        ControladorProducto control = new ControladorProducto(modelo, vista);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        
        TipoPagoDAO modeloTipoPago = new TipoPagoDAO();
        PanelTipoPago panelTipoPago = new PanelTipoPago();
        
        ControladorTipoPago control = new ControladorTipoPago(modeloTipoPago, panelTipoPago);
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        
        CatalogoDAO modeloCatalogo = new CatalogoDAO();
        PanelCatalogo panelCatalogo = new PanelCatalogo();
        ControladorCatalogo control = new ControladorCatalogo(modeloCatalogo, panelCatalogo);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        CategoriaMateriaPrimaDAO modeloCategoria = new CategoriaMateriaPrimaDAO();
        PanelCategoriaMateriaPrima panelCategotia = new PanelCategoriaMateriaPrima();
        
        ControladorCategoriaMateriaPrima controlMP = new 
                        ControladorCategoriaMateriaPrima(modeloCategoria, panelCategotia);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        ClienteDAO modeloCliente = new ClienteDAO();
        PanelCliente panelCliente = new PanelCliente();
        ControladorClientes controlCliente = new ControladorClientes(modeloCliente, panelCliente);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        
        DetalleCatalogoDAO modeloDetalleCatalogo = new DetalleCatalogoDAO();
        PanelDetalleCatalogo panelDetalleCatalogo = new PanelDetalleCatalogo();
        ControladorDetalleCatalogo controlDetalle = new ControladorDetalleCatalogo
                            (modeloDetalleCatalogo, panelDetalleCatalogo);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void btnchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchatActionPerformed
        // TODO add your handling code here:
        ServidorChat aplicacion = new ServidorChat(); // crea el servidor
        aplicacion.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        aplicacion.ejecutarServidor(); // ejecuta la aplicación servidor
    }//GEN-LAST:event_btnchatActionPerformed

    private void AdminProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminProveedoresActionPerformed
        // TODO add your handling code here:
        PanelProveedor panelProveedor = new PanelProveedor();
        ProveedorDAO modeloProveedor = new ProveedorDAO();
        ControladorProveedor controlProveedor = new ControladorProveedor(modeloProveedor, panelProveedor);
    }//GEN-LAST:event_AdminProveedoresActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        PanelFactura panelFactura = new PanelFactura();
        FacturaDAO modeloFactura = new FacturaDAO();
        ControladorFactura controlFactura = new ControladorFactura(modeloFactura, panelFactura);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        PanelListaIngredientes panelListaIngredientes = new PanelListaIngredientes();
        ListaIngredientesDAO modeloListaIngredientes = new ListaIngredientesDAO();
        ControladorListaIngredientes controladorListaIngredientes = new ControladorListaIngredientes(modeloListaIngredientes, panelListaIngredientes);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        PanelMateriaPrima panelMateriaPrima = new PanelMateriaPrima();
        MateriaPrimaDAO materiaPrimaDAO = new MateriaPrimaDAO();
        ControladorMateriaPrima controladorMateriaPrima = new ControladorMateriaPrima(materiaPrimaDAO, panelMateriaPrima);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItemEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEmpleadosActionPerformed
        // TODO add your handling code here:
        PanelEmpleado panelEmpleado = new PanelEmpleado();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        ControladorEmpleado controladorEmpleado = new ControladorEmpleado(empleadoDAO, panelEmpleado);
    }//GEN-LAST:event_jMenuItemEmpleadosActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        PanelEstadoPedido panelEstadoPedido = new PanelEstadoPedido();
        EstadoPedidoDAO estadoPedidoDAO = new EstadoPedidoDAO();
        ControladorEstadoPedido controladorEstadoPedido = new ControladorEstadoPedido(estadoPedidoDAO, panelEstadoPedido);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        PanelPedido panelPedido = new PanelPedido();
        PedidoDAO pedidoDAO = new PedidoDAO();
        ControladorPedido controladorPedido = new ControladorPedido(pedidoDAO, panelPedido);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItemPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPagosActionPerformed
        // TODO add your handling code here:
        PanelPagos panelPagos = new PanelPagos();
        PagoDAO pagoDAO = new PagoDAO();
        ControladorPago controladorPago = new ControladorPago(pagoDAO, panelPagos);
    }//GEN-LAST:event_jMenuItemPagosActionPerformed

    
    
    
    
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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
                
            }
        });
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AdminProveedores;
    private javax.swing.JMenuItem MenuItemProduccion;
    private javax.swing.JButton btnchat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton jButton1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemEmpleados;
    private javax.swing.JMenuItem jMenuItemPagos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JMenu menuControlMateriaPrima;
    private javax.swing.JMenu menuControlProductos;
    // End of variables declaration//GEN-END:variables
}
