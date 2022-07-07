package atm.command;

import atm.Cell;
import atm.cash.Banknote;

import java.util.List;

public class DepositCommand implements Command {

    @Override
    public int execute(List<Cell> cellList) {
        return 0;
    }

    @Override
    public boolean execute(List<Cell> cellList, Banknote banknote, int count) {
        int i;
        boolean result = false;
        switch (banknote.getValue()) {
            case 100, 200, 500, 1000, 2000, 5000 -> {
                for (i = 0; i < cellList.size(); i++) {
                    if (cellList.get(i).getBanknote().getValue() == banknote.getValue()) {
                        cellList.get(i).setCount(count);
                        result = true;
                    }
                }
                if (!result) {
                    cellList.add(new Cell(banknote, count));
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
    public List<Banknote> execute(List<Cell> cellList, int amount) {
        return null;
    }
}