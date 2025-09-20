package ru.netology.services;

public class Radio {
    private int currentVolume = 0;
    private int currentStation = 1;

    public int getCurrentVolume() {
        return currentVolume;
    }

    public int getCurrentStation() {
        return currentStation;
    }

    public void incVolume() {
        if (currentVolume < 100) {
            currentVolume = currentVolume + 1;
        }
    }

    public void decVolume() {
        if (currentVolume > 0) {
            currentVolume = currentVolume - 1;
        }
    }

    public void dec() {
        if (currentStation > 1) {
            currentStation = currentStation - 1;
        }
    }

    public void next() {
        if (currentStation < 9) {
            currentStation = currentStation + 1;
        }
    }

    public void setStation(byte station) {
        if (station <= 9 && station > 0) {
            currentStation = station;
        }
    }
}
