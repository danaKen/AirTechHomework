package com;

public class Flight {
    private int flightNumber;
    private String source;
    private String destination;
    private int availableCapacity;
    private int day;

    public Flight(int num, String src, String dest, int cap, int dayNum) {
        flightNumber = num;
        source = src;
        destination = dest;
        availableCapacity = cap;
        day = dayNum;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int cap) {
        availableCapacity = cap;
    }

    public int getDay() {
        return day;
    }
}
