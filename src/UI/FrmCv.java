package UI;

import BEAN.CabCv;
import BEAN.ExperienciaLaboral;
import BEAN.ExperienciaAcademica;
import BEAN.ResultadoCargaCsv;
import DAO.CvDAO;
import UTIL.CvValidation;
import UTIL.CvCsv;
import UTIL.CvPlantilla;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmCv extends javax.swing.JInternalFrame {

    private CvDAO cvDao;
    private DefaultTableModel dtm, dtmLab, dtmAcad;
    private Vector<Vector> empleados, empresas, instituciones;
    private CabCv cv;
    private int idxLab = -1, idxAcad = -1;
    private boolean cambios, cargando = true;

    public FrmCv() {
        initComponents();
        cvDao = new CvDAO();
        dtm = (DefaultTableModel) tblCV.getModel();
        dtmLab = (DefaultTableModel) tblLaboral.getModel();
        dtmAcad = (DefaultTableModel) tblAcademica.getModel();
        cv = new CabCv();
        try {
            cargaOpciones();
            llenaTblCV();
            limpia();
        } catch (Exception e) {
            muestraError(e.getMessage());
        }
        cargando = false;
    }

    private void cargaOpciones() throws Exception {
        cargando = true;
        empleados = cvDao.listaEmpleados();
        if (empleados == null) {
            throw new Exception(cvDao.getError());
        }
        empresas = cvDao.listaEmpresas();
        if (empresas == null) {
            throw new Exception(cvDao.getError());
        }
        instituciones = cvDao.listaInstituciones();
        if (instituciones == null) {
            throw new Exception(cvDao.getError());
        }
        llenaCombo(cmbEmpleado, empleados, "Seleccione empleado");
        llenaCombo(cmbEmpresa, empresas, "Seleccione empresa");
        llenaCombo(cmbInstitucion, instituciones, "Seleccione institución");
        llenaCombo(cmbFiltroEmpleado, empleados, "Todos los empleados");
        llenaCombo(cmbFiltroEmpresa, empresas, "Todas las empresas");
        llenaCombo(cmbFiltroInstitucion, instituciones, "Todas las instituciones");
        cargando = false;
    }

    private void llenaCombo(javax.swing.JComboBox combo, Vector<Vector> lista, String primera) {
        combo.removeAllItems();
        combo.addItem(primera);
        for (int i = 0; i < lista.size(); i++) {
            combo.addItem(lista.get(i).get(0) + " - " + lista.get(i).get(1));
        }
    }

    private int elegido(javax.swing.JComboBox combo, Vector<Vector> lista) {
        int idx = combo.getSelectedIndex();
        return idx <= 0 || lista == null ? 0 : Integer.parseInt(lista.get(idx - 1).get(0).toString());
    }

    private void selecciona(javax.swing.JComboBox combo, Vector<Vector> lista, int id) {
        combo.setSelectedIndex(0);
        for (int i = 0; i < lista.size(); i++) {
            if (Integer.parseInt(lista.get(i).get(0).toString()) == id) {
                combo.setSelectedIndex(i + 1);
            }
        }
    }

    private String nombre(Vector<Vector> lista, int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (Integer.parseInt(lista.get(i).get(0).toString()) == id) {
                return id + " - " + lista.get(i).get(1);
            }
        }
        return "" + id;
    }

    private void llenaTblCV() throws Exception {
        Vector<Vector> lista = cvDao.listaCV(elegido(cmbFiltroEmpleado, empleados), elegido(cmbFiltroEmpresa, empresas), elegido(cmbFiltroInstitucion, instituciones));
        if (lista == null) {
            throw new Exception(cvDao.getError());
        }
        dtm.setRowCount(0);
        for (int i = 0; i < lista.size(); i++) {
            dtm.addRow(lista.get(i));
        }
    }

    private void llenaDetalles() {
        dtmLab.setRowCount(0);
        dtmAcad.setRowCount(0);
        for (int i = 0; i < cv.getExperienciasLaborales().size(); i++) {
            ExperienciaLaboral exp = cv.getExperienciasLaborales().get(i);
            dtmLab.addRow(new Object[]{nombre(empresas, exp.getIdEmpresa()), exp.getFech_Ini(), exp.getFech_Fin(), exp.getDescRol()});
        }
        for (int i = 0; i < cv.getExperienciasAcademicas().size(); i++) {
            ExperienciaAcademica exp = cv.getExperienciasAcademicas().get(i);
            dtmAcad.addRow(new Object[]{nombre(instituciones, exp.getIdInstitucion()), exp.getFech_Ini(), exp.getFech_Fin(), exp.getEstadoAvance(), exp.getGradoAcad()});
        }
    }

    private void limpiaLaboral() {
        idxLab = -1;
        cmbEmpresa.setSelectedIndex(0);
        txtIniLab.setText("");
        txtFinLab.setText("");
        txtRol.setText("");
        btnAgregarLab.setText("Agregar laboral");
        tblLaboral.clearSelection();
    }

    private void limpiaAcademica() {
        idxAcad = -1;
        cmbInstitucion.setSelectedIndex(0);
        txtIniAcad.setText("");
        txtFinAcad.setText("");
        txtAvance.setText("");
        txtGrado.setText("");
        btnAgregarAcad.setText("Agregar");
        tblAcademica.clearSelection();
    }

    private void limpia() {
        cargando = true;
        cv = new CabCv();
        txtIdCV.setText("");
        cmbEmpleado.setSelectedIndex(0);
        limpiaLaboral();
        limpiaAcademica();
        llenaDetalles();
        tblCV.clearSelection();
        cambios = false;
        cargando = false;
        btnGrabar.setText("Grabar CV");
        lblEstado.setText("Los detalles se guardan al pulsar Grabar CV. Las fechas finales pueden quedar vacías.");
    }

    private ExperienciaLaboral editorLaboral() {
        ExperienciaLaboral exp = new ExperienciaLaboral();
        exp.setIdEmpresa(elegido(cmbEmpresa, empresas));
        exp.setFech_Ini(txtIniLab.getText());
        exp.setFech_Fin(txtFinLab.getText());
        exp.setDescRol(txtRol.getText());
        return exp;
    }

    private ExperienciaAcademica editorAcademico() {
        ExperienciaAcademica exp = new ExperienciaAcademica();
        exp.setIdInstitucion(elegido(cmbInstitucion, instituciones));
        exp.setFech_Ini(txtIniAcad.getText());
        exp.setFech_Fin(txtFinAcad.getText());
        exp.setEstadoAvance(txtAvance.getText());
        exp.setGradoAcad(txtGrado.getText());
        return exp;
    }

    private boolean pendienteLaboral() {
        ExperienciaLaboral exp = editorLaboral();
        if (idxLab >= 0 && idxLab < cv.getExperienciasLaborales().size()) {
            return !CvValidation.mismaLaboral(exp, cv.getExperienciasLaborales().get(idxLab));
        }
        return exp.getIdEmpresa() != 0 || !CvValidation.texto(exp.getFech_Ini()).isEmpty() || !CvValidation.texto(exp.getFech_Fin()).isEmpty() || !CvValidation.texto(exp.getDescRol()).isEmpty();
    }

    private boolean pendienteAcademica() {
        ExperienciaAcademica exp = editorAcademico();
        if (idxAcad >= 0 && idxAcad < cv.getExperienciasAcademicas().size()) {
            return !CvValidation.mismaAcademica(exp, cv.getExperienciasAcademicas().get(idxAcad));
        }
        return exp.getIdInstitucion() != 0 || !CvValidation.texto(exp.getFech_Ini()).isEmpty() || !CvValidation.texto(exp.getFech_Fin()).isEmpty() || !CvValidation.texto(exp.getEstadoAvance()).isEmpty() || !CvValidation.texto(exp.getGradoAcad()).isEmpty();
    }

    private boolean puedeDescartar() {
        return !(cambios || pendienteLaboral() || pendienteAcademica()) || JOptionPane.showConfirmDialog(this, "Hay cambios sin guardar. ¿Desea descartarlos?", "CV", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void muestraError(String mensaje) {
        javax.swing.JTextArea texto = new javax.swing.JTextArea(mensaje == null ? "No se pudo completar la operación." : mensaje, 18, 85);
        texto.setEditable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setCaretPosition(0);
        JOptionPane.showMessageDialog(this, new javax.swing.JScrollPane(texto), "Revise la operación", JOptionPane.ERROR_MESSAGE);
    }

    private void btnAgregarLabActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnAgregarLabActionPerformed
        CabCv prueba = new CabCv();
        prueba.setIdEmpleado(elegido(cmbEmpleado, empleados));
        for (int i = 0; i < cv.getExperienciasLaborales().size(); i++) {
            if (i != idxLab) {
                prueba.getExperienciasLaborales().addElement(cv.getExperienciasLaborales().get(i));
            }
        }
        ExperienciaLaboral exp = editorLaboral();
        prueba.getExperienciasLaborales().addElement(exp);
        Vector<String> errores = new Vector<String>();
        CvValidation.validar(prueba, empleados, empresas, instituciones, errores);
        if (!errores.isEmpty()) {
            muestraError(CvValidation.errores(errores));
            return;
        }
        if (idxLab < 0) {
            cv.getExperienciasLaborales().addElement(exp);
        } else {
            cv.getExperienciasLaborales().set(idxLab, exp);
        }
        cambios = true;
        limpiaLaboral();
        llenaDetalles();
        lblEstado.setText("Experiencia laboral preparada. Pulse Grabar CV para guardar los cambios.");
    }//GEN-LAST:event_btnAgregarLabActionPerformed
    private void btnAgregarAcadActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnAgregarAcadActionPerformed
        CabCv prueba = new CabCv();
        prueba.setIdEmpleado(elegido(cmbEmpleado, empleados));
        for (int i = 0; i < cv.getExperienciasAcademicas().size(); i++) {
            if (i != idxAcad) {
                prueba.getExperienciasAcademicas().addElement(cv.getExperienciasAcademicas().get(i));
            }
        }
        ExperienciaAcademica exp = editorAcademico();
        prueba.getExperienciasAcademicas().addElement(exp);
        Vector<String> errores = new Vector<String>();
        CvValidation.validar(prueba, empleados, empresas, instituciones, errores);
        if (!errores.isEmpty()) {
            muestraError(CvValidation.errores(errores));
            return;
        }
        if (idxAcad < 0) {
            cv.getExperienciasAcademicas().addElement(exp);
        } else {
            cv.getExperienciasAcademicas().set(idxAcad, exp);
        }
        cambios = true;
        limpiaAcademica();
        llenaDetalles();
        lblEstado.setText("Experiencia académica preparada. Pulse Grabar CV para guardar los cambios.");
    }//GEN-LAST:event_btnAgregarAcadActionPerformed
    private void tblLaboralMouseClicked(java.awt.event.MouseEvent evt){//GEN-FIRST:event_tblLaboralMouseClicked
        int idx = tblLaboral.getSelectedRow();
        if (idx < 0) {
            return;
        }
        if (idx != idxLab && pendienteLaboral() && JOptionPane.showConfirmDialog(this, "¿Descartar la edición laboral pendiente?", "CV", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            tblLaboral.clearSelection();
            return;
        }
        idxLab = idx;
        ExperienciaLaboral exp = cv.getExperienciasLaborales().get(idx);
        selecciona(cmbEmpresa, empresas, exp.getIdEmpresa());
        txtIniLab.setText(exp.getFech_Ini());
        txtFinLab.setText(exp.getFech_Fin());
        txtRol.setText(exp.getDescRol());
        btnAgregarLab.setText("Actualizar laboral");
    }//GEN-LAST:event_tblLaboralMouseClicked
    private void tblAcademicaMouseClicked(java.awt.event.MouseEvent evt){//GEN-FIRST:event_tblAcademicaMouseClicked
        int idx = tblAcademica.getSelectedRow();
        if (idx < 0) {
            return;
        }
        if (idx != idxAcad && pendienteAcademica() && JOptionPane.showConfirmDialog(this, "¿Descartar la edición académica pendiente?", "CV", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            tblAcademica.clearSelection();
            return;
        }
        idxAcad = idx;
        ExperienciaAcademica exp = cv.getExperienciasAcademicas().get(idx);
        selecciona(cmbInstitucion, instituciones, exp.getIdInstitucion());
        txtIniAcad.setText(exp.getFech_Ini());
        txtFinAcad.setText(exp.getFech_Fin());
        txtAvance.setText(exp.getEstadoAvance());
        txtGrado.setText(exp.getGradoAcad());
        btnAgregarAcad.setText("Actualizar");
    }//GEN-LAST:event_tblAcademicaMouseClicked
    private void btnQuitarLabActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnQuitarLabActionPerformed
        int idx = tblLaboral.getSelectedRow();
        if (idx < 0) {
            muestraError("Seleccione una experiencia laboral.");
            return;
        }
        cv.getExperienciasLaborales().remove(idx);
        cambios = true;
        limpiaLaboral();
        llenaDetalles();
        lblEstado.setText("Experiencia quitada de la edición. Pulse Grabar CV para confirmar.");
    }//GEN-LAST:event_btnQuitarLabActionPerformed
    private void btnQuitarAcadActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnQuitarAcadActionPerformed
        int idx = tblAcademica.getSelectedRow();
        if (idx < 0) {
            muestraError("Seleccione una experiencia académica.");
            return;
        }
        cv.getExperienciasAcademicas().remove(idx);
        cambios = true;
        limpiaAcademica();
        llenaDetalles();
        lblEstado.setText("Experiencia quitada de la edición. Pulse Grabar CV para confirmar.");
    }//GEN-LAST:event_btnQuitarAcadActionPerformed
    private void btnLimpiarLabActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnLimpiarLabActionPerformed
        limpiaLaboral();
    }//GEN-LAST:event_btnLimpiarLabActionPerformed
    private void btnLimpiarAcadActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnLimpiarAcadActionPerformed
        limpiaAcademica();
    }//GEN-LAST:event_btnLimpiarAcadActionPerformed
    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnGrabarActionPerformed
        if (pendienteLaboral() || pendienteAcademica()) {
            muestraError("Hay una experiencia editada que aún no está en su subgrilla. Pulse Agregar/Actualizar o limpie el editor antes de guardar el CV.");
            return;
        }
        cv.setIdEmpleado(elegido(cmbEmpleado, empleados));
        Vector<String> errores = new Vector<String>();
        CvValidation.validar(cv, empleados, empresas, instituciones, errores);
        if (!errores.isEmpty()) {
            muestraError(CvValidation.errores(errores));
            return;
        }
        try {
            int id = cvDao.guardaCV(cv);
            if (id == 0) {
                muestraError(cvDao.getError());
                return;
            }
            cv.setId_CV(id);
            cambios = false;
            txtIdCV.setText("" + id);
            btnGrabar.setText("Actualizar CV");
            limpiaLaboral();
            limpiaAcademica();
            try {
                CabCv guardado = cvDao.cargaCV(id);
                if (guardado == null) {
                    throw new Exception(cvDao.getError());
                }
                cv = guardado;
                llenaDetalles();
                llenaTblCV();
            } catch (Exception e) {
                muestraError("El CV sí se guardó, pero no se pudo actualizar la pantalla. Use Filtrar para recargarla.\n" + e.getMessage());
            }
            lblEstado.setText("CV guardado satisfactoriamente.");
            JOptionPane.showMessageDialog(this, "CV guardado satisfactoriamente.");
        } catch (Exception e) {
            muestraError(e.getMessage());
        }
    }//GEN-LAST:event_btnGrabarActionPerformed
    private void tblCVMouseClicked(java.awt.event.MouseEvent evt){//GEN-FIRST:event_tblCVMouseClicked
        int idx = tblCV.getSelectedRow();
        if (idx < 0) {
            return;
        }
        if (!puedeDescartar()) {
            tblCV.clearSelection();
            return;
        }
        try {
            CabCv seleccionado = cvDao.cargaCV(Integer.parseInt(dtm.getValueAt(idx, 0).toString()));
            if (seleccionado == null) {
                muestraError(cvDao.getError());
                return;
            }
            cargando = true;
            cv = seleccionado;
            txtIdCV.setText("" + cv.getId_CV());
            selecciona(cmbEmpleado, empleados, cv.getIdEmpleado());
            limpiaLaboral();
            limpiaAcademica();
            llenaDetalles();
            cambios = false;
            btnGrabar.setText("Actualizar CV");
            lblEstado.setText("CV seleccionado. Agregue, actualice o quite experiencias y pulse Actualizar CV.");
        } catch (Exception e) {
            muestraError(e.getMessage());
        } finally {
            cargando = false;
        }
    }//GEN-LAST:event_tblCVMouseClicked
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnNuevoActionPerformed
        if (puedeDescartar())
            limpia();
    }//GEN-LAST:event_btnNuevoActionPerformed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnSalirActionPerformed
        if (puedeDescartar())
            dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void cmbEmpleadoActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_cmbEmpleadoActionPerformed
        if (!cargando)
            cambios = true;
    }//GEN-LAST:event_cmbEmpleadoActionPerformed
    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt){//GEN-FIRST:event_formInternalFrameClosing
        btnSalirActionPerformed(null);
    }//GEN-LAST:event_formInternalFrameClosing
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnEliminarActionPerformed
        if (cv.getId_CV() == 0) {
            muestraError("Seleccione un CV guardado.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar este CV y todas sus experiencias laborales y académicas?", "Eliminar CV", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            if (!cvDao.eliminaCV(cv.getId_CV())) {
                muestraError(cvDao.getError());
                return;
            }
            limpia();
            llenaTblCV();
            JOptionPane.showMessageDialog(this, "CV eliminado.");
        } catch (Exception e) {
            muestraError(e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnFiltrarActionPerformed
        try {
            llenaTblCV();
        } catch (Exception e) {
            muestraError(e.getMessage());
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed
    private void btnLimpiarFiltrosActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnLimpiarFiltrosActionPerformed
        cmbFiltroEmpleado.setSelectedIndex(0);
        cmbFiltroEmpresa.setSelectedIndex(0);
        cmbFiltroInstitucion.setSelectedIndex(0);
        btnFiltrarActionPerformed(evt);
    }//GEN-LAST:event_btnLimpiarFiltrosActionPerformed
    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnExportarActionPerformed
        javax.swing.JFileChooser dialogo = new javax.swing.JFileChooser();
        dialogo.setDialogTitle("Guardar plantilla Excel");
        dialogo.setSelectedFile(new java.io.File("Plantilla_CV.xlsx"));
        dialogo.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Plantilla Excel (*.xlsx)", "xlsx"));
        if (dialogo.showSaveDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION) {
            return;
        }
        java.io.File elegida = dialogo.getSelectedFile();
        final java.io.File archivo = elegida.getName().toLowerCase(java.util.Locale.ROOT).endsWith(".xlsx") ? elegida : new java.io.File(elegida.getAbsolutePath() + ".xlsx");
        if (archivo.exists() && JOptionPane.showConfirmDialog(this, "El archivo ya existe. ¿Reemplazarlo?", "Plantilla", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            Vector<Vector> empleadosCSV = cvDao.listaEmpleados();
            if (empleadosCSV == null) {
                muestraError(cvDao.getError());
                return;
            }
            Vector<Vector> empresasCSV = cvDao.listaEmpresas();
            if (empresasCSV == null) {
                muestraError(cvDao.getError());
                return;
            }
            Vector<Vector> institucionesCSV = cvDao.listaInstituciones();
            if (institucionesCSV == null) {
                muestraError(cvDao.getError());
                return;
            }
            byte[] datos = CvPlantilla.generar(empleadosCSV, empresasCSV, institucionesCSV);
            java.nio.file.Files.write(archivo.toPath(), datos);
            lblEstado.setText("Plantilla guardada. Complete la hoja y guárdela como CSV UTF-8 para importar.");
            JOptionPane.showMessageDialog(this, "Plantilla XLSX guardada.\nComplete la hoja y guárdela como CSV UTF-8.\nConserve las diez columnas y sus encabezados.");
        } catch (Exception e) {
            muestraError(e.getMessage());
        }
    }//GEN-LAST:event_btnExportarActionPerformed
    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt){//GEN-FIRST:event_btnImportarActionPerformed
        if (cambios || pendienteLaboral() || pendienteAcademica()) {
            muestraError("Guarde o limpie los cambios del formulario antes de importar.");
            return;
        }
        javax.swing.JFileChooser dialogo = new javax.swing.JFileChooser();
        dialogo.setDialogTitle("Importar CSV de CV");
        dialogo.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV (*.csv)", "csv"));
        if (dialogo.showOpenDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION) {
            return;
        }
        final java.io.File archivo = dialogo.getSelectedFile();
        try {
            Vector<Vector> empleadosCSV = cvDao.listaEmpleados();
            if (empleadosCSV == null) {
                muestraError(cvDao.getError());
                return;
            }
            Vector<Vector> empresasCSV = cvDao.listaEmpresas();
            if (empresasCSV == null) {
                muestraError(cvDao.getError());
                return;
            }
            Vector<Vector> institucionesCSV = cvDao.listaInstituciones();
            if (institucionesCSV == null) {
                muestraError(cvDao.getError());
                return;
            }
            ResultadoCargaCsv lote = CvCsv.preparar(CvCsv.leer(archivo), empleadosCSV, empresasCSV, institucionesCSV);
            if (!lote.getErrores().isEmpty()) {
                muestraError(CvValidation.errores(lote.getErrores()));
                return;
            }
            int[] total = cvDao.importar(lote.getCvs());
            if (total == null) {
                muestraError(cvDao.getError());
                return;
            }
            total[3] += lote.getRepetidas();
            JOptionPane.showMessageDialog(this, "Importación realizada satisfactoriamente.\nCV nuevos: " + total[0]
                + "\nExperiencias laborales nuevas: " + total[1] + "\nExperiencias académicas nuevas: " + total[2]
                + "\nExperiencias idénticas omitidas: " + total[3]);
            lblEstado.setText("Importación realizada satisfactoriamente.");
        } catch (Exception e) {
            muestraError(e.getMessage());
            return;
        }
        try {
            cargaOpciones();
            limpia();
            llenaTblCV();
        } catch (Exception e) {
            muestraError("La importación se guardó, pero no se pudo recargar la pantalla.\n" + e.getMessage());
        }
    }//GEN-LAST:event_btnImportarActionPerformed

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollContenido = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtIdCV = new javax.swing.JTextField();
        lblEmpleado = new javax.swing.JLabel();
        cmbEmpleado = new javax.swing.JComboBox();
        btnGrabar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblFiltros = new javax.swing.JLabel();
        cmbFiltroEmpleado = new javax.swing.JComboBox();
        cmbFiltroEmpresa = new javax.swing.JComboBox();
        cmbFiltroInstitucion = new javax.swing.JComboBox();
        btnFiltrar = new javax.swing.JButton();
        btnLimpiarFiltros = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        btnImportar = new javax.swing.JButton();
        lblFlujo = new javax.swing.JLabel();
        lblLaboral = new javax.swing.JLabel();
        lblAcademica = new javax.swing.JLabel();
        lblEmpresa = new javax.swing.JLabel();
        cmbEmpresa = new javax.swing.JComboBox();
        lblInstitucion = new javax.swing.JLabel();
        cmbInstitucion = new javax.swing.JComboBox();
        lblIniLab = new javax.swing.JLabel();
        txtIniLab = new javax.swing.JTextField();
        lblFinLab = new javax.swing.JLabel();
        txtFinLab = new javax.swing.JTextField();
        lblIniAcad = new javax.swing.JLabel();
        txtIniAcad = new javax.swing.JTextField();
        lblFinAcad = new javax.swing.JLabel();
        txtFinAcad = new javax.swing.JTextField();
        lblRol = new javax.swing.JLabel();
        txtRol = new javax.swing.JTextField();
        btnAgregarLab = new javax.swing.JButton();
        btnQuitarLab = new javax.swing.JButton();
        lblAvance = new javax.swing.JLabel();
        txtAvance = new javax.swing.JTextField();
        lblGrado = new javax.swing.JLabel();
        txtGrado = new javax.swing.JTextField();
        btnAgregarAcad = new javax.swing.JButton();
        btnQuitarAcad = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        btnLimpiarLab = new javax.swing.JButton();
        btnLimpiarAcad = new javax.swing.JButton();
        scrollCV = new javax.swing.JScrollPane();
        tblCV = new javax.swing.JTable();
        scrollLaboral = new javax.swing.JScrollPane();
        tblLaboral = new javax.swing.JTable();
        scrollAcademica = new javax.swing.JScrollPane();
        tblAcademica = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("CV - Experiencias laborales y académicas");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        panel.setPreferredSize(new java.awt.Dimension(1200, 840));
        panel.setLayout(null);

        lblTitulo.setText("MANTENIMIENTO CV");
        panel.add(lblTitulo);
        lblTitulo.setBounds(10, 10, 590, 25);

        lblId.setText("Id CV");
        panel.add(lblId);
        lblId.setBounds(10, 45, 80, 25);

        txtIdCV.setEnabled(false);
        panel.add(txtIdCV);
        txtIdCV.setBounds(100, 45, 90, 25);

        lblEmpleado.setText("Empleado");
        panel.add(lblEmpleado);
        lblEmpleado.setBounds(10, 80, 85, 25);

        cmbEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEmpleadoActionPerformed(evt);
            }
        });
        panel.add(cmbEmpleado);
        cmbEmpleado.setBounds(100, 80, 500, 25);

        btnGrabar.setText("Grabar CV");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });
        panel.add(btnGrabar);
        btnGrabar.setBounds(10, 115, 125, 28);

        btnNuevo.setText("Nuevo / Limpiar");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        panel.add(btnNuevo);
        btnNuevo.setBounds(145, 115, 160, 28);

        btnEliminar.setText("Eliminar CV");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panel.add(btnEliminar);
        btnEliminar.setBounds(315, 115, 140, 28);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panel.add(btnSalir);
        btnSalir.setBounds(465, 115, 135, 28);

        lblFiltros.setText("Filtros");
        panel.add(lblFiltros);
        lblFiltros.setBounds(630, 10, 560, 25);

        panel.add(cmbFiltroEmpleado);
        cmbFiltroEmpleado.setBounds(630, 45, 560, 25);

        panel.add(cmbFiltroEmpresa);
        cmbFiltroEmpresa.setBounds(630, 80, 560, 25);

        panel.add(cmbFiltroInstitucion);
        cmbFiltroInstitucion.setBounds(630, 115, 560, 25);

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        panel.add(btnFiltrar);
        btnFiltrar.setBounds(630, 150, 130, 28);

        btnLimpiarFiltros.setText("Limpiar filtros");
        btnLimpiarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFiltrosActionPerformed(evt);
            }
        });
        panel.add(btnLimpiarFiltros);
        btnLimpiarFiltros.setBounds(770, 150, 160, 28);

        btnExportar.setText("Exportar plantilla");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        panel.add(btnExportar);
        btnExportar.setBounds(630, 190, 180, 30);

        btnImportar.setText("Importar CSV");
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });
        panel.add(btnImportar);
        btnImportar.setBounds(820, 190, 160, 30);
        panel.add(lblFlujo);
        lblFlujo.setBounds(630, 230, 560, 30);

        lblLaboral.setText("EXPERIENCIAS LABORALES");
        panel.add(lblLaboral);
        lblLaboral.setBounds(10, 160, 590, 25);

        lblAcademica.setText("EXPERIENCIAS ACADÉMICAS");
        panel.add(lblAcademica);
        lblAcademica.setBounds(10, 480, 590, 25);

        lblEmpresa.setText("Empresa");
        panel.add(lblEmpresa);
        lblEmpresa.setBounds(10, 190, 80, 25);

        panel.add(cmbEmpresa);
        cmbEmpresa.setBounds(100, 190, 500, 25);

        lblInstitucion.setText("Institución");
        panel.add(lblInstitucion);
        lblInstitucion.setBounds(10, 510, 85, 25);

        panel.add(cmbInstitucion);
        cmbInstitucion.setBounds(100, 510, 500, 25);

        lblIniLab.setText("Inicio (yyyy-MM-dd)");
        panel.add(lblIniLab);
        lblIniLab.setBounds(10, 225, 150, 25);
        panel.add(txtIniLab);
        txtIniLab.setBounds(160, 225, 130, 25);

        lblFinLab.setText("Fin (opcional)");
        panel.add(lblFinLab);
        lblFinLab.setBounds(310, 225, 110, 25);
        panel.add(txtFinLab);
        txtFinLab.setBounds(430, 225, 170, 25);

        lblIniAcad.setText("Inicio (yyyy-MM-dd)");
        panel.add(lblIniAcad);
        lblIniAcad.setBounds(10, 545, 150, 25);
        panel.add(txtIniAcad);
        txtIniAcad.setBounds(160, 545, 130, 25);

        lblFinAcad.setText("Fin (opcional)");
        panel.add(lblFinAcad);
        lblFinAcad.setBounds(310, 545, 110, 25);
        panel.add(txtFinAcad);
        txtFinAcad.setBounds(430, 545, 170, 25);

        lblRol.setText("Descripción de rol");
        panel.add(lblRol);
        lblRol.setBounds(10, 255, 590, 20);
        panel.add(txtRol);
        txtRol.setBounds(10, 280, 590, 25);

        btnAgregarLab.setText("Agregar laboral");
        btnAgregarLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLabActionPerformed(evt);
            }
        });
        panel.add(btnAgregarLab);
        btnAgregarLab.setBounds(10, 315, 180, 28);

        btnQuitarLab.setText("Quitar laboral");
        btnQuitarLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarLabActionPerformed(evt);
            }
        });
        panel.add(btnQuitarLab);
        btnQuitarLab.setBounds(200, 315, 160, 28);

        lblAvance.setText("Estado de avance");
        panel.add(lblAvance);
        lblAvance.setBounds(10, 575, 280, 20);
        panel.add(txtAvance);
        txtAvance.setBounds(10, 600, 280, 25);

        lblGrado.setText("Grado académico");
        panel.add(lblGrado);
        lblGrado.setBounds(310, 575, 290, 20);
        panel.add(txtGrado);
        txtGrado.setBounds(310, 600, 290, 25);

        btnAgregarAcad.setText("Agregar");
        btnAgregarAcad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAcadActionPerformed(evt);
            }
        });
        panel.add(btnAgregarAcad);
        btnAgregarAcad.setBounds(10, 635, 180, 28);

        btnQuitarAcad.setText("Quitar");
        btnQuitarAcad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarAcadActionPerformed(evt);
            }
        });
        panel.add(btnQuitarAcad);
        btnQuitarAcad.setBounds(200, 635, 160, 28);
        panel.add(lblEstado);
        lblEstado.setBounds(10, 800, 1180, 25);

        btnLimpiarLab.setText("Limpiar laboral");
        btnLimpiarLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarLabActionPerformed(evt);
            }
        });
        panel.add(btnLimpiarLab);
        btnLimpiarLab.setBounds(370, 315, 230, 28);

        btnLimpiarAcad.setText("Limpiar académica");
        btnLimpiarAcad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarAcadActionPerformed(evt);
            }
        });
        panel.add(btnLimpiarAcad);
        btnLimpiarAcad.setBounds(370, 635, 230, 28);

        tblCV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id CV", "Id empleado", "Empleado", "Laborales", "Académicas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCVMouseClicked(evt);
            }
        });
        scrollCV.setViewportView(tblCV);

        panel.add(scrollCV);
        scrollCV.setBounds(630, 270, 560, 515);

        tblLaboral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Empresa", "Inicio", "Fin", "Descripción de rol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLaboral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLaboralMouseClicked(evt);
            }
        });
        scrollLaboral.setViewportView(tblLaboral);

        panel.add(scrollLaboral);
        scrollLaboral.setBounds(10, 350, 590, 115);

        tblAcademica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Institución", "Inicio", "Fin", "Estado avance", "Grado académico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAcademica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAcademicaMouseClicked(evt);
            }
        });
        scrollAcademica.setViewportView(tblAcademica);

        panel.add(scrollAcademica);
        scrollAcademica.setBounds(10, 670, 590, 115);

        scrollContenido.setViewportView(panel);

        getContentPane().add(scrollContenido, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 1240, 900);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarAcad;
    private javax.swing.JButton btnAgregarLab;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnImportar;
    private javax.swing.JButton btnLimpiarAcad;
    private javax.swing.JButton btnLimpiarFiltros;
    private javax.swing.JButton btnLimpiarLab;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnQuitarAcad;
    private javax.swing.JButton btnQuitarLab;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbEmpleado;
    private javax.swing.JComboBox cmbEmpresa;
    private javax.swing.JComboBox cmbFiltroEmpleado;
    private javax.swing.JComboBox cmbFiltroEmpresa;
    private javax.swing.JComboBox cmbFiltroInstitucion;
    private javax.swing.JComboBox cmbInstitucion;
    private javax.swing.JLabel lblAcademica;
    private javax.swing.JLabel lblAvance;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFiltros;
    private javax.swing.JLabel lblFinAcad;
    private javax.swing.JLabel lblFinLab;
    private javax.swing.JLabel lblFlujo;
    private javax.swing.JLabel lblGrado;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIniAcad;
    private javax.swing.JLabel lblIniLab;
    private javax.swing.JLabel lblInstitucion;
    private javax.swing.JLabel lblLaboral;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollAcademica;
    private javax.swing.JScrollPane scrollCV;
    private javax.swing.JScrollPane scrollContenido;
    private javax.swing.JScrollPane scrollLaboral;
    private javax.swing.JTable tblAcademica;
    private javax.swing.JTable tblCV;
    private javax.swing.JTable tblLaboral;
    private javax.swing.JTextField txtAvance;
    private javax.swing.JTextField txtFinAcad;
    private javax.swing.JTextField txtFinLab;
    private javax.swing.JTextField txtGrado;
    private javax.swing.JTextField txtIdCV;
    private javax.swing.JTextField txtIniAcad;
    private javax.swing.JTextField txtIniLab;
    private javax.swing.JTextField txtRol;
    // End of variables declaration//GEN-END:variables
}
