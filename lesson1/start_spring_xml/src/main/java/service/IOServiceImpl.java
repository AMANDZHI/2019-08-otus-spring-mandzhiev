package service;

import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {
    private Scanner scanner;
    private PrintStream printStream;

    public IOServiceImpl(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    public String readString() {
        return scanner.next();
    }

    public void printString(String object) {
        printStream.println(object);
    }
}