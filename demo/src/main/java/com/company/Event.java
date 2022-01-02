package com.company;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Basma
 * @version 1.00 2021/12/30
 * This class is used to events which happens to ride
 */
public class Event{
    private String userName;
    private String captainName;
    private String event1Time;
    private String event2Time;
    private String event3Time;
    private String event4Time;
    /////////////////////////////methods///////////////////////////////
    /**
     * This method putPrice used for assign an event
     * @param captainName The value to set the captainName data field to
     */
    public void putPrice(String captainName)
    {
        LocalTime myObj = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.event1Time= myObj.format(formatter);
        this.captainName = captainName;
    }
    /**
     * This method acceptPrice used for assign an event
     * @param userName The value to set the userName data field to
     */
    public void acceptPrice(String userName)
    {
        LocalTime myObj = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.event2Time= myObj.format(formatter);
        this.userName= userName;
    }
    /**
     * This method arrivedLocation used for assign an event
     * @param userName The value to set the userName data field to
     * @param captainName The value to set the captainName data field to
     */
    public void arrivedLocation()
    {
        LocalTime myObj = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.event3Time= myObj.format(formatter);
    }
    /**
     * This method arrivedDistination used for assign an event
     * @param userName The value to set the userName data field to
     * @param captainName The value to set the captainName data field to
     */
    public void arrivedDistination()
    {
        LocalTime myObj = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.event4Time= myObj.format(formatter);
    }
    /**
     * This method getEvent1 used for printing event1
     */
    public void getEvent1()
    {
        System.out.println("Event Name: Driver puts the price");
        System.out.println("Event Time: " + event1Time);
        System.out.println("Captain Name: "+captainName);
    }
    /**
     * This method getEvent2 used for printing event2
     */
    public void getEvent2()
    {
        System.out.println("Event Name: Passenger accepts the price");
        System.out.println("Event Time: " + event1Time);
        System.out.println("Passenger Name: "+ userName);
    }
    /**
     * This method getEvent3 used for printing event3
     */
    public void getEvent3()
    {
        System.out.println("Event Name: Captain arrived to user location");
        System.out.println("Event Time: " + event1Time);
        System.out.println("Passenger Name: "+ userName);
        System.out.println("Captain Name: "+captainName);
    }
    /**
     * This method getEvent4 used for printing event4
     */
    public void getEvent4()
    {
        System.out.println("Event Name: Captain arrived to user destination");
        System.out.println("Event Time: " + event1Time);
        System.out.println("Passenger Name: "+ userName);
        System.out.println("Captain Name: "+captainName);
    }
}