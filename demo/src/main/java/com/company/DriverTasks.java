package com.company;

import java.util.ArrayList;

public class DriverTasks {
    Driver driver ;
    public DriverTasks(Driver driver)
    {
        this.driver=driver;
    }
    /**
     * The showDriver function is used to show information of driver
     */
    public void showDriver()
    {
        System.out.println("Driver Profile Info:");
        System.out.println("Name: "+driver.getUserName());
        System.out.println("Mobile Number: "+driver.getMobileNumber());
        System.out.println("Average Rate: "+ driver.getAverageRate());
        System.out.println("National Id: " + driver.getNationalId());
    }

    /**
     * The showListRate is used to show list rate of passengers
     */
    public void showListRate()
    {
        System.out.println("List of Rates of users that you have been with:");
        for(Passenger passenger : driver.getListRate().keySet())
        {
            System.out.println(passenger.getUserName()+": "+driver.getListRate().get(passenger));
        }
    }
    /**
     * The showRideRequests method is used for show all ride requested that are pending
     * @return True if there is pending requests, otherwise false
     */
    public boolean showRideRequests()
    {
        ArrayList<Passenger> list = driver.getRideRequests().getPendingRide();
        if(list.size()==0)
        {
            System.out.println("There is no requests...");
            return false;
        }
        System.out.println("Requested Rides:");
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).pendingRide.getNumberOfPassenger()<= driver.getLimitNumberOfPassenger())
            {
                System.out.println(list.get(i).getUserName() + " Request "+(i+1)+": ");
                Ride ride = list.get(i).getPendingRide();
                System.out.println("From "+ride.getSource()+ " to "+ ride.getDestination());
            }
        }
        return true;
    }
}
