package amandzhi.springjdbc.io;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

@Service
public class IOService {
    public String readString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(reader);
        return scanner.next();
    }

    public void printString(String object) {
        System.out.println(object);
    }
}