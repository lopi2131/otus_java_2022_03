package atm.command;

import atm.Atm;
import atm.Cell;
import atm.cash.Banknote;

import java.util.List;

public class InfoCommand implements Command {
    @Override
    public int execute(Atm atm) {
        var sum = 0;
        for (Cell cell : atm.getCellList()) {
            sum += (cell.getCount() * cell.getBanknote().getValue());
        }
        System.out.println("Balance: " + sum);
        return sum;
    }

    @Override
    public boolean execute(Atm atm, Banknote banknote, int count) {
        return false;
    }

    @Override
    public List<Banknote> execute(Atm atm, int amount) {
        return null;
    }
}
