package com.example.injector.core;

import com.example.injector.annotation.AutoInjectable;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс для внедрения зависимостей в объекты с использованием рефлексии.
 * Автоматически инициализирует поля, помеченные аннотацией @AutoInjectable.
 * 
 * @author Panova Viktoria
 * @version 1.0
 */
public class Injector {
    
    private Properties properties;
    private String configFile;
    
    /**
     * Создает инжектор с конфигурацией по умолчанию.
     * Использует файл config.properties из classpath.
     */
    public Injector() {
        this("config.properties");
    }
    
    /**
     * Создает инжектор с указанным файлом конфигурации.
     * 
     * @param configFile имя файла конфигурации в classpath
     */
    public Injector(String configFile) {
        this.configFile = configFile;
        properties = new Properties();
        loadProperties();
    }
    
    /**
     * Загружает свойства из файла конфигурации.
     * Вызывается автоматически при создании инжектора.
     */
    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + configFile);
            }
            properties.load(input);
            System.out.println(" Loaded configuration from: " + configFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration file: " + configFile, e);
        }
    }
    
    /**
     * Внедряет зависимости в переданный объект.
     * Находит все поля с аннотацией @AutoInjectable и инициализирует их.
     * 
     * @param object объект для внедрения зависимостей
     * @return тот же объект с внедренными зависимостями
     * @throws IllegalArgumentException если object равен null
     */
    public <T> T inject(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int injectionCount = 0;
        
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                injectField(object, field);
                injectionCount++;
            }
        }
        
        System.out.println(" Injected dependencies into " + injectionCount + " field(s)");
        return object;
    }
    
    /**
     * Внедряет зависимость в конкретное поле объекта.
     * 
     * @param object объект, в который внедряется зависимость
     * @param field поле для внедрения
     */
    private <T> void injectField(T object, Field field) {
        Class<?> fieldType = field.getType();
        String implementationClassName = properties.getProperty(fieldType.getName());
        
        if (implementationClassName == null || implementationClassName.trim().isEmpty()) {
            throw new RuntimeException("No implementation specified in " + configFile + " for interface: " + fieldType.getName());
        }
        
        try {
            Class<?> implementationClass = Class.forName(implementationClassName.trim());
            Object implementationInstance = implementationClass.getDeclaredConstructor().newInstance();
            
            field.setAccessible(true);
            field.set(object, implementationInstance);
            
            System.out.println("   ↳ " + field.getName() + " → " + implementationClass.getSimpleName());
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Implementation class not found: " + implementationClassName, e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of: " + implementationClassName, e);
        }
    }
}