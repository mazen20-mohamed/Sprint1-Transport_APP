package com.company;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * @author Basma
 * @version 1.00 2021/11/21
 * This is a class for define a driver with his operation
 */
public class Driver extends User
{
    private String nationalId;
    private DrivingLiscence drivingLiscence;
    private ArrayList<String> favouriteAreas;
    private Double averageRate;
    private HashMap<Passenger, Integer> listRate;
    private RideRequests rideRequests;
    private HashMap<Passenger, Ride> listFavouriteAreaRide;
    private ArrayList<String> notification;
    /////////////////////////////constructor///////////////////////////////////////////////////
    /**
     * The class constructor assigns a given values to UserName, mobileNumber, nationalId, driving License,and password
     * @param userName The value to set the UserName data field to
     * @param mobileNumber The value to set the mobileNumber data field to
     * @param password The value to set the password data field to
     * @param nationalId The value to set the nationalId data field to
     * @param drivingLiscence The value to set the drivingLicence data field to
     * @param rideRequests The value to set the rideRequests data field to
     */
    public Driver(String userName, String mobileNumber, String password, String nationalId, DrivingLiscence drivingLiscence , RideRequests rideRequests) {
        super(userName, mobileNumber,password);
        this.nationalId = nationalId;
        this.drivingLiscence = drivingLiscence;
        this.averageRate = 0.0;
        this.favouriteAreas = new ArrayList<>();
        this.notification = new ArrayList<>();
        this.listFavouriteAreaRide = new HashMap<>();
        this.listRate = new HashMap<>();
        this.rideRequests= rideRequests;
        this.setStatus(Status.pending);
    }
    ///////////////////////////setters and getters/////////////////////////////////////
    /**
     * The addFavouriteAreaRide method adds a given value to listFavouriteAreaRide
     * @param passenger The value to add the passenger data field to
     * @param ride The value to add the ride data field to
     */
    public void addFavouriteAreaRide(Passenger passenger, Ride ride)
    {
        String source = ride.getSource();
        for(int i=0;i<favouriteAreas.size();i++)
        {
            if(favouriteAreas.get(i).equalsIgnoreCase(source))
            {
                this.listFavouriteAreaRide.put(passenger,ride);
                return;
            }
        }
    }
    /**
     * The addNotification method adds a given value to Notification
     * @param notification The value to add the notification data field to
     */
    public void addNotification(String notification) {
        this.notification.add(notification);
    }
    /**
     * The addFavouriteAreas method adds a given value to favouriteAreas
     * @param favouriteAreas The value to add the favouriteAreas data field to
     */
    public void addFavouriteAreas(String favouriteAreas) {
        this.favouriteAreas.add(favouriteAreas);
    }
    /**
     * The addListRate method adds a given value to ListRate
     * @param passenger The value to add the passenger data field to
     * @param rate The value to set the rate data field to
     */
    public void addListRate(Passenger passenger, int rate) {
        listRate.put(passenger,rate);
        calculateAverageRate();
    }
    /**
     * The getNationalId returns the value of the NationalId
     * @return the value of the NationalId
     */
    public String getNationalId() {
        return nationalId;
    }
    /**
     * The getDrivingLiscence returns the value of the DrivingLiscence
     * @return the value of the DrivingLiscence
     */
    public DrivingLiscence getDrivingLiscence() {
        return drivingLiscence;
    }
    /**
     * The getFavouriteAreas returns the value of the FavouriteAreas
     * @return the value of the FavouriteAreas
     */
    public ArrayList<String> getFavouriteAreas() {
        return favouriteAreas;
    }
    /**
     * The getAverageRate returns the value of the AverageRate
     * @return the value of the AverageRate
     */
    public Double getAverageRate() {
        return averageRate;
    }
    /**
     * The getListRate returns the value of the ListRate
     * @return the value of the ListRate
     */
    public HashMap<Passenger, Integer> getListRate() {
        return listRate;
    }
    /**
     * The getRideRequests returns the value of the RideRequests
     * @return the value of the RideRequests
     */
    public RideRequests getRideRequests() {
        return rideRequests;
    }
    /**
     * The getListFARide returns the value of the listFARide
     * @return the value of the listFARide
     */
    public HashMap<Passenger, Ride> getListFARide() {
        return listFavouriteAreaRide;
    }
    /**
     * The getNotification returns the value of the notification
     * @return the value of the notification
     */
    public ArrayList<String> getNotification() {
        return notification;
    }
    /////////////////////////////methods///////////////////////////////
    /**
     * The deleteFavouriteArea method is used for deleting favouriteArea
     * @param favouriteArea The value to delete the favouriteArea data field to
     * @return True if it found and deleted, otherwise false
     */
    public boolean deleteFavouriteArea(String favouriteArea)
    {
        for(int i=0;i<favouriteAreas.size();i++)
        {
            if(favouriteAreas.get(i).equals(favouriteArea))
            {
                favouriteAreas.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     * The showDriver function is used to show information of driver
     */
    public void showDriver()
    {
        System.out.println("Driver Profile Info:");
        System.out.println("Name: "+this.getUserName());
        System.out.println("Mobile Number: "+this.getMobileNumber());
        System.out.println("Average Rate: "+ this.getAverageRate());
        System.out.println("National Id: " + this.getNationalId());
    }
    /**
     * The calculateAverageRate method calulate Average Rate and set to averageArea data field
     */
    public void calculateAverageRate()
    {
        int size= listRate.size();
        int sum=0;
        for(int rate : listRate.values())
        {
            sum+=rate;
        }
        this.averageRate = (double)sum / size;
    }
    /**
     * The showListRate is used to show list rate of passengers
     */
    public void showListRate()
    {
        System.out.println("List of Rates of users that you have been with:");
        for(Passenger passenger : listRate.keySet())
        {
            System.out.println(passenger.getUserName()+": "+listRate.get(passenger));
        }
    }
    /**
     * The showRideRequests method is used for show all ride requested that are pending
     * @return True if there is pending requests, otherwise false
     */
    public boolean showRideRequests()
    {
        ArrayList<Passenger> list = rideRequests.getPendingRide();
        if(list.size()==0)
        {
            System.out.println("There is no requests...");
            return false;
        }
        System.out.println("Requested Rides:");
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i).getUserName() + " Request "+(i+1)+": ");
            Ride ride = list.get(i).getPendingRide();
            System.out.println("From "+ride.getSource()+ " to "+ ride.getDestination());
        }
        return true;
    }
}
