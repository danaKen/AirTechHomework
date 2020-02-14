package com;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private List<Order> allOrders;

    // Constructor reads all orders from the file specified by filePath
    // and saves them in allOrders
    public Orders(String filePath) {
        allOrders = new ArrayList<>();

        FlightOrderFileReader readfile = new FlightOrderFileReader();
        allOrders = readfile.getAllOrders(filePath);
    }

    // assign flights to orders
    public void scheduleOrders(Flights flights) {
        if(flights != null) {
            for(Order order : allOrders) {
                order.setFlight(flights.addOrder(order));
            }
        }
    }


    // Output all orders' information
    public void outputOrders() {
        for(Order order : allOrders) {
            // get the flight of the order
            Flight orderFlight = order.getFlight();

            if(orderFlight == null) {// flight was not scheduled for this order
                System.out.println("order: " + order.getOrderName() + ", flightNumber: not scheduled");
            } else { //order has a flight scheduled
                System.out.println("order: " + order.getOrderName() + ", flightNumber: " + orderFlight.getFlightNumber() +
                        ", departure: " + orderFlight.getSource() + ", arrival: " + order.getDestination() +
                        ", day: " + orderFlight.getDay());
            }
        }
    }
}
