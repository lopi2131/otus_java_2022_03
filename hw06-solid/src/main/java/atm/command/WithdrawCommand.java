package atm.command;

import atm.Atm;
import atm.Cell;
import atm.cash.Banknote;

import java.util.*;
import java.util.stream.Collectors;

public class WithdrawCommand implements Command {
    @Override
    public int execute(Atm atm) {
        return 0;
    }

    @Override
    public boolean execute(Atm atm, Banknote banknote, int count) {
        return false;
    }

    @Override
    public List<Banknote> execute(Atm atm, int sum) {
        List<Cell> elements = new LinkedList<>();
        if (new InfoCommand().execute(atm) < sum) {
            System.out.println("Операция невозможна, недостаточно средств!");
            return null;
        }
        Map<Banknote, Integer> banknote = new HashMap<>();
        var cells = atm.getCellList().stream()
                .sorted((e1, e2) -> e2.getBanknote().getValue() - e1.getBanknote().getValue())
                .collect(Collectors.toList());
        for (var cell : cells) {
            int nominalValue = cell.getBanknote().getValue();
            if (sum >= nominalValue) {
                int noteCount = Math.min(sum / nominalValue, cell.getCount());
                banknote.put(cell.getBanknote(), noteCount);
                sum = sum - nominalValue * noteCount;
                elements.add(cell);
            }
        }
        if (sum > 0) {
            System.out.println("Нет подходящих купюр!");
            return null;
        } else {
            for (var cell : elements) {
                atm.getCellList().remove(cell);
            }
            return new ArrayList<>(banknote.keySet());
        }
    }
}
