package com.example.injector.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для пометки полей, которые нужно автоматически инициализировать.
 * Используется фреймворком внедрения зависимостей для автоматического
 * заполнения полей объектами.
 * 
 * @author Panova Viktoria
 * @version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
}