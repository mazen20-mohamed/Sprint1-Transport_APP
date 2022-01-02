package com.company;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class WebServices 
{
    private static boolean flagpassenger=false,flagdriver=false,flagadmin=false;
    Database database = Database.getInstance();
    Passenger passenger;Driver driver;Admin admin= new Admin(database);Login l = new Login(database);
    RideRequests ride = RideRequests.getInstance();
    @PostMapping("/registerPassenger")
    public String registerPassenger(String userName, String mobileNumber,String Password,String birthday)
    {
        passenger = new Passenger(userName, Password, mobileNumber, birthday);
        flagpassenger = database.addUserList(passenger);
        if(flagpassenger)
        {
            flagpassenger=true;
            return "Register succesfully";
        }
        return "Invalid Register";
    }
    @PostMapping("/registerDriver")
    public String registerDriver(String userName, String mobileNumber,String Password, String nationalId, int licenseId ,String licenseType, int limitNumberOfPassenger)
    {
        driver = new Driver(userName,  mobileNumber, Password,nationalId,new DrivingLiscence(licenseId,licenseType),limitNumberOfPassenger);
        DriverTasks task = new DriverTasks(driver);
        driver.setDriverTasks(task);
        flagdriver= database.addUserList(driver);
        if(flagdriver)
        {
            flagdriver = true;
            return "Register succesfully";
        }   
        return "Invalid Register";
    }
    @PostMapping("/loginUser")
    public String loginUser(String userName, String password)
    {
        User user = l.login(userName,password);
        if(user instanceof Driver)
        {
            driver = (Driver)user;
            flagdriver =true;
            return "Login succesfully";
        }
        else if(user instanceof Passenger)
        {
            passenger = (Passenger)user;
            flagpassenger= true;
            return "Login succesfully";
        }
        return "Invalid Login";
    }
    @PostMapping("/loginAdmin")
    public String loginAdmin(String userName , String pin)
    {
        flagadmin = admin.Login(userName, pin);
        if(flagadmin)
        {
            return "Login succesfully";
        }
        return "Invalid login";
    }
    @PutMapping("/loginAdmin/verfiy")
    public void adminVerfiy()
    {
        if(flagadmin)
        {
            admin.verfiy();
        }
    }
    @PutMapping("/loginAdmin/suspend")
    public String suspendAdmin(String userName)
    {
        if(admin.suspend(userName) && flagadmin)
        {
            return "User has been suspended";
        }
        return "Invalid";
    }
    @PostMapping("/loginAdmin/area")
    public void Adminaddarea(String area)
    {
        if(flagadmin)
        {
            admin.addDiscountArea(area);
        }
    }
    @GetMapping("/loginAdmin/rideEvent")
    public void rideEvent(String location)
    {
        if(flagadmin)
        {
            admin.rideEvent(location);
        }
    }
    @PostMapping("/registerDriver/addFavouriteArea")
    public void addFavouriteAreas(String area)
    {
        if(flagdriver)  
        {
            driver.addFavouriteAreas(area);
        }
    }
    @PostMapping("/registerDriver/deleteFavouriteArea")
    public void deleteFavouriteAreas(String area)
    {
        if(flagdriver)
        {
            driver.deleteFavouriteArea(area);
        }
    }
    @GetMapping("/registerDriver/getListRate")
    public void getListRate()
    {
        if(flagdriver)
        {
            driver.getDriverTasks().showListRate();
        }
    }
    @GetMapping("/registerDriver/showRideRequests")
    public boolean showRideRequest()
    {
        if(flagdriver)
        {
            ArrayList<Passenger> list = driver.getRideRequests().getPendingRide();
            boolean check = driver.getDriverTasks().showRideRequests();
            if(!check)
            {
                return false;
            }
            System.out.println("Enter number of ride you want to choose and the price");
            return true;
        }
        return false;
    }
    @PutMapping("/registerDriver/showRideRequests/rideRequests")
    public void rideRequest(int number,int offer)
    {
        if(flagdriver)
        {
            boolean check = showRideRequest();
            if(!check)
            {
                return;
            }
            Ride ride =RideRequests.getInstance().getPendingRide().get(number-1).pendingRide;
            ride.setPrice(offer);
            RideRequests.getInstance().getPendingRide().remove(number-1);
            ride.setDriverInfo(driver.getUserName());
            ride.setStatus(true);
            driver.setBusy(true);
            ride.getEvent().putPrice(driver.getUserName());
        }
    }
    @GetMapping("/registerDriver/getNotifications")
    public void getNotification()
    {
        if(flagdriver)
        {
            ArrayList<String>array = driver.getNotification();
            for(String l :array)
            {
                System.out.println(l);
            }
        }
    }
    @GetMapping("/registerDriver/favouriteAreaRides")
    public void favouriteAreaRides()
    {
        if(flagdriver)
        {
            HashMap<Passenger,Ride> list = driver.getListFARide();
            for(Passenger passenger : list.keySet())
            {
                System.out.println("Passenger Ride:");
                System.out.println("Name: "+passenger.getUserName()+ " Source: "+list.get(passenger).getSource()
                + " Destination: " + list.get(passenger).getDestination());
            }
        }
    }
    @PostMapping("/registerPassenger/requestRide")
    public void requestRide(String source , String destination,int numberOfPasssengers)
    {
        if(flagpassenger)
        {
            passenger.requestRide(new Ride(source, destination,numberOfPasssengers));
            ride.addPendingRide(passenger);
        }
    }
    @GetMapping("/registerPassenger/listRide")
    public void listRide()
    {
        if(flagpassenger)
        {
            ArrayList<Ride> list = passenger.getRidelist();
            for (Ride ride : list) {
                     System.out.println("Source: " + ride.getSource() + " Destination: " + ride.getDestination()
                    + "Price: " + ride.getPrice());
            }
        }
    }
    @GetMapping("/registerPassenger/checkRequest")
    public void checkRequest()
    {
        if(flagpassenger)
        {
            Ride pending = passenger.getPendingRide();
            if(pending.getStatus() == true && pending.getAcceptedPrice()==false)
            {
                System.out.println(passenger.priceWithDiscount());
            }
            else if(pending.getStatus() == false)
            {
                System.out.println("Ride still pending");
            }
        }
    }
    @PostMapping("/registerPassenger/checkRequest/confirmRide")
    public void confirmRide(String answer)
    {
        if(flagpassenger)
        {
            Ride pending = passenger.getPendingRide();
            if(answer.equalsIgnoreCase("yes"))
            {
                pending.setAcceptedPrice(true);
                pending.getEvent().acceptPrice(passenger.getUserName());
                System.out.println("Happy Ride");
            }
            else 
            {
                String name = pending.getDriverInfo();
                Driver d = null;
                for(User u : Database.getInstance().getUserList())
                {
                    if(u instanceof Driver && name.equals(u.getUserName()))
                    {
                        d = (Driver) u;
                        break;
                    }
                }
                d.setBusy(false);
            }
        }
    }
    @PostMapping("/registerPassenger/arrivedLocation")
    public void arrivedLocation()
    {
        if(flagpassenger)
        {
            passenger.pendingRide.getEvent().arrivedLocation();
        }
    }
    @PostMapping("/registerPassenger/arrivedDestination")
    public void arrivedDestination(int rate)
    {
        if(flagpassenger)
        {
            passenger.pendingRide.getEvent().arrivedDistination();
            Ride pending = passenger.getPendingRide();
            passenger.addRide(pending);
            String name = pending.getDriverInfo();
            Driver d = null;
            for(User u : Database.getInstance().getUserList())
            {
                if(u instanceof Driver && name.equals(u.getUserName()))
                {
                    d = (Driver) u;
                    break;
                }
            }
            d.setBusy(false);
            d.addListRate(passenger,rate);
            d.addFavouriteAreaRide(passenger,passenger.getPendingRide());
            
        }
    }   
    @GetMapping("/registerDriver/showDriver")
    public void showDriver()
    {
        driver.getDriverTasks().showDriver();
    }
}
