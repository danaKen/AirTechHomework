package com;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// A class that handles the file reading
public class FlightOrderFileReader {

    // Reads orders from the fileName and returns a list of these orders.
    // fileName must be a json file.
    public List<Order> getAllOrders(String fileName) {
        // check if the file is a json file
        if(!fileName.endsWith(".json")) {
            outputError("Input file for orders must be a json file: " + fileName);
        }

        // save all orders in ret
        List<Order> ret = new ArrayList<>();

        try {
            // get the String representation of the file.
            String data = new String(Files.readAllBytes(Paths.get(fileName)));
            // Save the contents of the String in the map. Order is preserved.
            Map<String, Map<String,String>> map = new Gson().fromJson(data, new TypeToken<LinkedHashMap<String, Map<String,String>>>(){}.getType());

            // Iterate through the map to create Order objects and add them to the list (ret).
            Iterator it = map.entrySet().iterator();
            while(it.hasNext()) {
                // Get destination
                Map.Entry pair = (Map.Entry)it.next();
                Map<String, String> value = (Map)pair.getValue();
                String destination = value.get("destination");

                // create Order
                Order order = new Order(pair.getKey().toString(), destination);
                // add order to the list
                ret.add(order);
            }
        } catch (IOException e) {
            outputError(e.toString());
        }

        return ret;
    }



    // Reads fileName and returns a list of flights specified in the fileName.
    // fileName must be a txt.
    // It is assumed that the fileName will already have the flights ordered.
    public List<Flight> getAllFlights(String fileName) {
        // check if the file is a txt file
        if(!fileName.endsWith(".txt")) {
            outputError("Input file for flights must be a txt file: " + fileName);
        }

        // save all flights in ret
        List<Flight> ret = new ArrayList<>();

        try {
            // Read fileName
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            int day = -1; //store day number here
            String st;
            while ((st = br.readLine()) != null) {
                if (st.toLowerCase().startsWith("day")) { // A line contains a day
                    day = Integer.parseInt(st.replaceAll("\\D+", ""));

                } else if (st.toLowerCase().startsWith("flight")) { // A line contains the flight information
                    // Prepare the parameters
                    int flightNumber = Integer.parseInt(st.replaceAll("\\D+", ""));
                    String source = getAirportName(st, true);
                    String dest = getAirportName(st, false);
                    int capacity = 20; //for this exercise capacity is 20 for all flights

                    //check if the day has been initialized
                    if (day == -1) outputError("Missing day");
                    //create a flight
                    Flight flight = new Flight(flightNumber, source, dest, capacity, day);
                    //add the above flight to the list
                    ret.add(flight);
                }
            }
        } catch (IOException e) {
            outputError(e.toString());
        }

        return ret;
    }

    // Helper function for getAllFlights() method.
    // Gets the content inside brackets:
    //   If getSource is true, then it will look for the first brackets, ie, source;
    //   If getSource is false, then it will look for the last brackets, ie, destination.
    private String getAirportName(String st, boolean getSource) {
        // Get indexes of first brackets
        int i = st.indexOf("(");
        int j = st.indexOf(")");

        if (!getSource) { // we need destination
            // Get indexes of last brackets
            int i2 = st.lastIndexOf("(");
            int j2 = st.lastIndexOf(")");
            // Make sure they are different from first brackets
            if(i == i2 || j == j2) outputError("Input file for Flight schedule is missing source or destination");
            i = i2;
            j = j2;
        }

        // Make sure the indexes were found and are in the correct order
        if(i == -1 || j == -1 || i > j) outputError("Input file for Flight schedule has wrong format");

        // return the string inside brackets
        return st.substring(i+1, j);
    }


    // Helper function.
    // Outputs error message and exits.
    private static void outputError(String message) {
        System.err.println("ERROR: " + message);
        System.exit(1);
    }

}
