package com.example.injector.implementations;

import com.example.injector.interfaces.SomeOtherInterface;

/**
 * Реализация интерфейса SomeOtherInterface.
 * Выводит букву "C" при вызове метода doSomeOther.
 * 
 * @author Panova Viktoria
 * @version 1.0
 */
public class SODoer implements SomeOtherInterface {
    /**
     * Выполняет другое действие - выводит "C" в консоль.
     */
    public void doSomeOther() {
        System.out.println("C");
    }
}