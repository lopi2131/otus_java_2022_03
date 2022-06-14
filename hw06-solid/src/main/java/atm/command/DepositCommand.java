package atm.command;

import atm.ATM;

import java.util.HashMap;
import java.util.Map;

public class DepositCommand implements Command{
    @Override
    public String execute(ATM atm) {
        Map<String, Integer> currencyCell = new HashMap<>();
        currencyCell.put("USD",100);
        return null;
    }
}
