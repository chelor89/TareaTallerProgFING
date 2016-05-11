/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Fabrica.Fabrica;
import dataTypes.DataCliente;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.Estado;
import interfaces.IControladorClientes;
import interfaces.IControladorPedidos;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fede
 */
public class VerInfoCliente extends javax.swing.JInternalFrame {

    Fabrica f = Fabrica.getInstance();
    IControladorClientes cc = f.getIControladorClientes();

    IControladorPedidos cp = f.getIControladorPedidos();

    public VerInfoCliente() {
        initComponents();
        
        LlenarTablaClientes();

    }

    private void LlenarDatosCliente() {

        DataCliente dc = cc.verInfoCliente();
        rcTxtNick.setText(dc.getNick());
        rcTxtNombre.setText(dc.getNombre());
        rcTxtDireccion.setText(dc.getDireccion());
        rcTxtApellido.setText(dc.getApellido());
        rcTxtEmail.setText(dc.getMail());
        rcTxtFecha.setText(dc.getFecha().toString());

        BufferedImage img = dc.getImagen();
        rcLblImagen.setIcon(new ImageIcon(img.getScaledInstance(rcLblImagen.getWidth(), rcLblImagen.getHeight(), Image.SCALE_SMOOTH)));
        LlenarTablaPedidosDeCliente();
    }

    private void LlenarTablaPedidosDeCliente() {

        DefaultTableModel tm = (DefaultTableModel) vcTblPedidos.getModel();
        tm.setRowCount(0);
        Set<DataPedido> pedidos = cc.verInfoCliente().getPedidos();

        Iterator<DataPedido> it = pedidos.iterator();
        while (it.hasNext()) {
            DataPedido dp = it.next();
            tm.addRow(new Object[]{dp.getId(), dp.getNickRestaurante(), dp.getFecha().toString()});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        vcTblPedidos = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        vpTblProductos = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        vpTxtTotal = new javax.swing.JTextField();
        vpTxtEstado = new javax.swing.JTextField();
        vpTxtCliente = new javax.swing.JTextField();
        vpTxtRestaurante = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        rcLblImagen = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rcTxtFecha = new javax.swing.JTextField();
        rcTxtDireccion = new javax.swing.JTextField();
        rcTxtApellido = new javax.swing.JTextField();
        rcTxtNombre = new javax.swing.JTextField();
        rcTxtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rcTxtNick = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Información de Cliente");
        setAutoscrolls(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nick", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane.setViewportView(tblClientes);

        getContentPane().add(jScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 450, 190));

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        vcTblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Restaurante", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vcTblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vcTblPedidosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(vcTblPedidos);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 410, 120));

        jLabel13.setText("Clientes:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del Pedido", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP));
        jPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        vpTblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Precio Unitario", "Tipo", "Cantidad", "SubTotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vpTblProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        vpTblProductos.setFillsViewportHeight(true);
        jScrollPane3.setViewportView(vpTblProductos);

        jPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 400, 140));

        jLabel15.setText("Total:");
        jPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, -1, -1));

        vpTxtTotal.setEditable(false);
        jPanel.add(vpTxtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 105, 47, -1));

        vpTxtEstado.setEditable(false);
        jPanel.add(vpTxtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 281, -1));

        vpTxtCliente.setEditable(false);
        jPanel.add(vpTxtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 55, 281, -1));

        vpTxtRestaurante.setEditable(false);
        jPanel.add(vpTxtRestaurante, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 281, -1));

        jLabel1.setText("Restaurante:");
        jPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel2.setText("Cliente:");
        jPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 55, -1, -1));

        jLabel16.setText("Estado:");
        jPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        getContentPane().add(jPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 430, 290));

        jLabel17.setText("Pedidos:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Información de Cliente"), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rcLblImagen.setBackground(new java.awt.Color(204, 204, 204));
        rcLblImagen.setIconTextGap(0);
        rcLblImagen.setOpaque(true);
        jPanel1.add(rcLblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 130, 120));

        jLabel14.setText("Fecha de Nacimiento:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

        rcTxtFecha.setEditable(false);
        jPanel1.add(rcTxtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 170, -1));

        rcTxtDireccion.setEditable(false);
        jPanel1.add(rcTxtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 125, 170, -1));

        rcTxtApellido.setEditable(false);
        jPanel1.add(rcTxtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 170, -1));

        rcTxtNombre.setEditable(false);
        jPanel1.add(rcTxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 75, 170, -1));

        rcTxtEmail.setEditable(false);
        rcTxtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rcTxtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(rcTxtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 170, -1));

        jLabel9.setText("Correo Electrónico:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        jLabel10.setText("Nombre:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 75, -1, -1));

        jLabel11.setText("Apellido:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        jLabel12.setText("Dirección:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 125, -1, -1));

        rcTxtNick.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(rcTxtNick, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 184, 18));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 460, 210));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        DefaultTableModel tm = (DefaultTableModel) tblClientes.getModel();
        cc.seleccionarCliente(tm.getValueAt(tblClientes.getSelectedRow(), 0).toString());
        LlenarDatosCliente();
       

    }//GEN-LAST:event_tblClientesMouseClicked

    private void rcTxtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rcTxtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rcTxtEmailActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void vcTblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vcTblPedidosMouseClicked

        DefaultTableModel tm = (DefaultTableModel) vcTblPedidos.getModel();
        cp.seleccionarPedido((int) tm.getValueAt(vcTblPedidos.getSelectedRow(), 0));
       
        LlenarTablaProductos();       


    }//GEN-LAST:event_vcTblPedidosMouseClicked
    private void LlenarTablaProductos() {

        DataPedido dp = cp.verInfoPedido();

        vpTxtCliente.setText(dp.getNickCliente());
        vpTxtRestaurante.setText(dp.getNickRestaurante());

        if (dp.getEstado() == Estado.Preparacion) {
            vpTxtEstado.setText("Preparacion");
        } else if (dp.getEstado() == Estado.Enviado) {
            vpTxtEstado.setText("Enviado");
        } else {
            vpTxtEstado.setText("Recibido");
        }
        vpTxtTotal.setText(Integer.toString(dp.getPrecioTotal()));

        Set<DataProducto> productos = dp.getProductos();

        DefaultTableModel tm = (DefaultTableModel) vpTblProductos.getModel();
        tm.setRowCount(0);

        Iterator<DataProducto> it = productos.iterator();
        while (it.hasNext()) {
            DataProducto dprod = it.next();
            String tipo;
            if (dprod.getTipo() == 0) {
                tipo = "Individual";
            } else {
                tipo = "Promoción";
            }
            tm.addRow(new Object[]{dprod.getNombre(), dprod.getPrecioUnitario(), tipo, dprod.getCantidad(), dprod.getPrecioUnitario() * dprod.getCantidad()});
        }
    }

 
    private void LlenarTablaClientes() {
        DefaultTableModel tm = (DefaultTableModel) tblClientes.getModel();
        Set<DataCliente> clientes = cc.listarClientes();

        Iterator<DataCliente> it = clientes.iterator();
        while (it.hasNext()) {

            DataCliente dc = it.next();
            tm.addRow(new Object[]{dc.getNick(), dc.getMail()});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel rcLblImagen;
    private javax.swing.JTextField rcTxtApellido;
    private javax.swing.JTextField rcTxtDireccion;
    private javax.swing.JTextField rcTxtEmail;
    private javax.swing.JTextField rcTxtFecha;
    private javax.swing.JLabel rcTxtNick;
    private javax.swing.JTextField rcTxtNombre;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable vcTblPedidos;
    private javax.swing.JTable vpTblProductos;
    private javax.swing.JTextField vpTxtCliente;
    private javax.swing.JTextField vpTxtEstado;
    private javax.swing.JTextField vpTxtRestaurante;
    private javax.swing.JTextField vpTxtTotal;
    // End of variables declaration//GEN-END:variables
}
