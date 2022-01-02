package com.company;
import java.util.ArrayList;
/**
 * @author Mazen
 * @version 1.00 2021/11/22
 * This class is used for notify drivers
 */
public class Notification {
    private Database database;
    private static Notification notification = null;
    /////////////////////////////constructor///////////////////////////////////////////////////
    /**
     * The class constructor assigns a given values to database
     * @param database The value to set the database data field to
     */
    Notification()
    {
        this.database = Database.getInstance();
    }
    public static Notification getInstance() {
        if (notification== null) {
            notification = new Notification();
        }
        return notification;
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
                if(driver.getBusy()==true)
                {
                        continue;
                }
                int flag=0;
                for(int j=0;j<favourite.size();j++)
                {
                    if(favourite.get(j).equalsIgnoreCase(source))
                    {
                        driver.addNotification("(Favourite Area)There is a request from "+ ride.getSource() + " to " + ride.getDestination());
                        flag=1;
                        break;
                    }
                }
                if(flag==0)
                {
                    driver.addNotification("There is a request from "+ ride.getSource() + " to " + ride.getDestination());
                }
            }
        }
    }
}