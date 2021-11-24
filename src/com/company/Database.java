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
    /////////////////////////////constructor///////////////////////////////////////////////////
    /**
     * The class constructor initialize userList array
     */
    public Database() {
        userList = new ArrayList<>();
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
        for(int i=0;i<userList.size();i++)
        {
            if(userList.get(i).getUserName().equals(user.getUserName()))
            {
                return false;
            }
        }
        userList.add(user);
        return true;
    }
    /////////////////////////////methods///////////////////////////////
    /**
     * The class searchUserList search user in the arraylist
     * @param user The value to search the user data field in the arraylist
     */
    public boolean searchUserList(User user)
    {
        for(int i=0;i<userList.size();i++)
        {
            if(userList.get(i)== user)
            {
                return true;
            }
        }
        return false;
    }
}