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
public class ResultadoCargaCsv {

    private Vector<CabCv> listaCv = new Vector<CabCv>();
    private Vector<String> listaErrores = new Vector<String>();
    private int cantidadRepetidos;

    public Vector<CabCv> getCvs() {
        return listaCv;
    }

    public Vector<String> getErrores() {
        return listaErrores;
    }

    public int getRepetidas() {
        return cantidadRepetidos;
    }

    public void setRepetidas(int numRegistrosRepetidos) {
        this.cantidadRepetidos = numRegistrosRepetidos;
    }
}
