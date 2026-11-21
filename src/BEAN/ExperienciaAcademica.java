/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

/**
 *
 * @author dflor
 */
public class ExperienciaAcademica {
    private int IdInstitucion;
    private String Fech_Ini;
    private String Fech_Fin;
    private String EstadoAvance;
    private String GradoAcad;
    private int FilaOrigen;

    public ExperienciaAcademica() {
    }

    public int getIdInstitucion() {
        return IdInstitucion;
    }

    public void setIdInstitucion(int IdInstitucion) {
        this.IdInstitucion = IdInstitucion;
    }

    public String getFech_Ini() {
        return Fech_Ini;
    }

    public void setFech_Ini(String Fech_Ini) {
        this.Fech_Ini = Fech_Ini;
    }

    public String getFech_Fin() {
        return Fech_Fin;
    }

    public void setFech_Fin(String Fech_Fin) {
        this.Fech_Fin = Fech_Fin;
    }

    public String getEstadoAvance() {
        return EstadoAvance;
    }

    public void setEstadoAvance(String EstadoAvance) {
        this.EstadoAvance = EstadoAvance;
    }

    public String getGradoAcad() {
        return GradoAcad;
    }

    public void setGradoAcad(String GradoAcad) {
        this.GradoAcad = GradoAcad;
    }

    public int getFilaOrigen() {
        return FilaOrigen;
    }

    public void setFilaOrigen(int FilaOrigen) {
        this.FilaOrigen = FilaOrigen;
    }
}
