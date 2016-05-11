/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Fabrica.Fabrica;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.Estado;
import interfaces.IControladorPedidos;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fede
 */

public class VerInfoPedido extends javax.swing.JInternalFrame {
    int idp;
    Fabrica f = Fabrica.getInstance();
    IControladorPedidos cp = f.getIControladorPedidos();
    DataPedido pedidoActual;
    
    public VerInfoPedido(int id) {
        
        initComponents();
        if (id==-1){
            
            LlenarTablaPedidos();
       
            btnCancelar.setVisible(false);
        }
        else if (id==-2){
            LlenarTablaPedidos();     
            btnCancelar.setVisible(true);
            btnCancelar.setEnabled(false);
        }
        else{
//      
        }
                      
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        vpTblPedidos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        vpTblProductos = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        vpTxtTotal = new javax.swing.JTextField();
        vpTxtEstado = new javax.swing.JTextField();
        vpTxtCliente = new javax.swing.JTextField();
        vpTxtRestaurante = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vpTblEstados = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Información de Pedido");
        setAutoscrolls(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        vpTblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Restaurante", "Cliente", "Fecha", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        vpTblPedidos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        vpTblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vpTblPedidosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(vpTblPedidos);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 494, 330));

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

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 410, 110));

        btnCancelar.setText("Cancelar Pedido");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 350, -1, -1));

        vpTxtTotal.setEditable(false);
        getContentPane().add(vpTxtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 105, 47, -1));

        vpTxtEstado.setEditable(false);
        getContentPane().add(vpTxtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 281, -1));

        vpTxtCliente.setEditable(false);
        getContentPane().add(vpTxtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 55, 281, -1));

        vpTxtRestaurante.setEditable(false);
        getContentPane().add(vpTxtRestaurante, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 281, -1));

        jLabel10.setText("Total:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 105, -1, -1));

        jLabel9.setText("Estado:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, -1, -1));

        jLabel2.setText("Cliente:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 55, -1, -1));

        jLabel1.setText("Restaurante:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, -1, -1));

        vpTblEstados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Estado", "Fecha"
            }
        ));
        vpTblEstados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vpTblEstadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(vpTblEstados);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 410, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void LlenarTablaPedidos(){
        DefaultTableModel tm = (DefaultTableModel) vpTblPedidos.getModel();
        vpTblPedidos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        tm.setRowCount(0);
        Set<DataPedido> pedidos = cp.listarPedidos();
                        
        Iterator<DataPedido> it = pedidos.iterator();
        while (it.hasNext()){
            DataPedido dp = it.next(); 
            String estado;
            if (dp.getEstado() == Estado.Enviado)
                estado = "Enviado";
            else if (dp.getEstado() == Estado.Preparacion)
                estado = "Preparacion";
            else
                estado = "Recibido";
            tm.addRow(new Object[] {dp.getId(), dp.getNickRestaurante(), dp.getNickCliente(), dp.getFecha().toString(), estado});
        }   
    }    
    private void vpTblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vpTblPedidosMouseClicked
        btnCancelar.setEnabled(true);
        DefaultTableModel tm = (DefaultTableModel) vpTblPedidos.getModel();        
        pedidoActual = cp.seleccionarPedido((int) tm.getValueAt(vpTblPedidos.getSelectedRow(), 0));
        LlenarDatosPedido();
    }//GEN-LAST:event_vpTblPedidosMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        pedidoActual = cp.seleccionarPedido(idp);
        if(pedidoActual.getEstado().equals(Estado.Preparacion)){
            cp.cancelarPedidoM();
            JOptionPane.showMessageDialog(null, "El pedido ha sido cancelado.");
            this.dispose();
        }else
            JOptionPane.showMessageDialog(null, "Solo puede cancelar pedidos en estado de Preparacion");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void vpTblEstadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vpTblEstadosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_vpTblEstadosMouseClicked
    private void LlenarDatosPedido()
{
        DataPedido dp = cp.verInfoPedido();
        
        vpTxtCliente.setText(dp.getNickCliente());
        vpTxtRestaurante.setText(dp.getNickRestaurante());          
           
        if (dp.getEstado() == Estado.Preparacion)
            vpTxtEstado.setText("Preparacion");
        else if (dp.getEstado() == Estado.Enviado)
            vpTxtEstado.setText("Enviado");
        else
            vpTxtEstado.setText("Recibido");
        vpTxtTotal.setText(Integer.toString(dp.getPrecioTotal()));
        
        Set<DataProducto> productos = dp.getProductos();
        
        DefaultTableModel tm = (DefaultTableModel) vpTblProductos.getModel();
        tm.setRowCount(0);       
        idp = dp.getId();
        Iterator<DataProducto> it = productos.iterator();
        while (it.hasNext()){
            DataProducto dprod = it.next();
            String tipo;
            if (dprod.getTipo() == 0)
                     tipo = "Individual";
            else
                     tipo = "Promoción";
            tm.addRow(new Object[] {dprod.getNombre(), dprod.getPrecioUnitario(), tipo, dprod.getCantidad(), dprod.getPrecioUnitario()*dprod.getCantidad()});
        }   
        DefaultTableModel te = (DefaultTableModel) vpTblEstados.getModel();
        te.setRowCount(0);
        te.addRow(new Object[] {"preparacion",dp.getEstados()[0]});
        te.addRow(new Object[] {"enviado",dp.getEstados()[1]});
        te.addRow(new Object[] {"recibido",dp.getEstados()[2]});
}
        
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable vpTblEstados;
    private javax.swing.JTable vpTblPedidos;
    private javax.swing.JTable vpTblProductos;
    private javax.swing.JTextField vpTxtCliente;
    private javax.swing.JTextField vpTxtEstado;
    private javax.swing.JTextField vpTxtRestaurante;
    private javax.swing.JTextField vpTxtTotal;
    // End of variables declaration//GEN-END:variables
}
