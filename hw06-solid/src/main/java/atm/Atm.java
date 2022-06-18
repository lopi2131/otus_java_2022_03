package atm;

import java.util.List;

public class Atm {
    List<Cell> cellList;

    public Atm(List<Cell> cellList) {
        this.cellList = cellList;
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(List<Cell> cellList) {
        this.cellList = cellList;
    }

    @Override
    public String toString() {
        return "Atm{" +
                "cellList=" + cellList +
                '}';
    }
}
