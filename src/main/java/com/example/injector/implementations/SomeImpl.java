package com.example.injector.implementations;

import com.example.injector.interfaces.SomeInterface;

/**
 * Реализация интерфейса SomeInterface.
 * Выводит букву "A" при вызове метода doSomething.
 * 
 * @author Panova Viktoria
 * @version 1.0
 */
public class SomeImpl implements SomeInterface {
    /**
     * Выполняет действие - выводит "A" в консоль.
     */
    public void doSomething() {
        System.out.println("A");
    }
}