package BEAN;

public class Cargo {

    private int idCargo;
    private String descArea;

    public Cargo() {
    }

    public Cargo(int idCargo, String descArea) {
        this.idCargo = idCargo;
        this.descArea = descArea;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getDescArea() {
        return descArea;
    }

    public void setDescArea(String descArea) {
        this.descArea = descArea;
    }
}
