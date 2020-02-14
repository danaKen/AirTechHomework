package com;

public class Order {
    private String orderName;
    private String destination;
    private Flight flight = null; //flight is assigned later

    public Order(String order, String dest) {
        orderName = order;
        destination = dest;
    }

    public void setFlight(Flight f) {
        flight = f;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getDestination() {
        return destination;
    }
}
