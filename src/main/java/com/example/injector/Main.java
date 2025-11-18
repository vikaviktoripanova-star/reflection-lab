package com.example.injector;

import com.example.injector.core.Injector;
import com.example.injector.core.SomeBean;

/**
 * Главный класс приложения для демонстрации работы внедрения зависимостей.
 * Показывает пример использования инжектора и автоматической инициализации полей.
 * 
 * @author Panova Viktoria
 * @version 1.0
 */
public class Main {
    /**
     * Точка входа в приложение.
     * Демонстрирует работу фреймворка внедрения зависимостей.
     * 
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        System.out.println(" Reflection DI Lab - Application Started\n");
        
        demonstrateBasicInjection();
        demonstrateWithDifferentConfig();
        
        System.out.println("\n Application Completed");
    }
    
    /**
     * Демонстрирует базовое внедрение зависимостей.
     * Показывает состояние полей до и после инъекции.
     */
    private static void demonstrateBasicInjection() {
        System.out.println("=== Basic Injection Demo ===");
        
        Injector injector = new Injector();
        SomeBean bean = new SomeBean();
        
        System.out.println("\n📋 Before injection:");
        System.out.println("   field1: " + (bean.getField1() == null ? "null" : bean.getField1().getClass().getSimpleName()));
        System.out.println("   field2: " + (bean.getField2() == null ? "null" : bean.getField2().getClass().getSimpleName()));
        
        // Внедряем зависимости
        SomeBean injectedBean = injector.inject(bean);
        
        System.out.println("\n After injection:");
        System.out.println("   field1: " + injectedBean.getField1().getClass().getSimpleName());
        System.out.println("   field2: " + injectedBean.getField2().getClass().getSimpleName());
        
        System.out.println("\n Executing business logic:");
        injectedBean.foo();
    }
    
    /**
     * Показывает, как изменить конфигурацию для использования других реализаций.
     * Объясняет процесс изменения файла конфигурации.
     */
    private static void demonstrateWithDifferentConfig() {
        System.out.println("\n\n=== Custom Configuration Demo ===");
        System.out.println("To test with OtherImpl, modify config.properties:");
        System.out.println("Change: com.example.injector.interfaces.SomeInterface=com.example.injector.implementations.SomeImpl");
        System.out.println("To:    com.example.injector.interfaces.SomeInterface=com.example.injector.implementations.OtherImpl");
        System.out.println("Then output will be: BC");
    }
}