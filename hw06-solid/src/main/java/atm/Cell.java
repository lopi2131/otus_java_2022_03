package atm;

import atm.cash.Banknote;

public class Cell {
    Banknote banknote;
    int count;

    public Cell(Banknote banknote, int count) {
        this.banknote = banknote;
        this.count = count;
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public int getCount() {
        return count;
    }
}
