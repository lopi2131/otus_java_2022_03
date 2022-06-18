package atm.command;

import atm.Atm;
import atm.cash.Banknote;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Command {
    int execute(Atm atm);

    boolean execute(Atm atm, Banknote banknote, int count) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    List<Banknote> execute(Atm atm, int sum);
}
