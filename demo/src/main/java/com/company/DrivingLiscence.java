package com.company;

/**
 * @author Naden
 * @version 1.00 2021/11/22
 * This class take basic information of driver license and store it
 */
public class DrivingLiscence {
    private int licenseId ;
    private String licenseType ;
    /**
     * initialize the attributes by empty default values
     */
    public DrivingLiscence(){
        licenseId = 0 ;
        licenseType ="" ;
    }
    /**
     * The class constructor assigns a given values to Licenseid, licenseType
     * @param id The value to set the Licenseid data field to
     * @param type The value to set the licenseType data field to
     */
    public DrivingLiscence(int id , String type){
        licenseId = id ;
        licenseType = type ;
    }

    /**
     * The getLicenseId returns the value of the LicenseId
     * @return the value of the LicenseId
     */
    public int getLicenseId() {
        return licenseId;
    }
    /**
     * The getLicenseType returns the value of the LicenseType
     * @return the value of the LicenseType
     */
    public String getLicenseType() {
        return licenseType;
    }
}
