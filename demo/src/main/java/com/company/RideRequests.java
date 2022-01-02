package com.company;
import java.util.ArrayList;
/**
 * @author Naden
 * @version 1.00 2021/11/22
 * this class store the pending rides and notify all drivers about new request
 */
public class RideRequests
{
    private ArrayList<Passenger> pendingRide ;
    private Notification notification;
    private static RideRequests rideRequests= null;
    /////////////////////////////constructor///////////////////////////////////////////////////
    /**
     * The class constructor assigns a given values to notification
     * @param notification The value to set the notification data field to
     */
    RideRequests() {
        pendingRide = new ArrayList<>();
        this.notification = Notification.getInstance();
    }
    public static RideRequests getInstance() {
        if (rideRequests == null) {
            rideRequests = new RideRequests();
        }
        return rideRequests;
    }
    ///////////////////////////setters and getters/////////////////////////////////////
    /**
     * The setPendingRide method assigns a given value to PendingRide
     * @param passenger The value to set the userName data field to
     */
    public void addPendingRide(Passenger passenger){
        pendingRide.add(passenger);
        notification.notify(passenger.getPendingRide());
    }
    /**
     * The deletePendingRide method assigns a given value to PendingRide
     * @param passenger The value to set the userName data field to
     */
    public void deletePendingRide(Passenger passenger){
        pendingRide.remove(passenger) ;
    }
    /**
     * The getPendingRide returns the value of the PendingRide
     * @return the value of the ArrayList<Passenger>
     */
    public ArrayList<Passenger> getPendingRide() {
        return pendingRide;
    }

}
