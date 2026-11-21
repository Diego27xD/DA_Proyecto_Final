/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import java.util.Vector;

/**
 *
 * @author dflor
 */
public class CabCv {
    private int Id_CV;
    private int IdEmpleado;
    private Vector<ExperienciaLaboral> listaExpLaborales;
    private Vector<ExperienciaAcademica> listaExpAcademicas;
    
    public CabCv() {
        this.listaExpLaborales = new Vector<ExperienciaLaboral>();
        this.listaExpAcademicas = new Vector<ExperienciaAcademica>();
    }
    
    public int getId_CV() {
        return Id_CV;
    }

    public void setId_CV(int Id_CV) {
        this.Id_CV = Id_CV;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public Vector<ExperienciaLaboral> getExperienciasLaborales() {
        return listaExpLaborales;
    }

    public void setExperienciasLaborales(Vector<ExperienciaLaboral> experienciasLaborales) {
        this.listaExpLaborales = experienciasLaborales;
    }

    public Vector<ExperienciaAcademica> getExperienciasAcademicas() {
        return listaExpAcademicas;
    }

    public void setAcademicas(Vector<ExperienciaAcademica> experienciasAcademicas) {
        this.listaExpAcademicas = experienciasAcademicas;
    }
}
