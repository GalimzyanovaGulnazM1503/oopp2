package ru.netology.services;

public class Radio {
    private int currentVolume = 0;
    private int currentStation = 1;

    private final int stationCol;

    public Radio(int stationCol) {
        this.stationCol = stationCol;
    }

    public Radio() {
        this.stationCol = 10;
    }

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

    public void prev() {
        if (currentStation >= 1) {
            currentStation = currentStation - 1;
        } else {
            currentStation = stationCol - 1;
        }
    }

    public void next() {
        if (currentStation < stationCol - 1) {
            currentStation = currentStation + 1;
        }  else {
            currentStation = 0;
        }
    }

    public void setStation(byte station) {
        if (station < stationCol && station >= 0) {
            currentStation = station;
        }
    }
}
