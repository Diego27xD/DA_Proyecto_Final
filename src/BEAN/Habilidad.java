package BEAN;
public class Habilidad {
    private int idHabilidad;
    private String descHab;

    public Habilidad() {
    }

    public Habilidad(int idHabilidad, String descHab) {
        this.idHabilidad = idHabilidad;
        this.descHab = descHab;
    }

    public int getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(int idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public String getDescHab() {
        return descHab;
    }

    public void setDescHab(String descHab) {
        this.descHab = descHab;
    }
}
