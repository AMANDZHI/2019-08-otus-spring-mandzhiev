package amandzhi.springBoot.service;

import amandzhi.springBoot.executor.TestingApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class Commands {
    private String userName;
    private final TestingApp testingApp;

    @Autowired
    public Commands(TestingApp testingApp) {
        this.testingApp = testingApp;
    }

    private Availability isLogin() {
        return userName == null ? Availability.unavailable("Please enter login command") : Availability.available();
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "amandzhi") String userName) {
        this.userName = userName;
        return String.format("Welcome %s", userName);
    }

    @ShellMethod(value = "Go test", key = {"t", "test"})
    @ShellMethodAvailability(value = "isLogin")
    public void goTest() {
        String result = testingApp.start();
        System.out.println(userName + result);
    }
}