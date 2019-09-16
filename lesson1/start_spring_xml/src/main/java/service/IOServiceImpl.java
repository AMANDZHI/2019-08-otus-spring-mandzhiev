package service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {
    private Scanner scanner;
    private PrintStream printStream;

    public IOServiceImpl(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    public String readString() {
        return scanner.next();
    }

    public void printString(String object) {
        printStream.println(object);
    }
}