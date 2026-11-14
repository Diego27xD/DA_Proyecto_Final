package BEAN;
public class Etnia {
    private int idEtnia;
    private String descripcion;

    public Etnia() {
    }

    public Etnia(int idEtnia, String descripcion) {
        this.idEtnia = idEtnia;
        this.descripcion = descripcion;
    }

    public int getIdEtnia() {
        return idEtnia;
    }

    public void setIdEtnia(int idEtnia) {
        this.idEtnia = idEtnia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
