import atm.Atm;
import atm.Cell;
import atm.cash.Banknote;
import atm.command.DepositCommand;
import atm.command.InfoCommand;
import atm.command.WithdrawCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("АТМ должен уметь: ")
public class AtmTests {
    Atm atm;

    @BeforeEach
    void setUp() {
        List<Cell> cells = new LinkedList<>();
        cells.add(new Cell(Banknote.N100, 2));
        cells.add(new Cell(Banknote.N200, 1));
        atm = new Atm(cells);
    }


    @Test
    @DisplayName("выдавать сумму остатка денежных средств")
    void testBalance() {
        assertThat(atm.info()).isEqualTo(400);
    }

    @Test
    @DisplayName("отказывать в приеме купюры неподдерживаемого номинала")
    void testWrongNominal() {
        assertThat(atm.deposit(Banknote.WRONG, 1)).isFalse();
    }

    @Test
    @DisplayName("отказывать в выдаче средств, если их недостаточно")
    void testWithdrawNotMoney() {
        assertThat(atm.withdraw(2000)).isNull();
    }

    @Test
    @DisplayName("отказывать в выдаче средств, если нет подходящих купюр")
    void testWithdrawBanknoteMissing() {
        assertThat(atm.deposit(Banknote.N1000, 1)).isTrue();
        assertThat(atm.withdraw(500)).isNull();
    }

    @Test
    @DisplayName("успешно выдавать сумму минимальным количеством купюр")
    void testWithdraw() {
        atm.deposit(Banknote.N100, 1);
        var banknotes = atm.withdraw(300);
        assertThat(banknotes).isNotNull();
        assertThat(banknotes.stream()
                .map(Banknote::getValue)
                .collect(Collectors.toList()))
                .asList()
                .containsExactlyInAnyOrder(200, 100);
        assertThat(atm.info()).isEqualTo(0);
    }
}
