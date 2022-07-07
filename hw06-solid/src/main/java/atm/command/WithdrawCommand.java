package atm.command;

import atm.Cell;
import atm.cash.Banknote;

import java.util.*;
import java.util.stream.Collectors;

public class WithdrawCommand implements Command {
    @Override
    public int execute(List<Cell> cellList) {
        return 0;
    }

    @Override
    public boolean execute(List<Cell> cellList, Banknote banknote, int count) {
        return false;
    }

    @Override
    public List<Banknote> execute(List<Cell> cellList, int sum) {
        List<Cell> elements = new LinkedList<>();
        if (new InfoCommand().execute(cellList) < sum) {
            System.out.println("Операция невозможна, недостаточно средств!");
            return null;
        }
        Map<Banknote, Integer> banknote = new HashMap<>();
        var cells = cellList.stream()
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
                cellList.remove(cell);
            }
            return new ArrayList<>(banknote.keySet());
        }
    }
}