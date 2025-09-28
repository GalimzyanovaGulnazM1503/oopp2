package ru.netology.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class RadioTest {

    private Radio radio;

    @BeforeEach
    void setUp() {
        radio = new Radio(10);
    }

    // Тесты для конструкторов
    @Test
    void constructor_WithCustomStationCount_ShouldSetCorrectStationCol() {
        Radio customRadio = new Radio(5);
        customRadio.setStation((byte) 4);
        assertEquals(4, customRadio.getCurrentStation());
    }

    @Test
    void constructor_Default_ShouldSet10Stations() {
        Radio defaultRadio = new Radio();
        defaultRadio.setStation((byte) 9);
        assertEquals(9, defaultRadio.getCurrentStation());
    }

    // Тесты для метода prev()
    @Test
    void prev_WhenCurrentStationIs1_ShouldSetToMaxStation() {
        radio.setStation((byte) 1);
        radio.prev();
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    void prev_WhenCurrentStationIsGreaterThan1_ShouldDecreaseByOne() {
        radio.setStation((byte) 5);
        radio.prev();
        assertEquals(4, radio.getCurrentStation());
    }

    @Test
    void prev_WhenCurrentStationIs0_ShouldSetToMaxStationMinusOne() {
        radio.setStation((byte) 0);
        radio.prev();
        assertEquals(9, radio.getCurrentStation());
    }

    @Test
    void prev_WhenCurrentStationIsMax_ShouldDecreaseByOne() {
        radio.setStation((byte) 9);
        radio.prev();
        assertEquals(8, radio.getCurrentStation());
    }

    // Тесты для метода next()
    @Test
    void next_WhenCurrentStationIsMaxMinusOne_ShouldSetToZero() {
        radio.setStation((byte) 8);
        radio.next();
        assertEquals(9, radio.getCurrentStation());
    }

    @Test
    void next_WhenCurrentStationIsLessThanMaxMinusOne_ShouldIncreaseByOne() {
        radio.setStation((byte) 5);
        radio.next();
        assertEquals(6, radio.getCurrentStation());
    }

    @Test
    void next_WhenCurrentStationIsZero_ShouldIncreaseByOne() {
        radio.setStation((byte) 0);
        radio.next();
        assertEquals(1, radio.getCurrentStation());
    }

    @Test
    void next_WhenCurrentStationIsMax_ShouldWrapToZero() {
        radio.setStation((byte) 9);
        radio.next();
        assertEquals(0, radio.getCurrentStation());
    }

    // Тесты для метода setStation()
    @Test
    void setStation_WithValidStation_ShouldSetCurrentStation() {
        radio.setStation((byte) 7);
        assertEquals(7, radio.getCurrentStation());
    }

    @Test
    void setStation_WithInvalidStationAboveMax_ShouldNotChangeStation() {
        radio.setStation((byte) 5);
        radio.setStation((byte) 15); // Невалидная станция
        assertEquals(5, radio.getCurrentStation());
    }

    @Test
    void setStation_WithNegativeStation_ShouldNotChangeStation() {
        radio.setStation((byte) 3);
        radio.setStation((byte) -1); // Невалидная станция
        assertEquals(3, radio.getCurrentStation());
    }

    @Test
    void setStation_WithMaxValidStation_ShouldSetStation() {
        radio.setStation((byte) 9);
        assertEquals(9, radio.getCurrentStation());
    }

    @Test
    void setStation_WithMinValidStation_ShouldSetStation() {
        radio.setStation((byte) 0);
        assertEquals(0, radio.getCurrentStation());
    }

    // Тесты для метода incVolume()
    @Test
    void incVolume_WhenVolumeLessThan100_ShouldIncreaseVolume() {
        radio.incVolume();
        assertEquals(1, radio.getCurrentVolume());
    }

    @Test
    void incVolume_WhenVolumeIs100_ShouldNotIncreaseVolume() {
        // Устанавливаем максимальную громкость
        for (int i = 0; i < 100; i++) {
            radio.incVolume();
        }
        assertEquals(100, radio.getCurrentVolume());

        radio.incVolume(); // Попытка увеличить сверх максимума
        assertEquals(100, radio.getCurrentVolume());
    }

    @Test
    void incVolume_MultipleIncrements_ShouldIncreaseCorrectly() {
        radio.incVolume();
        radio.incVolume();
        radio.incVolume();
        assertEquals(3, radio.getCurrentVolume());
    }

    // Тесты для метода decVolume()
    @Test
    void decVolume_WhenVolumeGreaterThanZero_ShouldDecreaseVolume() {
        radio.incVolume(); // Увеличиваем до 1
        radio.incVolume(); // Увеличиваем до 2
        radio.decVolume();
        assertEquals(1, radio.getCurrentVolume());
    }

    @Test
    void decVolume_WhenVolumeIsZero_ShouldNotDecreaseVolume() {
        radio.decVolume();
        assertEquals(0, radio.getCurrentVolume());
    }

    @Test
    void decVolume_WhenVolumeIsOne_ShouldSetToZero() {
        radio.incVolume(); // Увеличиваем до 1
        radio.decVolume();
        assertEquals(0, radio.getCurrentVolume());
    }

    @Test
    void decVolume_MultipleDecrements_ShouldNotGoBelowZero() {
        radio.incVolume(); // 1
        radio.decVolume(); // 0
        radio.decVolume(); // Должно остаться 0
        radio.decVolume(); // Должно остаться 0
        assertEquals(0, radio.getCurrentVolume());
    }

    // Тесты для граничных значений
    @Test
    void boundary_VolumeFromZeroToHundred() {
        for (int i = 0; i < 100; i++) {
            radio.incVolume();
        }
        assertEquals(100, radio.getCurrentVolume());

        radio.incVolume();
        assertEquals(100, radio.getCurrentVolume());
    }

    @Test
    void boundary_StationCycling() {
        radio.setStation((byte) 0);
        radio.prev();
        assertEquals(9, radio.getCurrentStation());

        radio.next();
        assertEquals(0, radio.getCurrentStation());
    }

    // Тесты с кастомным количеством станций
    @Test
    void customStationCount_With5Stations() {
        Radio customRadio = new Radio(5);

        // Проверка границ
        customRadio.setStation((byte) 0);
        assertEquals(0, customRadio.getCurrentStation());

        customRadio.setStation((byte) 4);
        assertEquals(4, customRadio.getCurrentStation());

        // Проверка переключения
        customRadio.setStation((byte) 4);
        customRadio.next();
        assertEquals(0, customRadio.getCurrentStation());

        customRadio.setStation((byte) 0);
        customRadio.prev();
        assertEquals(4, customRadio.getCurrentStation());
    }

    @Test
    void nextNegative() {
        radio.setStation((byte) 9);
        radio.next();
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    void nextNegativeCd() {
        radio = new Radio();
        radio.setStation((byte) 9);
        radio.next();
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    void prevNegative() {
        radio.setStation((byte) 0);
        radio.prev();
        assertEquals(9, radio.getCurrentStation());
    }

    @Test
    void prevNegativeC() {
        radio.setStation((byte) 0);
        radio.prev();
        assertEquals(9, radio.getCurrentStation());
    }
}