import atm.Atm;
import atm.Cell;
import atm.cash.Banknote;
import atm.command.DepositCommand;
import atm.command.InfoCommand;
import atm.command.WithdrawCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("АТМ должен уметь: ")
public class AtmTests {
    Atm atm = new Atm(List.of(new Cell(Banknote.N200, 1), new Cell(Banknote.N100, 2)));

    @Test
    @DisplayName("выдавать сумму остатка денежных средств")
    void testBalance() {
        var balance = new InfoCommand();
        assertThat(balance.execute(atm)).isEqualTo(400);
    }

    @Test
    @DisplayName("отказывать в приеме купюры неподдерживаемого номинала")
    void testWrongNominal() {
        var wrongBanknote = Banknote.N500;
        var deposit = new DepositCommand();
        assertThat(deposit.execute(atm, wrongBanknote)).isFalse();
    }

    @Test
    @DisplayName("отказывать в выдаче средств, если их недостаточно")
    void testWithdrawNotMoney() {
        var withdraw = new WithdrawCommand();
        assertThat(withdraw.execute(atm, 2000)).isNull();
    }

    @Test
    @DisplayName("отказывать в выдаче средств, если нет подходящих купюр")
    void testWithdrawBanknoteMissing() {
        var deposit = new DepositCommand();
        var withdraw = new WithdrawCommand();
        deposit.execute(atm, Banknote.N1000);
        assertThat(withdraw.execute(atm, 500)).isNull();
    }

    @Test
    @DisplayName("успешно выдавать сумму минимальным количеством купюр")
    void testWithdraw() {
        var deposit = new DepositCommand();
        var withdraw = new WithdrawCommand();
        var balance = new InfoCommand();
        deposit.execute(atm, Banknote.N100);
        var banknotes = withdraw.execute(atm, 300);
        assertThat(banknotes).isNotNull();
        assertThat(banknotes.stream().map(Banknote::getValue).collect(Collectors.toList()))
                .asList()
                .containsExactlyInAnyOrder(200, 100);
        assertThat(balance.execute(atm)).isEqualTo(400);
    }
}
