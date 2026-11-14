package BEAN;

public class Rol {
    private int idRol;
    private String descRol;
    private double sueldo;

    public Rol() {
    }

    public Rol(int idRol, String descRol, double sueldo) {
        this.idRol = idRol;
        this.descRol = descRol;
        this.sueldo = sueldo;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getDescRol() {
        return descRol;
    }

    public void setDescRol(String descRol) {
        this.descRol = descRol;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
}
