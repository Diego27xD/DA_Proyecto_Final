package UI;

import BEAN.InstitucionAcad;
import DAO.InstitucionAcadDAO;
import UTIL.Util;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmMantInstitucion extends javax.swing.JInternalFrame {
    DefaultTableModel dtm;
    InstitucionAcadDAO instDao;
    int idInst;
    public FrmMantInstitucion() {
        initComponents();
        dtm = (DefaultTableModel)this.tblInstituciones.getModel();
        instDao = new InstitucionAcadDAO();
        llenaTblInstituciones(false, "");
        llenaCmbTipo();
    }
    public void llenaTblInstituciones(boolean sw, String cad){
        Vector<InstitucionAcad> listInst;
        listInst = instDao.listaInstituciones(sw, cad);
        dtm.setRowCount(0);
        for(int i = 0; i<listInst.size();i++){
            Vector vec = new Vector();
            vec.addElement(listInst.get(i).getIdInstitucion());
            vec.addElement(listInst.get(i).getNombInstitucion());
            vec.addElement(listInst.get(i).getTipInstitucion());
            vec.addElement(listInst.get(i).getTlf());
            vec.addElement(listInst.get(i).getWeb());
            dtm.addRow(vec);
        }
    }
    private void llenaCmbTipo(){
        this.cmbTipo.addItem("");
        this.cmbTipo.addItem("Universidad");
        this.cmbTipo.addItem("Instituto");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdInstitucion = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox<>();
        txtTlf = new javax.swing.JTextField();
        txtWeb = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInstituciones = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnGrabar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("MANTENIMIENTO INSTITUCIÓN");

        jLabel2.setText("Id Institución");

        jLabel3.setText("Nombre");

        jLabel4.setText("Tipo");

        jLabel5.setText("Teléfono");

        jLabel6.setText("Web");

        txtIdInstitucion.setEnabled(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(null);

        jLabel7.setText("Buscar");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, 20, 38, 16);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar);
        txtBuscar.setBounds(100, 20, 420, 22);

        tblInstituciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Institución", "Nombre", "Tipo", "Teléfono", "Web"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInstituciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInstitucionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblInstituciones);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 70, 790, 180);

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);
        btnEliminar.setBounds(389, 270, 120, 25);

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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(73, 73, 73)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtWeb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addComponent(txtTlf, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtIdInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(btnGrabar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGap(499, 499, 499)
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
                            .addComponent(txtIdInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGrabar)
                            .addComponent(btnLimpiar)
                            .addComponent(btnSalir))
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 128, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if(this.txtBuscar.getText().isEmpty()){
            this.llenaTblInstituciones(false, "");
        }else{
            this.llenaTblInstituciones(true, this.txtBuscar.getText());
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if(valida()==true){
            Util u = new Util();
            InstitucionAcad i;
            i = new InstitucionAcad();
            i.setNombInstitucion(this.txtNombre.getText().trim());
            i.setTipInstitucion(this.cmbTipo.getSelectedItem().toString());
            i.setTlf(this.txtTlf.getText().trim());
            i.setWeb(this.txtWeb.getText().trim());
            if(this.btnGrabar.getText().equals("Grabar")){
                if(u.repExp("InstitucionAcad", "NombInstitucion", i.getNombInstitucion())){
                    JOptionPane.showMessageDialog(this, "La institución ya está registrada");
                    return;
                }
                this.instDao.insertaInstitucion(i);
            }else{
                i.setIdInstitucion(idInst);
                this.instDao.actualizaInstitucion(i);
            }
            this.llenaTblInstituciones(false, "");
            this.limpia();
        }
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void tblInstitucionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInstitucionesMouseClicked
        int idx;
        idx = this.tblInstituciones.getSelectedRow();
        this.idInst = Integer.parseInt(dtm.getValueAt(idx, 0).toString());
        this.txtIdInstitucion.setText(dtm.getValueAt(idx, 0).toString());
        this.txtNombre.setText(dtm.getValueAt(idx, 1).toString());
        this.cmbTipo.setSelectedItem(dtm.getValueAt(idx,2).toString());
        this.txtTlf.setText(dtm.getValueAt(idx, 3).toString());
        this.txtWeb.setText(dtm.getValueAt(idx, 4).toString());
        this.btnGrabar.setText("Actualizar");
        this.btnEliminar.setEnabled(true);
    }//GEN-LAST:event_tblInstitucionesMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        boolean verif;
        int idx;
        int idinst;
        idx = this.tblInstituciones.getSelectedRow();
        idinst = Integer.parseInt(dtm.getValueAt(idx, 0).toString());
        verif = this.instDao.eliminaInstitucion(idinst);
        if(verif == true){
            JOptionPane.showMessageDialog(this, "Eliminación exitosa");
            llenaTblInstituciones(false, "");
        }else{
            JOptionPane.showMessageDialog(this, "No es posible la eliminación");
        }
        this.btnEliminar.setEnabled(false);
        limpia();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.btnEliminar.setEnabled(false);
        this.tblInstituciones.clearSelection();
        this.limpia();
        this.llenaTblInstituciones(false, "");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private boolean valida(){
        boolean sw = false;
        String cad = "";
        if(this.txtNombre.getText().trim().isEmpty()){
            cad = "Debe registrar el nombre ";
        }else if(this.txtNombre.getText().trim().length() > 150){
            cad = "El nombre no debe superar los 150 caracteres ";
        }
        if(this.cmbTipo.getSelectedItem().toString().isEmpty()){
            cad += "\nDebe seleccionar tipo";
        }
        if(this.txtTlf.getText().trim().length() > 20){
            cad += "\nEl teléfono no debe superar los 20 caracteres";
        }
        if(this.txtWeb.getText().trim().length() > 150){
            cad += "\nLa web no debe superar los 150 caracteres";
        }
        if(cad.isEmpty()){
            sw = true;
        }else{
            JOptionPane.showMessageDialog(this, cad);
        }
        return sw;
    }
    private void limpia(){
        this.txtIdInstitucion.setText("");
        this.txtNombre.setText("");
        this.cmbTipo.setSelectedItem("");
        this.txtTlf.setText("");
        this.txtWeb.setText("");
        this.btnGrabar.setText("Grabar");
        this.txtBuscar.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInstituciones;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtIdInstitucion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTlf;
    private javax.swing.JTextField txtWeb;
    // End of variables declaration//GEN-END:variables
}
