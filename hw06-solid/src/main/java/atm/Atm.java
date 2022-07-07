package atm;

import atm.cash.Banknote;
import atm.command.Command;
import atm.command.DepositCommand;
import atm.command.InfoCommand;
import atm.command.WithdrawCommand;

import java.util.List;

public class Atm {
    private List<Cell> cellList;
    private final Command depositCommand;
    private final Command infoCommand;
    private final Command withdrawCommand;

    public Atm(List<Cell> cellList) {
        this.cellList = cellList;
        this.depositCommand = new DepositCommand();
        this.infoCommand = new InfoCommand();
        this.withdrawCommand = new WithdrawCommand();
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(List<Cell> cellList) {
        this.cellList = cellList;
    }

    public boolean deposit(Banknote banknote, int count) {
        return depositCommand.execute(cellList, banknote, count);
    }

    public int info() {
        return infoCommand.execute(cellList);
    }

    public List<Banknote> withdraw(int sum) {
        return withdrawCommand.execute(cellList, sum);
    }

    @Override
    public String toString() {
        return "Atm{" +
                "cellList=" + cellList +
                '}';
    }
}
