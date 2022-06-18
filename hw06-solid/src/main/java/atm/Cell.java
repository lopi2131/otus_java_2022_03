package atm;

import atm.cash.Banknote;

public class Cell {
    private final Banknote banknote;
    private int count;

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

    public void setCount(int count) {
        this.count += count;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "banknote=" + banknote +
                ", count=" + count +
                '}';
    }
}
