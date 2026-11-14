package BEAN;

public class Genero {

    private int idGenero;
    private String descrip;

    public Genero() {
    }

    public Genero(int idGenero, String descrip) {
        this.idGenero = idGenero;
        this.descrip = descrip;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
