package com.company;
import java.util.ArrayList;
/**
 *@author Naden
 *@version 1.00 2021/11/22
 * this class extend from user abstract class
 */
public class Passenger extends User{
    public ArrayList<Ride> ridelist ;
    public Ride pendingRide ;
    /**
     * The class constructor assigns a given values to UserName, mobileNumber, and Status
     * @param UserName The value to set the UserName data field to
     * @param mobileNumber The value to set the mobileNumber data field to
     * @param Password The value to set the Password data field to
     */
    public Passenger(String UserName , String Password, String mobileNumber ){
        super(UserName, Password , mobileNumber);
        this.setStatus(Status.activate);
        ridelist = new ArrayList<>();
    }
    /**
     * The getPendingRide returns the value of the UserName
     * @return the value of the PendingRide
     */
    public Ride getPendingRide() {
        return pendingRide;
    }
    /**
     * The getRidelist returns the value of the Ridelist
     * @return the value of the PendingRide
     */
    public ArrayList<Ride> getRidelist() {
        return ridelist;
    }
    /**
     * The addRide add ride into ridelist
     * @param ride The value to add the ride data field to
     */
    public void addRide(Ride ride){
        ridelist.add(ride);
    }
    /**
     * The requestRide method assigns a given value to pendingRide
     * @param ride  The value to set the pendingRide data field to
     */
    public void requestRide(Ride ride){
        pendingRide = ride ;
    }
}
