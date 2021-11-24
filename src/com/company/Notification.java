package com.company;

import java.util.ArrayList;

/**
 * @author Mazen
 * @version 1.00 2021/11/22
 * This class is used for notify drivers
 */
public class Notification {
    private Database database;
    /////////////////////////////constructor///////////////////////////////////////////////////
    /**
     * The class constructor assigns a given values to database
     * @param database The value to set the database data field to
     */
    Notification(Database database)
    {
        this.database = database;
    }
    /////////////////////////////methods///////////////////////////////
    /**
     * The notify method is used for notify all drivers whose favourite area is the same as ride source
     * @param ride The value is to check ride source with favourite area
     */
    public void notify(Ride ride)
    {
        ArrayList<User>  list = database.getUserList();
        String source = ride.getSource();
        for(int i=0;i<list.size();i++)
        {
            if(Driver.class.isInstance(list.get(i)))
            {
                Driver driver = (Driver) list.get(i);
                ArrayList<String> favourite=  driver.getFavouriteAreas();
                for(int j=0;j<favourite.size();j++)
                {
                    if(favourite.get(j).equalsIgnoreCase(source))
                    {
                        driver.addNotification("There is a request from "+ ride.getSource() + " to " + ride.getDestination());
                        break;
                    }
                }
            }
        }
    }
}