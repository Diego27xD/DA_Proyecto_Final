package UI;
import BEAN.Area;
import DAO.AreaDAO;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class FrmMantArea extends javax.swing.JInternalFrame {
    DefaultTableModel dtm;
    AreaDAO areaDao;
    int idArea;
    
    public FrmMantArea() {
        initComponents();
        dtm = (DefaultTableModel)this.tblAreas.getModel();
        areaDao = new AreaDAO();
        this.btnEliminar.setEnabled(false);
        llenaTblAreas(false, "");
    }
    public void llenaTblAreas(boolean sw, String cad){
        Vector<Area> listAreas;
        listAreas = areaDao.listaAreas(sw, cad);
        dtm.setRowCount(0);

        for(int i = 0; i < listAreas.size(); i++){
            Vector vec = new Vector();
            vec.addElement(listAreas.get(i).getIdArea());
            vec.addElement(listAreas.get(i).getDescArea());
            vec.addElement(listAreas.get(i).getDescSubArea());
            dtm.addRow(vec);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblIdArea = new javax.swing.JLabel();
        txtIdArea = new javax.swing.JTextField();
        lblDescArea = new javax.swing.JLabel();
        txtDescArea = new javax.swing.JTextField();
        lblDescSubArea = new javax.swing.JLabel();
        txtDescSubArea = new javax.swing.JTextField();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAreas = new javax.swing.JTable();
        btnGrabar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        lblTitulo.setText("MANTENIMIENTO DE ÁREA");

        lblIdArea.setText("Id Área");

        lblDescArea.setText("Descripción Área");

        lblDescSubArea.setText("Descripción Subárea");

        lblBuscar.setText("Buscar");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        tblAreas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id Área", "Descripción Área", "Descripción Subárea"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAreas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAreasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAreas);

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

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTitulo)
                            .addComponent(lblBuscar))
                        .addGap(56, 56, 56)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGrabar)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblIdArea)
                                .addComponent(lblDescArea)
                                .addComponent(lblDescSubArea)))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtIdArea)
                                .addComponent(txtDescArea)
                                .addComponent(txtDescSubArea, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                            .addComponent(btnLimpiar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnEliminar)
                                .addGap(235, 235, 235)
                                .addComponent(btnSalir)
                                .addGap(57, 57, 57)))))
                .addGap(98, 98, 98))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblTitulo)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdArea)
                            .addComponent(txtIdArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBuscar)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblDescArea)
                                    .addComponent(txtDescArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblDescSubArea)
                                    .addComponent(txtDescSubArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGrabar)
                            .addComponent(btnLimpiar)
                            .addComponent(btnEliminar))
                        .addContainerGap(70, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir)
                        .addGap(62, 62, 62))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        if(this.txtBuscar.getText().isEmpty()){
            this.llenaTblAreas(false, "");
        }else{
            this.llenaTblAreas(true, this.txtBuscar.getText());
        }
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if(valida()==true){
            Area a;
            a = new Area();

            a.setDescArea(this.txtDescArea.getText());
            a.setDescSubArea(this.txtDescSubArea.getText());

        if(this.btnGrabar.getText().equals("Grabar")){
            this.areaDao.insertaArea(a);
        }else{
            a.setIdArea(idArea);
            this.areaDao.actualizaArea(a);
        }

        this.llenaTblAreas(false, "");
        this.limpia();
        }
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void tblAreasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAreasMouseClicked
        int idx;
        idx = this.tblAreas.getSelectedRow();

        this.idArea = Integer.parseInt(dtm.getValueAt(idx, 0).toString());
        this.txtIdArea.setText(dtm.getValueAt(idx, 0).toString());
        this.txtDescArea.setText(dtm.getValueAt(idx, 1).toString());
        this.txtDescSubArea.setText(dtm.getValueAt(idx, 2) == null ? "" : dtm.getValueAt(idx, 2).toString());

        this.btnGrabar.setText("Actualizar");
        this.btnEliminar.setEnabled(true);
    }//GEN-LAST:event_tblAreasMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        boolean verif;
        int idx;
        int idarea;

        idx = this.tblAreas.getSelectedRow();
        idarea = Integer.parseInt(dtm.getValueAt(idx, 0).toString());

        verif = this.areaDao.eliminaArea(idarea);

        if(verif == true){
            JOptionPane.showMessageDialog(this, "Eliminación exitosa");
            llenaTblAreas(false, "");
        }else{
            JOptionPane.showMessageDialog(this, "No es posible la eliminación");
        }

this.btnEliminar.setEnabled(false);
limpia();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.limpia();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if(this.txtBuscar.getText().isEmpty()){
            this.llenaTblAreas(false, "");
        }else{
            this.llenaTblAreas(true, this.txtBuscar.getText());
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private boolean valida(){
    boolean sw = false;
    String cad = "";

    if(this.txtDescArea.getText().isEmpty()){
        cad = "Debe registrar la descripción del área";
    }

    if(cad.isEmpty()){
        sw = true;
    }else{
        JOptionPane.showMessageDialog(this, cad);
    }

    return sw;
}
    private void limpia(){
    this.txtIdArea.setText("");
    this.txtDescArea.setText("");
    this.txtDescSubArea.setText("");
    this.btnGrabar.setText("Grabar");
    this.txtBuscar.setText("");
    this.btnEliminar.setEnabled(false);
}
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDescArea;
    private javax.swing.JLabel lblDescSubArea;
    private javax.swing.JLabel lblIdArea;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblAreas;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDescArea;
    private javax.swing.JTextField txtDescSubArea;
    private javax.swing.JTextField txtIdArea;
    // End of variables declaration//GEN-END:variables
}
