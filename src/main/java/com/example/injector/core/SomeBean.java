package com.example.injector.core;

import com.example.injector.annotation.AutoInjectable;
import com.example.injector.interfaces.SomeInterface;
import com.example.injector.interfaces.SomeOtherInterface;

/**
 * Пример класса, который использует внедрение зависимостей.
 * Поле field1 и field2 автоматически инициализируются инжектором.
 * 
 * @author Panova Viktoria
 * @version 1.0
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;
    
    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Выполняет действия с внедренными зависимостями.
     * Демонстрирует работу автоматически инициализированных полей.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
    
    /**
     * Возвращает первую зависимость.
     * 
     * @return объект SomeInterface
     */
    public SomeInterface getField1() {
        return field1;
    }
    
    /**
     * Возвращает вторую зависимость.
     * 
     * @return объект SomeOtherInterface
     */
    public SomeOtherInterface getField2() {
        return field2;
    }
}