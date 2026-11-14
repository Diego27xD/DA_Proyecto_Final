package BEAN;
public class InstitucionAcad {
    private int idInstitucion;
    private String nombInstitucion;
    private String tipInstitucion;
    private String tlf;
    private String web;

    public InstitucionAcad() {
    }

    public InstitucionAcad(int idInstitucion, String nombInstitucion, String tipInstitucion, String tlf, String web) {
        this.idInstitucion = idInstitucion;
        this.nombInstitucion = nombInstitucion;
        this.tipInstitucion = tipInstitucion;
        this.tlf = tlf;
        this.web = web;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombInstitucion() {
        return nombInstitucion;
    }

    public void setNombInstitucion(String nombInstitucion) {
        this.nombInstitucion = nombInstitucion;
    }

    public String getTipInstitucion() {
        return tipInstitucion;
    }

    public void setTipInstitucion(String tipInstitucion) {
        this.tipInstitucion = tipInstitucion;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
