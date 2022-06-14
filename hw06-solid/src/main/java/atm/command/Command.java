package atm.command;

import atm.ATM;

public interface Command {
    String execute(ATM atm);
}
