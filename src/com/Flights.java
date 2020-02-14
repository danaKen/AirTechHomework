package com;

import java.util.ArrayList;
import java.util.List;


public class Flights {
    private List<Flight> allFlights; //List of all flights

    // Constructor reads a file specified by filePath, and saves all flights in filePath to allFlights
    public Flights(String filePath) {
        allFlights = new ArrayList<>();

        FlightOrderFileReader readfile = new FlightOrderFileReader();
        allFlights = readfile.getAllFlights(filePath);
    }


    // adds/reassigns flight to the order
    public Flight addOrder(Order order) {
        // assign it to the first possible flight
        for(Flight flight : allFlights) {
            // get the available capacity of the flight
            int capacity = flight.getAvailableCapacity();
            // check if there is an available capacity left AND if it is flying to the same destination:
            if(capacity != 0 && order.getDestination().equals(flight.getDestination())) {
                flight.setAvailableCapacity(capacity - 1);
                return flight;
            }
        }
        // There is no flight available, return null
        return null;
    }

    // Outputs all flights' information
    public void outputFlights() {
        for(Flight flight : allFlights) {
            System.out.println( "Flight: " + flight.getFlightNumber() + ", departure: " + flight.getSource() +
                    ", arrival: " + flight.getDestination() + ", day: " + flight.getDay() );
        }
    }
}
