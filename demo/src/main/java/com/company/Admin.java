package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Mazen
 * @version 1.00 2021/11/22
 * This class is used for creating Admin with his operation
 */
public class Admin {
    private final String userName="APP_123";
    private final String pin = "1234";
    private Database database;
    /////////////////////////////constructor///////////////////////////////////////////////////
    /**
     * The class constructor assigns a given values to database
     */
    public Admin(Database database) {
        this.database = database;
    }
    /////////////////////////////methods//////////////////////////////////////
    /**
     * The login method is used to check if the admin information is correct or not
     * @param userName The value to check the userName data field to
     * @param pin The value to check the pin data field to
     * @return True if it is correct, otherwise false
     */
    public boolean Login(String userName, String pin)
    {
        return this.userName.equals(userName) && this.pin.equals(pin);
    }
    /**
     * The verfiy method is used to change status of driver from pending to activate
     */
    public void verfiy()
    {
        ArrayList<User> list = database.getUserList();
        int cnt=1;
        for (User user : list) {
            if (user.getStatus().equals(Status.pending) && user instanceof Driver) {
                System.out.println("Driver " + (cnt) + ":");
                Driver driver = (Driver) user;
                driver.getDriverTasks().showDriver();
                DrivingLiscence drivingLiscence = driver.getDrivingLiscence();
                System.out.println("License Id: " + drivingLiscence.getLicenseId());
                System.out.println("License Type: " + drivingLiscence.getLicenseType());
                System.out.println("Driver account is activated");
                driver.setStatus(Status.activate);
                cnt++;
            }
        }
    }
    /**
     * The suspend method is used to change status of user to suspend
     * @param userName The value to check the userName data field to
     * @return True if it is done, otherwise false
     */
    public boolean suspend(String userName)
    {
        ArrayList<User> list = database.getUserList();
        for (User user : list) {
            if (user.getUserName().equals(userName)) {
                user.setStatus(Status.suspend);
                return true;
            }
        }
        return false;
    }
    /**
     * This method addDiscountArea used for setting the value to data field to
     * @param area The value to set the area data field to
     */
    public void addDiscountArea(String area)
    {
        Discount.getInstance().getDiscountAreas().add(area);
    }
    public void rideEvent(String location)
    {
        ArrayList<Ride> rides = Database.getInstance().searchForRides(location);
        for (Ride ride:rides)
        {
            ride.getEvent().getEvent1();
            ride.getEvent().getEvent2();
            ride.getEvent().getEvent3();
            ride.getEvent().getEvent4();
        }
    }   
}