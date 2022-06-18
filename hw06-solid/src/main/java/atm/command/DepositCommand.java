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
    public boolean execute(Atm atm, Banknote banknote, int count) {
        int i;
        boolean result = false;
        switch (banknote.getValue()) {
            case 100, 200, 500, 1000, 2000, 5000 -> {
                for (i = 0; i < atm.getCellList().size(); i++) {
                    if (atm.getCellList().get(i).getBanknote().getValue() == banknote.getValue()) {
                        atm.getCellList().get(i).setCount(count);
                        result = true;
                    }
                }
                if (!result) {
                    atm.getCellList().add(new Cell(banknote, count));
                    result = true;
                }
            }
            default -> {
                System.out.println("Операция не может быть выполнена");
            }
        }
        return result;
    }

    @Override
    public List<Banknote> execute(Atm atm, int amount) {
        return null;
    }
}
