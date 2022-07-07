package atm;

import atm.cash.Banknote;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Cell> cells = new LinkedList<>();
        cells.add(new Cell(Banknote.N100, 2));
        cells.add(new Cell(Banknote.N200, 1));
        cells.add(new Cell(Banknote.N500, 1));
        var atm = new Atm(cells);

        atm.deposit(Banknote.N1000, 1);//balance 1900
        atm.deposit(Banknote.N1000, -1);//balance 1900
        atm.withdraw(500);
        atm.info();//balance 1400
        atm.deposit(Banknote.N5000, 1);
        atm.info(); //balance 6400
        atm.withdraw(10000);//Недостаточно средств, balance 6400
        atm.withdraw(2000);//Нет подходящих купюр, balance 6400
        atm.deposit(Banknote.N2000, 1);
        atm.info(); //balance 8400
        atm.withdraw(2000);
        atm.info();//balance 6400
    }
}