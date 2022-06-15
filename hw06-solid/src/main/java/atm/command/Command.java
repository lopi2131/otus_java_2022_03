package atm.command;

import atm.Atm;
import atm.cash.Banknote;

import java.util.List;

public interface Command {
    int execute(Atm atm);

    boolean execute(Atm atm, Banknote banknote);

    List<Banknote> execute(Atm atm, int sum);
}
