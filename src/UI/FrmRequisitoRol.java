package UI;

import BEAN.RequisitoRol;
import DAO.RequisitoRolDAO;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class FrmRequisitoRol extends javax.swing.JDialog {
    DefaultTableModel dtm;
    RequisitoRolDAO reqDao;
    int idRol;
    String descAnterior;
    public FrmRequisitoRol(java.awt.Frame parent, boolean modal) {
        this(parent, modal, 0, "");
    }
    public FrmRequisitoRol(java.awt.Frame parent, boolean modal, int idRol, String descRol) {
        super(parent, modal);
        initComponents();
        this.setSize(555, 440);
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
        this.idRol = idRol;
        this.descAnterior = "";
        this.txtIdRol.setText(String.valueOf(idRol));
        this.txtDescRol.setText(descRol);
        dtm = (DefaultTableModel)this.tblRequisitos.getModel();
        this.tblRequisitos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tblRequisitos.getColumnModel().getColumn(0).setPreferredWidth(60);
        this.tblRequisitos.getColumnModel().getColumn(1).setPreferredWidth(400);
        reqDao = new RequisitoRolDAO();
        llenaTblRequisitos();
    }
    public void llenaTblRequisitos(){
        Vector<RequisitoRol> listReq;
        listReq = reqDao.listaRequisitos(true, idRol);
        dtm.setRowCount(0);
        for(int i = 0; i<listReq.size();i++){
            Vector vec = new Vector();
            vec.addElement(listReq.get(i).getIdRol());
            vec.addElement(listReq.get(i).getDescripReq());
            dtm.addRow(vec);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblIdRol = new javax.swing.JLabel();
        lblDescRol = new javax.swing.JLabel();
        lblRequisito = new javax.swing.JLabel();
        txtIdRol = new javax.swing.JTextField();
        txtDescRol = new javax.swing.JTextField();
        txtRequisito = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRequisitos = new javax.swing.JTable();
        btnGrabar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Requisitos del Rol");
        getContentPane().setLayout(null);

        lblTitulo.setFont(lblTitulo.getFont().deriveFont(lblTitulo.getFont().getStyle() | java.awt.Font.BOLD, lblTitulo.getFont().getSize()+4));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("MANTENIMIENTO REQUISITO ROL");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(0, 15, 540, 25);

        lblIdRol.setText("Id Rol");
        getContentPane().add(lblIdRol);
        lblIdRol.setBounds(25, 55, 100, 20);

        lblDescRol.setText("Rol");
        getContentPane().add(lblDescRol);
        lblDescRol.setBounds(25, 90, 100, 20);

        lblRequisito.setText("Requisito");
        getContentPane().add(lblRequisito);
        lblRequisito.setBounds(25, 125, 100, 20);

        txtIdRol.setEditable(false);
        getContentPane().add(txtIdRol);
        txtIdRol.setBounds(130, 53, 100, 25);

        txtDescRol.setEditable(false);
        getContentPane().add(txtDescRol);
        txtDescRol.setBounds(130, 88, 300, 25);

        txtRequisito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRequisitoActionPerformed(evt);
            }
        });
        getContentPane().add(txtRequisito);
        txtRequisito.setBounds(130, 123, 300, 25);

        tblRequisitos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
        
            },
            new String [] {
                "Id Rol", "Requisito"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };
        
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRequisitos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRequisitosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRequisitos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(25, 165, 490, 170);

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGrabar);
        btnGrabar.setBounds(25, 350, 110, 30);

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(150, 350, 110, 30);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(275, 350, 110, 30);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir);
        btnSalir.setBounds(400, 350, 110, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if(valida()==true){
            boolean verif;
            RequisitoRol r;
            r = new RequisitoRol();
            r.setIdRol(idRol);
            r.setDescripReq(this.txtRequisito.getText().trim());
            if(this.btnGrabar.getText().equals("Grabar")){
                verif = this.reqDao.insertaRequisito(r);
                if(verif == true){
                    JOptionPane.showMessageDialog(this, "Requisito registrado");
                }else{
                    JOptionPane.showMessageDialog(this, "No se pudo registrar el requisito");
                }
            }else{
                verif = this.reqDao.actualizaRequisito(r, descAnterior);
                if(verif == true){
                    JOptionPane.showMessageDialog(this, "Requisito actualizado");
                }else{
                    JOptionPane.showMessageDialog(this, "No se pudo actualizar el requisito");
                }
            }
            if(verif == true){
                this.llenaTblRequisitos();
                this.limpia();
            }
        }
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        boolean verif;
        int resp;
        if(this.descAnterior.isEmpty()){
            JOptionPane.showMessageDialog(this, "Seleccione un requisito de la tabla");
            return;
        }
        resp = JOptionPane.showConfirmDialog(this,
                "¿Eliminar el requisito \"" + this.descAnterior + "\"?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION){
            verif = this.reqDao.eliminaRequisito(idRol, descAnterior);
            if(verif == true){
                JOptionPane.showMessageDialog(this, "Requisito eliminado");
                this.llenaTblRequisitos();
                this.limpia();
            }else{
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el requisito");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpia();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtRequisitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRequisitoActionPerformed
        btnGrabarActionPerformed(evt);
    }//GEN-LAST:event_txtRequisitoActionPerformed

    private void tblRequisitosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRequisitosMouseClicked
        int idx;
        idx = this.tblRequisitos.getSelectedRow();
        if(idx >= 0){
            this.descAnterior = dtm.getValueAt(idx, 1).toString();
            this.txtRequisito.setText(this.descAnterior);
            this.btnGrabar.setText("Actualizar");
            this.btnEliminar.setEnabled(true);
        }
    }//GEN-LAST:event_tblRequisitosMouseClicked
    private boolean valida(){
        boolean sw = false;
        boolean mismo;
        String existente;
        String cad = "";
        String r = this.txtRequisito.getText().trim();
        if(r.isEmpty()){
            cad = "Debe registrar el requisito";
        }else{
            for(int i = 0; i<dtm.getRowCount();i++){
                existente = dtm.getValueAt(i, 1).toString();
                mismo = this.btnGrabar.getText().equals("Actualizar") && existente.equals(descAnterior);
                if(existente.equalsIgnoreCase(r) && mismo == false){
                    cad = "Ese requisito ya está registrado para el rol";
                }
            }
        }
        if(cad.isEmpty()){
            sw = true;
        }else{
            JOptionPane.showMessageDialog(this, cad);
            this.txtRequisito.requestFocus();
        }
        return sw;
    }
    private void limpia(){
        this.txtRequisito.setText("");
        this.descAnterior = "";
        this.tblRequisitos.clearSelection();
        this.btnGrabar.setText("Grabar");
        this.btnEliminar.setEnabled(false);
        this.txtRequisito.requestFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescRol;
    private javax.swing.JLabel lblIdRol;
    private javax.swing.JLabel lblRequisito;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblRequisitos;
    private javax.swing.JTextField txtDescRol;
    private javax.swing.JTextField txtIdRol;
    private javax.swing.JTextField txtRequisito;
    // End of variables declaration//GEN-END:variables
}
