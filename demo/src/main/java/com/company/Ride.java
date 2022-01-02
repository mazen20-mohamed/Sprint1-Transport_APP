package com.company;
/**
 * @author Naden
 * @version 1.00 2021/11/22
 * This class create a ride by User request
 */
public class Ride {
    private String source;
    private String destination;
    private int price,numberOfPassenger;
    private boolean status = false,acceptedPrice = false;
    private String driverInfo;
    private Event event;
    /**
     * initialize the attributes by empty default values
     */
    public Ride(){
        source = "";
        destination ="";
        event  =new Event();
    }
    /**
     * The class constructor assigns a given values to source , destination
     * @param  source The value to set the UserName data field to
     * @param destination The value to set the mobileNumber data field to
     */
    public  Ride(String source , String destination,int numberOfPassenger){
        this.source = source;
        this.destination = destination;
        this.numberOfPassenger = numberOfPassenger;
        event  =new Event();
    }
    /**
     * The setPrice method assigns a given value to status
     * @param price The value to set the status data field to
     */
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * The getPrice returns the value of the Price
     * @return the value of the Price
     */
    public int getPrice() {
        return price;
    }
    /**
     * The getDestination returns the value of the Destination
     * @return the value of the Destination
     */
    public String getDestination() {
        return destination;
    }
    /**
     * The getSource returns the value of the Source
     * @return the value of the Source
     */
    public String getSource() {
        return source;
    }
    /**
     * The getStatus returns the value of the Status
     * @return the value of the Status
     */
    public boolean getStatus() {
        return status;
    }
    /**
     * The setStatus method assigns a given value to Status
     * @param status The value to set the status data field to
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    /**
     * The getDriverInfo returns the value of the DriverInfo
     * @return the value of the DriverInfo
     */
    public String getDriverInfo() {
        return driverInfo;
    }
    /**
     * The setDriverInfo method assigns a given value to DriverInfo
     * @param driverInfo The value to set the DriverInfo data field to
     */
    public void setDriverInfo(String driverInfo) {
        this.driverInfo = driverInfo;
    }
    public boolean getAcceptedPrice() {
        return acceptedPrice;
    }
    public void setAcceptedPrice(boolean acceptedPrice) {
        this.acceptedPrice = acceptedPrice;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public int getNumberOfPassenger() {
        return numberOfPassenger;
    }
}