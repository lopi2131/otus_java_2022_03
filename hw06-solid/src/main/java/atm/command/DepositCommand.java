package atm.command;

import atm.Atm;
import atm.Cell;
import atm.cash.Banknote;

import java.util.List;

public class DepositCommand implements Command {

    @Override
    public int execute(Atm atm) {
        return 0;
    }

    @Override
    public boolean execute(Atm atm, Banknote banknote) {
        for (Cell cell : atm.getCellList()) {
            if (banknote.getValue() == cell.getBanknote().getValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Banknote> execute(Atm atm, int amount) {
        return null;
    }
}
