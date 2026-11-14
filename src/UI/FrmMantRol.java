package UI;

import BEAN.Rol;
import DAO.RolDAO;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmMantRol extends javax.swing.JInternalFrame {
    DefaultTableModel dtm;
    RolDAO rolDao;
    int idRol;
    
    public FrmMantRol() {
        initComponents();
        dtm = (DefaultTableModel)this.tblRoles.getModel();
        rolDao = new RolDAO();
        llenaTblRoles(false, "");
    }
    
    public void llenaTblRoles(boolean sw, String cad){
        Vector<Rol> listRoles;
        listRoles = rolDao.listaRoles(sw, cad);
        dtm.setRowCount(0);
        for(int i = 0; i<listRoles.size();i++){
            Vector vec = new Vector();
            vec.addElement(listRoles.get(i).getIdRol());
            vec.addElement(listRoles.get(i).getDescRol());
            vec.addElement(listRoles.get(i).getSueldo());
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
        jLabel4 = new javax.swing.JLabel();
        txtIdRol = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtSueldo = new javax.swing.JTextField();
        btnGrabar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRoles = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Mantenimiento de Rol");

        pnlPrincipal.setPreferredSize(new java.awt.Dimension(860, 400));
        pnlPrincipal.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("MANTENIMIENTO ROL");
        pnlPrincipal.add(jLabel1);
        jLabel1.setBounds(320, 15, 260, 25);

        jLabel2.setText("Id Rol");
        pnlPrincipal.add(jLabel2);
        jLabel2.setBounds(30, 90, 120, 20);

        jLabel3.setText("Descripción");
        pnlPrincipal.add(jLabel3);
        jLabel3.setBounds(30, 135, 120, 20);

        jLabel4.setText("Sueldo");
        pnlPrincipal.add(jLabel4);
        jLabel4.setBounds(30, 180, 120, 20);

        txtIdRol.setEnabled(false);
        pnlPrincipal.add(txtIdRol);
        txtIdRol.setBounds(160, 90, 100, 22);
        pnlPrincipal.add(txtDescripcion);
        txtDescripcion.setBounds(160, 135, 240, 22);
        pnlPrincipal.add(txtSueldo);
        txtSueldo.setBounds(160, 180, 120, 22);

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

        jLabel5.setText("Buscar");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 25, 50, 20);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar);
        txtBuscar.setBounds(80, 25, 310, 22);

        tblRoles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Rol", "Descripción", "Sueldo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRoles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRolesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRoles);

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
            this.llenaTblRoles(false, "");
        }else{
            this.llenaTblRoles(true, this.txtBuscar.getText());
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if(valida()==true){
            Rol r;
            int resultado;
            r = new Rol();
            r.setDescRol(this.txtDescripcion.getText().trim());
            r.setSueldo(Double.parseDouble(this.txtSueldo.getText().trim()));
            if(this.btnGrabar.getText().equals("Grabar")){
                resultado = this.rolDao.insertaRol(r);
            }else{
                r.setIdRol(idRol);
                resultado = this.rolDao.actualizaRol(r);
            }
            if(resultado > 0){
                JOptionPane.showMessageDialog(this, "Registro grabado correctamente");
                this.limpia();
            }else{
                JOptionPane.showMessageDialog(this, "No se pudo grabar el registro");
            }
        }
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void tblRolesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRolesMouseClicked
        int idx;
        idx = this.tblRoles.getSelectedRow();
        if(idx >= 0){
            this.idRol = Integer.parseInt(dtm.getValueAt(idx, 0).toString());
            this.txtIdRol.setText(dtm.getValueAt(idx, 0).toString());
            this.txtDescripcion.setText(dtm.getValueAt(idx, 1).toString());
            this.txtSueldo.setText(dtm.getValueAt(idx, 2).toString());
            this.btnGrabar.setText("Actualizar");
            this.btnEliminar.setEnabled(true);
        }
    }//GEN-LAST:event_tblRolesMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        boolean verif;
        int resp;
        int nContratos;
        int nRequisitos;
        resp = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el rol con Id " + this.idRol + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION){
            nContratos = this.rolDao.cuentaContratos(this.idRol);
            nRequisitos = this.rolDao.cuentaRequisitos(this.idRol);
            if(nContratos > 0 || nRequisitos > 0){
                JOptionPane.showMessageDialog(this, "No es posible la eliminación.\n"
                        + "El rol está asignado en " + nContratos + " contrato(s)\n"
                        + "y tiene " + nRequisitos + " requisito(s) registrado(s).");
            }else{
                verif = this.rolDao.eliminaRol(this.idRol);
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
        String sueldo = this.txtSueldo.getText().trim();
        double valor;
        int idAux = 0;
        if(desc.isEmpty()){
            cad = "Debe registrar la descripción";
        }else if(desc.length() > 100){
            cad = "La descripción no debe superar los 100 caracteres";
        }
        if(sueldo.isEmpty()){
            cad += "\nDebe registrar el sueldo";
        }else{
            try{
                valor = Double.parseDouble(sueldo);
                if(!(valor > 0 && valor <= 99999999.99)){
                    cad += "\nEl sueldo debe ser mayor a 0 y menor a 100000000";
                }
            }catch(NumberFormatException e){
                cad += "\nEl sueldo debe ser un número (use punto para decimales)";
            }
        }
        if(cad.isEmpty()){
            if(this.btnGrabar.getText().equals("Actualizar")){
                idAux = this.idRol;
            }
            if(this.rolDao.existeDescripcion(desc, idAux)){
                cad = "Ya existe un rol con esa descripción";
            }
        }
        if(cad.isEmpty()){
            sw = true;
        }else{
            JOptionPane.showMessageDialog(this, cad.trim());
        }
        return sw;
    }
    
    private void limpia(){
        this.idRol = 0;
        this.txtIdRol.setText("");
        this.txtDescripcion.setText("");
        this.txtSueldo.setText("");
        this.txtBuscar.setText("");
        this.btnGrabar.setText("Grabar");
        this.btnEliminar.setEnabled(false);
        this.llenaTblRoles(false, "");
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTable tblRoles;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtIdRol;
    private javax.swing.JTextField txtSueldo;
    // End of variables declaration//GEN-END:variables
}
