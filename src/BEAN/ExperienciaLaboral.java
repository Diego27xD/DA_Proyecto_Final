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
public class ExperienciaLaboral {
    private int IdEmpresa;
    private String Fech_Ini;
    private String Fech_Fin;
    private String DescRol;
    private int FilaOrigen;

    public ExperienciaLaboral() {
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int IdEmpresa) {
        this.IdEmpresa = IdEmpresa;
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

    public String getDescRol() {
        return DescRol;
    }

    public void setDescRol(String DescRol) {
        this.DescRol = DescRol;
    }

    public int getFilaOrigen() {
        return FilaOrigen;
    }

    public void setFilaOrigen(int FilaOrigen) {
        this.FilaOrigen = FilaOrigen;
    }
}
