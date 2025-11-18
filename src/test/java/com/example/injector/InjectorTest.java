package com.example.injector;

import com.example.injector.core.Injector;
import com.example.injector.core.SomeBean;
import com.example.injector.implementations.SomeImpl;
import com.example.injector.implementations.SODoer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки работы инжектора зависимостей.
 * Содержит unit-тесты для основных сценариев использования.
 * 
 * @author Panova Viktoria
 * @version 1.0
 */
public class InjectorTest {
    
    private Injector injector;
    
    /**
     * Подготавливает инжектор перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        injector = new Injector();
    }
    
    /**
     * Тестирует конфигурацию по умолчанию.
     * Проверяет, что поля инициализируются правильными реализациями.
     */
    @Test
    void testDefaultConfiguration() {
        System.out.println(" Testing Default Configuration");
        
        SomeBean bean = new SomeBean();
        SomeBean injectedBean = injector.inject(bean);
        
        assertNotNull(injectedBean.getField1(), "Field1 should not be null after injection");
        assertNotNull(injectedBean.getField2(), "Field2 should not be null after injection");
        
        assertTrue(injectedBean.getField1() instanceof SomeImpl, 
                  "Field1 should be instance of SomeImpl");
        assertTrue(injectedBean.getField2() instanceof SODoer, 
                  "Field2 should be instance of SODoer");
        
        System.out.println(" Default configuration test passed");
    }
    
    /**
     * Тестирует процесс внедрения зависимостей в поля.
     * Проверяет состояние полей до и после инъекции.
     */
    @Test
    void testFieldInjection() {
        System.out.println(" Testing Field Injection");
        
        SomeBean bean = new SomeBean();
        
        assertNull(bean.getField1(), "Field1 should be null before injection");
        assertNull(bean.getField2(), "Field2 should be null before injection");
        
        SomeBean injectedBean = injector.inject(bean);
        
        assertNotNull(injectedBean.getField1(), "Field1 should not be null after injection");
        assertNotNull(injectedBean.getField2(), "Field2 should not be null after injection");
        
        System.out.println(" Field injection test passed");
    }
    
    /**
     * Тестирует обработку null объектов.
     * Проверяет, что инжектор выбрасывает исключение при null.
     */
    @Test
    void testNullObjectInjection() {
        System.out.println(" Testing Null Object Handling");
        
        assertThrows(IllegalArgumentException.class, () -> {
            injector.inject(null);
        }, "Should throw exception when injecting into null object");
        
        System.out.println(" Null object handling test passed");
    }
    
    /**
     * Тестирует работоспособность бизнес-логики после внедрения зависимостей.
     * Проверяет, что методы можно вызывать без исключений.
     */
    @Test
    void testBusinessLogicAfterInjection() {
        System.out.println(" Testing Business Logic After Injection");
        
        SomeBean bean = injector.inject(new SomeBean());
        
        assertDoesNotThrow(() -> {
            bean.foo();
        }, "Business logic should work without exceptions after injection");
        
        System.out.println(" Business logic test passed");
    }
    
    /**
     * Тестирует, что инжектор возвращает тот же объект, который был передан.
     * Проверяет идентичность объектов до и после инъекции.
     */
    @Test
    void testSameInstanceReturned() {
        System.out.println(" Testing Same Instance Returned");
        
        SomeBean originalBean = new SomeBean();
        SomeBean returnedBean = injector.inject(originalBean);
        
        assertSame(originalBean, returnedBean, 
                  "Inject should return the same object instance");
        
        System.out.println(" Same instance test passed");
    }
}