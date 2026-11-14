package BEAN;

public class TipoContrato {
    private int idTipContrato;
    private String descTipContrato;

    public TipoContrato() {
    }

    public TipoContrato(int idTipContrato, String descTipContrato) {
        this.idTipContrato = idTipContrato;
        this.descTipContrato = descTipContrato;
    }

    public int getIdTipContrato() {
        return idTipContrato;
    }

    public void setIdTipContrato(int idTipContrato) {
        this.idTipContrato = idTipContrato;
    }

    public String getDescTipContrato() {
        return descTipContrato;
    }

    public void setDescTipContrato(String descTipContrato) {
        this.descTipContrato = descTipContrato;
    }
}
