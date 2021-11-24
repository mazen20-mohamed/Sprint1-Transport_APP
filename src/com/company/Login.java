package com.company;
import java.util.ArrayList;
/**
 * @author Basma
 * @version 1.00 2021/11/21
 * This class is used to login users
 */
public class Login
{
    private Database database;
    /////////////////////////////constructor///////////////////////////////////////////////////
    /**
     * The class constructor assigns a given values to database
     * @param database The value to set the database data field to
     */
    Login(Database database)
    {
        this.database=database;
    }
    /////////////////////////////methods///////////////////////////////
    /**
     * The login method is used to check if the information user is found in the databse
     * @param password The value to set the password data field to
     * @param userName The value to set the userName data field to
     * @return The value of user founded , otherwise null
     */
    public User Login(String password, String userName)
    {
        ArrayList<User> list = database.getUserList();
        for(int i=0;i<list.size();i++)
        {
            User user = list.get(i);
            if(user.getUserName().equals(userName) && user.getPassword().equals(password) && user.getStatus().equals(Status.activate))
            {
                return user;
            }
        }
        return null;
    }
}
