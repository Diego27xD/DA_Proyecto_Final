package UI;

import BEAN.Estado;
import DAO.EstadoDAO;
import UTIL.Util;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmMantEstado extends javax.swing.JInternalFrame {
    DefaultTableModel dtm;
    EstadoDAO estDao;
    int idEst;
    public FrmMantEstado() {
        initComponents();
        dtm = (DefaultTableModel)this.tblEstados.getModel();
        estDao = new EstadoDAO();
        llenaTbl(false, "");
    }
    public void llenaTbl(boolean sw, String cad){
        Vector<Estado> lista;
        lista = estDao.listaEstados(sw, cad);
        dtm.setRowCount(0);
        for(int i = 0; i<lista.size();i++){
            Vector vec = new Vector();
            vec.addElement(lista.get(i).getIdEstado());
            vec.addElement(lista.get(i).getDescEstado());
            dtm.addRow(vec);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdEstado = new javax.swing.JTextField();
        txtDescEstado = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstados = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnGrabar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("MANTENIMIENTO ESTADO");

        jLabel2.setText("Id Estado");

        jLabel3.setText("Descripción");

        txtIdEstado.setEnabled(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(null);

        jLabel4.setText("Buscar");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 20, 32, 14);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar);
        txtBuscar.setBounds(100, 20, 420, 20);

        tblEstados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Estado", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEstados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEstadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEstados);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 70, 490, 180);

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);
        btnEliminar.setBounds(389, 270, 120, 23);

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(btnGrabar)
                .addGap(189, 189, 189)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
            .addGroup(layout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtIdEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDescEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGrabar)
                            .addComponent(btnLimpiar)
                            .addComponent(btnSalir))
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 97, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if(this.txtBuscar.getText().isEmpty()){
            this.llenaTbl(false, "");
        }else{
            this.llenaTbl(true, this.txtBuscar.getText());
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if(valida()==true){
            Util u = new Util();
            Estado est;
            est = new Estado();
            est.setDescEstado(this.txtDescEstado.getText().trim());
            if(this.btnGrabar.getText().equals("Grabar")){
                if(u.repExp("Estado", "DescEstado", est.getDescEstado())){
                    JOptionPane.showMessageDialog(this, "El estado ya está registrado");
                    return;
                }
                this.estDao.insertaEstado(est);
            }else{
                est.setIdEstado(idEst);
                this.estDao.actualizaEstado(est);
            }
            this.llenaTbl(false, "");
            this.limpia();
        }
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void tblEstadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEstadosMouseClicked
        int idx;
        idx = this.tblEstados.getSelectedRow();
        this.idEst = Integer.parseInt(dtm.getValueAt(idx, 0).toString());
        this.txtIdEstado.setText(dtm.getValueAt(idx, 0).toString());
        this.txtDescEstado.setText(dtm.getValueAt(idx, 1).toString());
        this.btnGrabar.setText("Actualizar");
        this.btnEliminar.setEnabled(true);
    }//GEN-LAST:event_tblEstadosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        boolean verif;
        int idx;
        int id;
        idx = this.tblEstados.getSelectedRow();
        id = Integer.parseInt(dtm.getValueAt(idx, 0).toString());
        verif = this.estDao.eliminaEstado(id);
        if(verif == true){
            JOptionPane.showMessageDialog(this, "Eliminación exitosa");
            llenaTbl(false, "");
        }else{
            JOptionPane.showMessageDialog(this, "No es posible la eliminación");
        }
        this.btnEliminar.setEnabled(false);
        limpia();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.btnEliminar.setEnabled(false);
        this.tblEstados.clearSelection();
        this.limpia();
        this.llenaTbl(false, "");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private boolean valida(){
        boolean sw = false;
        String cad = "";
        if(this.txtDescEstado.getText().trim().isEmpty()){
            cad = "Debe registrar la descripción del estado";
        }else if(this.txtDescEstado.getText().trim().length() > 50){
            cad = "La descripción no debe superar los 50 caracteres";
        }
        if(cad.isEmpty()){
            sw = true;
        }else{
            JOptionPane.showMessageDialog(this, cad);
        }
        return sw;
    }
    private void limpia(){
        this.txtIdEstado.setText("");
        this.txtDescEstado.setText("");
        this.btnGrabar.setText("Grabar");
        this.txtBuscar.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEstados;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDescEstado;
    private javax.swing.JTextField txtIdEstado;
    // End of variables declaration//GEN-END:variables
}
