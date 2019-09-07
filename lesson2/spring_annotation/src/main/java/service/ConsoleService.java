package service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

@Service
public class ConsoleService implements Console<String> {
    public String readConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(reader);
        return scanner.next();
    }

    public void printConsole(String object) {
        System.out.println(object);
    }
}