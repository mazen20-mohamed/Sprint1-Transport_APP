package com.company;
import java.util.ArrayList;
/**
 * @author Basma
 * @version 1.00 2021/11/21
 * This class is used to store database of user
 */
public class Database
{
    private ArrayList<User> userList;
    private ArrayList<Ride>rides;
    private static Database database = null;
    /////////////////////////////constructor///////////////////////////////////////////////////
    /**
     * The class constructor initialize userList array and rides
     */
    public Database() {
        userList = new ArrayList<>();
        rides = new ArrayList<>();
    }
    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }
    ///////////////////////////setters and getters/////////////////////////////////////
    /**
     * The getUserList returns the value of the arraylist of users
     * @return the value of the arraylist
     */
    public ArrayList<User> getUserList()
    {
        return userList;
    }
    /**
        * The class addUserList add user to arraylist
        * @param user The value to set the user data field to
        * @return True if it user added correctly, otherwise false
     */
    public boolean addUserList(User user)
    {
        boolean data = searchUserList(user);
        if(data)
        {
            return false;
        }
        for (User value : userList) {
            if (value.getUserName().equals(user.getUserName())) {
                return false;
            }
        }
        userList.add(user);
        return true;
    }
    /////////////////////////////methods///////////////////////////////
    /**
     * The method searchUserList search user in the arraylist
     * @param user The value to search the user data field in the arraylist
     */
    public boolean searchUserList(User user)
    {
        for (User value : userList) {
            if (value == user) {
                return true;
            }
        }
        return false;
    }
    public void addRide(Ride ride)
    {
        rides.add(ride);
    }
    public ArrayList<Ride> getRides()
    {
        return rides;
    }
    public ArrayList<Ride> searchForRides(String location)
    {
        ArrayList<Ride> arrayList= new ArrayList<>();
        for (Ride ride:rides)
        {
            if(ride.getDestination().equals(location)||ride.getSource().equals(location))
            {
                arrayList.add(ride);
            }
        }
        return arrayList;
    }
}
