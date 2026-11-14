package BEAN;
public class EstadoCiv {
    private int idEstCiv;
    private String descrip;

    public EstadoCiv() {
    }

    public EstadoCiv(int idEstCiv, String descrip) {
        this.idEstCiv = idEstCiv;
        this.descrip = descrip;
    }

    public int getIdEstCiv() {
        return idEstCiv;
    }

    public void setIdEstCiv(int idEstCiv) {
        this.idEstCiv = idEstCiv;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
