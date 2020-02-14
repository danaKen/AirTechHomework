package com;


public class Main {
    public static void main(String[] args) {
        //Store input file paths here
        String inputFlightsFile = "";
        String inputOrdersFile = "";

        //read input parameters
        if(args.length < 1) {
            System.err.println("Invalid list of parameters. Type Main -h for help");
            System.exit(1);
        } else if(args[0].equals("-h")) {
            System.out.println("Main {pathScheduleFile.txt} [{pathOrdersFile.json}]");
            System.exit(0);
        } else if(args.length == 1) {
            inputFlightsFile = args[0];
        } else {
            inputFlightsFile = args[0];
            inputOrdersFile = args[1];
        }

        // Output Flights schedule
        Flights flights = new Flights(inputFlightsFile);
        flights.outputFlights();

        // Output scheduled orders (if the file was provided)
        if(!inputOrdersFile.isEmpty()) {
            Orders orders = new Orders(inputOrdersFile);
            orders.scheduleOrders(flights);
            orders.outputOrders();
        }
    }
}
