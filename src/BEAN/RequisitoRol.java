package BEAN;

public class RequisitoRol {

    private int idRol;
    private String descripReq;

    public RequisitoRol(){

    }

    public RequisitoRol(int idRol, String descripReq){
        this.idRol = idRol;
        this.descripReq = descripReq;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getDescripReq() {
        return descripReq;
    }

    public void setDescripReq(String descripReq) {
        this.descripReq = descripReq;
    }

    @Override
    public String toString(){
        return descripReq;
    }
}
