package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PrinterOfNumber {
    public static final Logger logger = LoggerFactory.getLogger(PrinterOfNumber.class);
    private String last = "second";
    private int param = 1;
    private boolean isUpward = true;

    public static void main(String[] args) {
        new PrinterOfNumber().action();
    }

    private void action() {

        var thread1 = new Thread(() -> printSequenceOfNumber("first"));
        thread1.setName("Thread-1");
        thread1.start();

        var thread2 = new Thread(() -> printSequenceOfNumber("second"));
        thread2.setName("Thread-2");
        thread2.start();
    }

    public int getParam() {
        if (param == 10) {
            isUpward = false;
        } else if (param == 1) {
            isUpward = true;
        }
        return isUpward ? param++ : param--;
    }

    private synchronized void printSequenceOfNumber(String numberOfThread) {
        PrinterOfNumber printerOfNumber = new PrinterOfNumber();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                while (last.equals(numberOfThread)) {
                    this.wait();
                }
                last = numberOfThread;

                logger.info("{}, param: {}", Thread.currentThread().getName(), printerOfNumber.getParam());

                Thread.sleep(500);
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
