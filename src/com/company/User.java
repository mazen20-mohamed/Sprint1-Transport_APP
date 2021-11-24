package com.company;
/**
 * @author Mazen
 * @version 1.00 2021/11/21
 * @ This class is used for define just a user with normal operation
 */
public class  User
{
    private String userName;
    private String mobileNumber;
    private Status status;
    private String password;
    /////////////////////////////constructor///////////////////////////////////////////////////
    User() {
    }
    /**
     * The class constructor assigns a given values to UserName, mobileNumber, and password
     * @param userName The value to set the UserName data field to
     * @param mobileNumber The value to set the mobileNumber data field to
     * @param password The value to set the password data field to
     */
    User(String userName ,String mobileNumber,String password)
    {
        this.mobileNumber = mobileNumber;
        this.userName = userName;
        this.password = password;
    }
    ///////////////////////////setters and getters/////////////////////////////////////
    /**
     * The setstatus method assigns a given value to status
     * @param status The value to set the status data field to
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    /**
     * The getPassword returns the value of the password
     * @return the value of the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * The getUserName returns the value of the UserName
     * @return the value of the UserName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * The getStatus returns the value of the Status
     * @return the value of the Status
     */
    public Status getStatus() {
        return status;
    }
    /**
     * The getMobileNumber returns the value of the MobileNumber
     * @return the value of the MobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }
    /////////////////////////////methods///////////////////////////////
}
