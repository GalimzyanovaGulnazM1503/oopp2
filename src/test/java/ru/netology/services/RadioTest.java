package ru.netology.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class RadioTest {

    private Radio radio;

    @BeforeEach
    void setUp() {
        radio = new Radio();
    }

    // Тесты для начального состояния
    @Test
    void testInitialState() {
        assertEquals(1, radio.getCurrentStation());
        assertEquals(0, radio.getCurrentVolume());
    }

    // Тесты для громкости
    @Test
    void testIncreaseVolumeFromZero() {
        radio.incVolume();
        assertEquals(1, radio.getCurrentVolume());
    }

    @Test
    void testIncreaseVolumeFromMiddle() {
        // Устанавливаем среднюю громкость
        for (int i = 0; i < 50; i++) {
            radio.incVolume();
        }
        radio.incVolume();
        assertEquals(51, radio.getCurrentVolume());
    }

    @Test
    void testIncreaseVolumeAtMax() {
        // Доводим до максимума
        for (int i = 0; i < 100; i++) {
            radio.incVolume();
        }
        assertEquals(100, radio.getCurrentVolume());

        // Пытаемся увеличить сверх максимума
        radio.incVolume();
        assertEquals(100, radio.getCurrentVolume());
    }

    @Test
    void testDecreaseVolumeFromMiddle() {
        // Устанавливаем среднюю громкость
        for (int i = 0; i < 50; i++) {
            radio.incVolume();
        }
        radio.decVolume();
        assertEquals(49, radio.getCurrentVolume());
    }

    @Test
    void testDecreaseVolumeAtMin() {
        // Громкость на минимуме (0)
        radio.decVolume();
        assertEquals(0, radio.getCurrentVolume());
    }

    @Test
    void testDecreaseVolumeFromOne() {
        radio.incVolume(); // volume = 1
        radio.decVolume(); // volume = 0
        assertEquals(0, radio.getCurrentVolume());
    }

    @Test
    void testVolumeBoundaryBehavior() {
        // Проверка граничных значений громкости
        for (int i = 0; i < 150; i++) {
            radio.incVolume();
        }
        assertEquals(100, radio.getCurrentVolume());

        for (int i = 0; i < 150; i++) {
            radio.decVolume();
        }
        assertEquals(0, radio.getCurrentVolume());
    }

    // Тесты для радиостанций
    @Test
    void testNextStationFromInitial() {
        radio.next();
        assertEquals(2, radio.getCurrentStation());
    }

    @Test
    void testNextStationFromMiddle() {
        radio.setStation((byte) 5);
        radio.next();
        assertEquals(6, radio.getCurrentStation());
    }

    @Test
    void testNextStationAtMax() {
        radio.setStation((byte) 9);
        radio.next();
        assertEquals(9, radio.getCurrentStation()); // Не должен измениться
    }

    @Test
    void testPreviousStationFromMiddle() {
        radio.setStation((byte) 5);
        radio.dec();
        assertEquals(4, radio.getCurrentStation());
    }

    @Test
    void testPreviousStationFromInitial() {
        radio.dec();
        assertEquals(1, radio.getCurrentStation()); // Не должен измениться
    }

    @Test
    void testPreviousStationFromTwo() {
        radio.setStation((byte) 2);
        radio.dec();
        assertEquals(1, radio.getCurrentStation());
    }

    @Test
    void testPreviousStationAtMin() {
        radio.dec();
        radio.dec();
        assertEquals(1, radio.getCurrentStation()); // Не должен измениться
    }

    @Test
    void testSetValidStation() {
        radio.setStation((byte) 5);
        assertEquals(5, radio.getCurrentStation());
    }

    @Test
    void testSetStationMaxBoundary() {
        radio.setStation((byte) 9);
        assertEquals(9, radio.getCurrentStation());
    }

    @Test
    void testSetStationMinBoundary() {
        radio.setStation((byte) 1);
        assertEquals(1, radio.getCurrentStation());
    }

    @Test
    void testStationSequenceOperations() {
        // Последовательность операций со станциями
        radio.next(); // 1 -> 2
        radio.next(); // 2 -> 3
        radio.dec();  // 3 -> 2
        radio.next(); // 2 -> 3
        radio.next(); // 3 -> 4

        assertEquals(4, radio.getCurrentStation());
    }

    @Test
    void testVolumeAndStationIndependence() {
        // Проверка независимости громкости и станции
        radio.incVolume();
        radio.incVolume();
        radio.next();
        radio.next();

        assertEquals(3, radio.getCurrentStation());
        assertEquals(2, radio.getCurrentVolume());

        radio.decVolume();
        radio.dec();

        assertEquals(2, radio.getCurrentStation());
        assertEquals(1, radio.getCurrentVolume());
    }

    @Test
    void testMultipleSetStationOperations() {
        radio.setStation((byte) 3);
        radio.setStation((byte) 7);
        radio.setStation((byte) 5);

        assertEquals(5, radio.getCurrentStation());
    }

    @Test
    void testComplexScenario() {
        // Комплексный сценарий использования
        radio.setStation((byte) 5);
        radio.incVolume();
        radio.incVolume();
        radio.next();
        radio.decVolume();
        radio.dec();
        radio.setStation((byte) 8);
        radio.incVolume();

        assertEquals(8, radio.getCurrentStation());
        assertEquals(2, radio.getCurrentVolume());
    }

    @Test
    void testStationRangeLimits() {
        // Проверка всех допустимых станций
        for (int i = 1; i <= 9; i++) {
            radio.setStation((byte) i);
            assertEquals(i, radio.getCurrentStation());
        }
    }
}

