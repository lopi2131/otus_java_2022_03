package atm.command;

import atm.ATM;

import java.util.ArrayDeque;
import java.util.Queue;

class Executor {
    private final ATM atm;
    private final Queue<Command> commands = new ArrayDeque<>();

    public Executor(ATM atm) {
        this.atm = atm;
    }

    void addCommand(Command command) {
        commands.add(command);
    }

    void executeCommands() {
        Command command;
        while ((command = commands.poll()) != null) {
            var result = command.execute(atm);
            System.out.println("command execution result:" + result);
        }
    }
}