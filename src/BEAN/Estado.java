package BEAN;
public class Estado {
    private int idEstado;
    private String descEstado;

    public Estado() {
    }

    public Estado(int idEstado, String descEstado) {
        this.idEstado = idEstado;
        this.descEstado = descEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }
}
