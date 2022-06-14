package atm;

public class ATM {
    private Cells rurCell;
    private Cells eurCell;
    private Cells usdCell;

    public Cells getRurCell() {
        return rurCell;
    }

    public void setRurCell(Cells rurCell) {
        this.rurCell = rurCell;
    }

    public Cells getEurCell() {
        return eurCell;
    }

    public void setEurCell(Cells eurCell) {
        this.eurCell = eurCell;
    }

    public Cells getUsdCell() {
        return usdCell;
    }

    public void setUsdCell(Cells usdCell) {
        this.usdCell = usdCell;
    }
}
