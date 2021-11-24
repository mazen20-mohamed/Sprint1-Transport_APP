package com.company;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Mazen
 * @version 1.00 2021/11/22
 * This class is used for creating Admin with his operation
 */
public class Admin {
    private static String userName="APP_123";
    private static String pin = "1234";
    private Database database;
    /////////////////////////////constructor///////////////////////////////////////////////////
    /**
     * The class constructor assigns a given values to database
     * @param database The value to set the database data field to
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
        if(this.userName.equals(userName) && this.pin.equals(pin))
        {
            return true;
        }
        return false;
    }
    /**
     * The verfiy method is used to change status of driver from pending to activate
     */
    public void verfiy()
    {
        ArrayList<User> list = database.getUserList();
        int cnt=1;
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getStatus().equals(Status.pending) &&Driver.class.isInstance(list.get(i)))
            {
                Scanner input = new Scanner(System.in);
                System.out.println("Driver "+(cnt)+":");
                Driver driver= (Driver) list.get(i);
                driver.showDriver();
                DrivingLiscence drivingLiscence = driver.getDrivingLiscence();
                System.out.println("License Id: "+drivingLiscence.getLicenseId());
                System.out.println("License Type: "+drivingLiscence.getLicenseType());
                System.out.println("Do you want to change Status to activate?(yes/no)");
                String answer = input.next();
                if(answer.equalsIgnoreCase("yes"))
                {
                    driver.setStatus(Status.activate);
                }
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
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getUserName().equals(userName))
            {
                list.get(i).setStatus(Status.suspend);
                return true;
            }
        }
        return false;
    }
}