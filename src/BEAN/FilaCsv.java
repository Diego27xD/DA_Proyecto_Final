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
public class FilaCsv {

    private int numero;
    private String[] datos;

    public FilaCsv(int numero, String[] datos) {
        this.numero = numero;
        this.datos = datos;
    }

    public int getNumero() {
        return numero;
    }

    public String[] getDatos() {
        return datos;
    }
}
