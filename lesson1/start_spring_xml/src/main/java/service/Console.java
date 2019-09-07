package service;

public interface Console<T> {
    T readConsole();

    void printConsole(T object);
}