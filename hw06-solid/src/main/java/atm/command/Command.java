package atm.command;

import atm.Cell;
import atm.cash.Banknote;
import lombok.SneakyThrows;

import java.util.List;

public interface Command {
    int execute(List<Cell> cellList);

    @SneakyThrows
    boolean execute(List<Cell> cellList, Banknote banknote, int count);

    List<Banknote> execute(List<Cell> cellList, int sum);
}