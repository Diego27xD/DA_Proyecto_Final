package UI;

import BEAN.TipoContrato;
import DAO.TipoContratoDAO;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmMantTipoContrato extends javax.swing.JInternalFrame {
    DefaultTableModel dtm;
    TipoContratoDAO tipoDao;
    int idTipo;
    
    public FrmMantTipoContrato() {
        initComponents();
        dtm = (DefaultTableModel)this.tblTipoContratos.getModel();
        tipoDao = new TipoContratoDAO();
        llenaTblTipoContratos(false, "");
    }
    
    public void llenaTblTipoContratos(boolean sw, String cad){
        Vector<TipoContrato> listTipos;
        listTipos = tipoDao.listaTipoContratos(sw, cad);
        dtm.setRowCount(0);
        for(int i = 0; i<listTipos.size();i++){
            Vector vec = new Vector();
            vec.addElement(listTipos.get(i).getIdTipContrato());
            vec.addElement(listTipos.get(i).getDescTipContrato());
            dtm.addRow(vec);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdTipContrato = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        btnGrabar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipoContratos = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Mantenimiento de Tipo de Contrato");

        pnlPrincipal.setPreferredSize(new java.awt.Dimension(860, 400));
        pnlPrincipal.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("MANTENIMIENTO TIPO DE CONTRATO");
        pnlPrincipal.add(jLabel1);
        jLabel1.setBounds(240, 15, 420, 25);

        jLabel2.setText("Id Tipo Contrato");
        pnlPrincipal.add(jLabel2);
        jLabel2.setBounds(30, 90, 120, 20);

        jLabel3.setText("Descripción");
        pnlPrincipal.add(jLabel3);
        jLabel3.setBounds(30, 135, 120, 20);

        txtIdTipContrato.setEnabled(false);
        pnlPrincipal.add(txtIdTipContrato);
        txtIdTipContrato.setBounds(160, 90, 100, 22);
        pnlPrincipal.add(txtDescripcion);
        txtDescripcion.setBounds(160, 135, 240, 22);

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnGrabar);
        btnGrabar.setBounds(30, 340, 110, 25);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnLimpiar);
        btnLimpiar.setBounds(160, 340, 110, 25);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnSalir);
        btnSalir.setBounds(290, 340, 110, 25);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(null);

        jLabel4.setText("Buscar");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 25, 50, 20);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar);
        txtBuscar.setBounds(80, 25, 310, 22);

        tblTipoContratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Tipo Contrato", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTipoContratos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTipoContratosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTipoContratos);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 60, 370, 200);

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);
        btnEliminar.setBounds(270, 275, 120, 25);

        pnlPrincipal.add(jPanel1);
        jPanel1.setBounds(430, 60, 410, 320);

        getContentPane().add(pnlPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if(this.txtBuscar.getText().isEmpty()){
            this.llenaTblTipoContratos(false, "");
        }else{
            this.llenaTblTipoContratos(true, this.txtBuscar.getText());
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if(valida()==true){
            TipoContrato t;
            int resultado;
            t = new TipoContrato();
            t.setDescTipContrato(this.txtDescripcion.getText().trim());
            if(this.btnGrabar.getText().equals("Grabar")){
                resultado = this.tipoDao.insertaTipoContrato(t);
            }else{
                t.setIdTipContrato(idTipo);
                resultado = this.tipoDao.actualizaTipoContrato(t);
            }
            if(resultado > 0){
                JOptionPane.showMessageDialog(this, "Registro grabado correctamente");
                this.limpia();
            }else{
                JOptionPane.showMessageDialog(this, "No se pudo grabar el registro");
            }
        }
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void tblTipoContratosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTipoContratosMouseClicked
        int idx;
        idx = this.tblTipoContratos.getSelectedRow();
        if(idx >= 0){
            this.idTipo = Integer.parseInt(dtm.getValueAt(idx, 0).toString());
            this.txtIdTipContrato.setText(dtm.getValueAt(idx, 0).toString());
            this.txtDescripcion.setText(dtm.getValueAt(idx, 1).toString());
            this.btnGrabar.setText("Actualizar");
            this.btnEliminar.setEnabled(true);
        }
    }//GEN-LAST:event_tblTipoContratosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        boolean verif;
        int resp;
        int nContratos;
        resp = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el tipo de contrato con Id " + this.idTipo + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION){
            nContratos = this.tipoDao.cuentaContratos(this.idTipo);
            if(nContratos > 0){
                JOptionPane.showMessageDialog(this, "No es posible la eliminación.\n"
                        + "El tipo de contrato está asignado en " + nContratos + " contrato(s).");
            }else{
                verif = this.tipoDao.eliminaTipoContrato(this.idTipo);
                if(verif == true){
                    JOptionPane.showMessageDialog(this, "Eliminación exitosa");
                }else{
                    JOptionPane.showMessageDialog(this, "No es posible la eliminación");
                }
            }
            limpia();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpia();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    
    private boolean valida(){
        boolean sw = false;
        String cad = "";
        String desc = this.txtDescripcion.getText().trim();
        int idAux = 0;
        if(desc.isEmpty()){
            cad = "Debe registrar la descripción";
        }else if(desc.length() > 100){
            cad = "La descripción no debe superar los 100 caracteres";
        }else{
            if(this.btnGrabar.getText().equals("Actualizar")){
                idAux = this.idTipo;
            }
            if(this.tipoDao.existeDescripcion(desc, idAux)){
                cad = "Ya existe un tipo de contrato con esa descripción";
            }
        }
        if(cad.isEmpty()){
            sw = true;
        }else{
            JOptionPane.showMessageDialog(this, cad);
        }
        return sw;
    }
    
    private void limpia(){
        this.idTipo = 0;
        this.txtIdTipContrato.setText("");
        this.txtDescripcion.setText("");
        this.txtBuscar.setText("");
        this.btnGrabar.setText("Grabar");
        this.btnEliminar.setEnabled(false);
        this.llenaTblTipoContratos(false, "");
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
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblTipoContratos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtIdTipContrato;
    // End of variables declaration//GEN-END:variables
}
