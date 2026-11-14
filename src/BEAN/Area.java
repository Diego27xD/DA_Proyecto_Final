package BEAN;

public class Area {
    private int idArea;
    private String descArea;
    private String descSubArea;

    public Area() {
    }
  
    
    public Area(int idArea, String descArea, String descSubArea) {
        this.idArea = idArea;
        this.descArea = descArea;
        this.descSubArea = descSubArea;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getDescArea() {
        return descArea;
    }

    public void setDescArea(String descArea) {
        this.descArea = descArea;
    }

    public String getDescSubArea() {
        return descSubArea;
    }

    public void setDescSubArea(String descSubArea) {
        this.descSubArea = descSubArea;
    }
    
    
    
    
    
}
