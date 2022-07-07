package atm.command;

import atm.Cell;
import atm.cash.Banknote;

import java.util.List;

public class InfoCommand implements Command {
    @Override
    public int execute(List<Cell> cellList) {
        var sum = 0;
        for (Cell cell : cellList) {
            sum += (cell.getCount() * cell.getBanknote().getValue());
        }
        System.out.println("Balance: " + sum);
        return sum;
    }

    @Override
    public boolean execute(List<Cell> cellList, Banknote banknote, int count) {
        return false;
    }

    @Override
    public List<Banknote> execute(List<Cell> cellList, int amount) {
        return null;
    }
}