package atm.command;

import atm.Atm;
import atm.cash.Banknote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        if (new InfoCommand().execute(atm) < sum) {
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
            }
        }
        if (sum > 0) {
            return null;
        } else {
            return new ArrayList<>(banknote.keySet());
        }
    }
}
