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
}
