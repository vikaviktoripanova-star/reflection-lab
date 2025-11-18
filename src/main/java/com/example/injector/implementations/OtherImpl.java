package com.example.injector.implementations;

import com.example.injector.interfaces.SomeInterface;

/**
 * Реализация интерфейса SomeInterface.
 * Выводит букву "B" при вызове метода doSomething.
 * 
 * @author Panova Viktoria
 * @version 1.0
 */
public class OtherImpl implements SomeInterface {
    /**
     * Выполняет действие - выводит "B" в консоль.
     */
    public void doSomething() {
        System.out.println("B");
    }
}