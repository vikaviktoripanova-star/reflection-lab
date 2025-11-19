# Лабораторная работа: Reflection DI Framework

## Описание проекта

Реализация фреймворка для внедрения зависимостей с использованием Java Reflection. 
Проект демонстрирует принципы работы Dependency Injection через аннотации и конфигурационные файлы.

## Цель работы

- Изучить механизмы рефлексии в Java
- Реализовать систему внедрения зависимостей
- Освоить работу с аннотациями и конфигурационными файлами
- Написать unit-тесты для проверки функциональности

## Функциональность

- Автоматическое внедрение зависимостей через аннотацию @AutoInjectable
- Конфигурация через properties-файлы
- Рефлексия для доступа к приватным полям
- Поддержка интерфейсов и их реализаций

## Структура проекта

```
reflection-lab/
├── src/
│   ├── main/
│   │   ├── java/com/example/injector/
│   │   │   ├── annotation/AutoInjectable.java
│   │   │   ├── core/
│   │   │   │   ├── Injector.java
│   │   │   │   └── SomeBean.java
│   │   │   ├── implementations/
│   │   │   │   ├── SomeImpl.java
│   │   │   │   ├── OtherImpl.java
│   │   │   │   └── SODoer.java
│   │   │   ├── interfaces/
│   │   │   │   ├── SomeInterface.java
│   │   │   │   └── SomeOtherInterface.java
│   │   │   └── Main.java
│   │   └── resources/config.properties
│   └── test/
│       ├── java/com/example/injector/InjectorTest.java
│       └── resources/test-config.properties
├── pom.xml
└── README.md
```

## Использование

### 1. Пометить поля аннотацией

```java
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;
    
    @AutoInjectable
    private SomeOtherInterface field2;
    
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}
```

### 2. Настроить конфигурацию

Файл `config.properties`:
```properties
com.example.injector.interfaces.SomeInterface=com.example.injector.implementations.SomeImpl
com.example.injector.interfaces.SomeOtherInterface=com.example.injector.implementations.SODoer
```

### 3. Использовать инжектор

```java
Injector injector = new Injector();
SomeBean bean = injector.inject(new SomeBean());
bean.foo(); // Выведет: A C
```

## Запуск проекта

### Компиляция и запуск
```bash
mvn clean compile
mvn exec:java
```

### Запуск тестов
```bash
mvn test
```

### Генерация JavaDoc в директорию target/javadoc
```bash
mvn javadoc:javadoc
```

## Пример вывода

```
Reflection DI Lab - Application Started

=== Basic Injection Demo ===

Before injection:
   field1: null
   field2: null

Loaded configuration from: config.properties
   ↳ field1 → SomeImpl
   ↳ field2 → SODoer
Injected dependencies into 2 field(s)

After injection:
   field1: SomeImpl
   field2: SODoer

Executing business logic:
A
C

Application Completed
```

## Изменение конфигурации

Чтобы изменить реализацию, отредактируйте `config.properties`:

```properties
com.example.injector.interfaces.SomeInterface=com.example.injector.implementations.OtherImpl
```

Тогда вывод изменится на: `B C`

## Тестирование

Проект включает unit-тесты, проверяющие:
- Корректность внедрения зависимостей
- Правильность типов реализаций
- Обработку исключительных ситуаций
- Работоспособность бизнес-логики

## Технологии

- Java 11
- Maven
- JUnit 5
- Java Reflection API

