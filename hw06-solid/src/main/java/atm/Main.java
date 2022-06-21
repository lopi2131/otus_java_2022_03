package atm;

import atm.cash.Banknote;
import atm.command.DepositCommand;
import atm.command.InfoCommand;
import atm.command.WithdrawCommand;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var balance = new InfoCommand();
        var deposit = new DepositCommand();
        var withdraw = new WithdrawCommand();

        List<Cell> cells = new LinkedList<>();
        cells.add(new Cell(Banknote.N100, 2));
        cells.add(new Cell(Banknote.N200, 1));
        cells.add(new Cell(Banknote.N500, 1));
        Atm atm = new Atm(cells);


        deposit.execute(atm, Banknote.N1000, 1); //balance 1900
        withdraw.execute(atm, 500);              //balance 1400
        balance.execute(atm);
        deposit.execute(atm, Banknote.N5000, 1); //balance 6400
        balance.execute(atm);
        withdraw.execute(atm, 10000);  //Недостаточно средств, balance 6400
        withdraw.execute(atm, 2000);  //Нет подходящих купюр, balance 6400
        deposit.execute(atm, Banknote.N2000, 1); //balance 8400
        balance.execute(atm);
        withdraw.execute(atm, 2000); //Выдача 2000
        balance.execute(atm); //balance 6400
    }
}
