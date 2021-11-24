package com.company;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * This is just a demo to create a Transport application and shows how to work
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("------ Welcome To Taxino Application ------");
        Scanner scan = new Scanner(System.in);
        Database database = new Database();
        Login login = new Login(database);
        Notification notification1 = new Notification(database);
        RideRequests rideRequests = new RideRequests(notification1);
        while(true)
        {
            System.out.println("--- Main Menu ---");
            System.out.println("1) Admin");
            System.out.println("2) User");
            int option = 0 ;
            option = scan.nextInt();
            String userName,password;
            switch (option)
            {
                case 1:
                {
                    Admin admin = new Admin(database);
                    System.out.print("Enter your User Name: ");
                    userName = scan.next();
                    System.out.print("Enter your Password: ");
                    password = scan.next();
                    if(!admin.Login(userName,password))
                    {
                        System.out.println("Invalid login...");
                        continue;
                    }
                    System.out.println("--- Admin Menu ---");
                    System.out.println("1) verify");
                    System.out.println("2) suspend");
                    int adminOption = 0;
                    adminOption = scan.nextInt();
                    switch (adminOption) {
                        case 1: {
                            admin.verfiy();
                            break;
                        }
                        case 2:
                        {
                            String name = scan.next();
                            boolean done = admin.suspend(name);
                            if(done)
                            {
                                System.out.println("Suspend Successfully...");
                            }
                            else
                            {
                                System.out.println("Invalid Suspend...");
                            }
                            break;
                        }
                    }
                    break;
                }
                case 2:
                {
                    System.out.println("--- User Menu ---");
                    System.out.println("1) Driver");
                    System.out.println("2) Passenger");
                    System.out.println("3) Back to Main Menu");
                    String mobile,nationalId;
                    int optionUser = 0 ;
                    optionUser= scan.nextInt();
                    switch (optionUser)
                    {
                        case 1:
                        {
                            while (true)
                            {
                                Driver driver = null;
                                System.out.println("--- Driver Menu ---");
                                System.out.println("1) Register");
                                System.out.println("2) Login");
                                System.out.println("3) Back to User Menu");
                                int log = 0,liscenseId;
                                String liscensetype;
                                log= scan.nextInt();
                                if(log==1)
                                {
                                    System.out.print("Enter your User Name: ");
                                    userName = scan.next();
                                    System.out.print("Enter your Password: ");
                                    password = scan.next();
                                    System.out.print("Enter your Mobile Phone: ");
                                    mobile = scan.next();
                                    System.out.print("Enter your National Id: ");
                                    nationalId= scan.next();
                                    System.out.print("Enter your License Id: ");
                                    liscenseId = scan.nextInt();
                                    System.out.print("Enter your License type: ");
                                    liscensetype = scan.next();
                                    DrivingLiscence drivingLiscence = new DrivingLiscence(liscenseId,liscensetype);
                                    driver = new Driver(userName,mobile,password,nationalId,drivingLiscence,rideRequests);
                                    boolean data = database.addUserList(driver);
                                    if(data)
                                    {
                                        System.out.println("Register has been done...");
                                    }
                                    else
                                    {
                                        System.out.println("Something has been Wrong with (Username/NationalId)...");
                                    }
                                    System.out.println("Waiting for admin for verifying your account...");
                                    break;
                                }
                                else if(log==2)
                                {
                                    System.out.print("Enter your User Name: ");
                                    userName = scan.next();
                                    System.out.print("Enter your Password: ");
                                    password = scan.next();
                                    driver = (Driver) login.Login(password,userName);
                                    if(driver==null)
                                    {
                                        System.out.println("Invalid Login...");
                                        continue;
                                    }
                                    while (true)
                                    {
                                       System.out.println("--- Driver Option ---");
                                       System.out.println("1) Favourite Areas");
                                       System.out.println("2) List Rate");
                                       System.out.println("3) Ride Requests");
                                       System.out.println("4) Notification");
                                       System.out.println("5) Favourite Area Rides");
                                       System.out.println("6) Back to Driver Menu");
                                       log = scan.nextInt();
                                       if(log==1)
                                       {
                                           while (true)
                                           {
                                               System.out.println("--- Favourite Areas ---");
                                               System.out.println("1) Add");
                                               System.out.println("2) Delete");
                                               System.out.println("3) Show");
                                               System.out.println("4) Back to Driver Option");
                                               log = scan.nextInt();
                                               if(log==1)
                                               {
                                                   System.out.print("Enter Favourite Area: ");
                                                   String favourite = scan.next();
                                                   driver.addFavouriteAreas(favourite);
                                               }
                                               else if(log==2)
                                               {
                                                   System.out.print("Enter Favourite Area: ");
                                                   String favourite = scan.next();
                                                   driver.deleteFavouriteArea(favourite);
                                               }
                                               else if(log==3)
                                               {
                                                   ArrayList<String> list = driver.getFavouriteAreas();
                                                   int counter= 1 ;
                                                   for(String print: list)
                                                   {
                                                       System.out.println(counter+") "+print);
                                                       counter++;
                                                   }
                                               }
                                               else
                                               {
                                                   break;
                                               }
                                           }
                                       }
                                       else if(log==2)
                                       {
                                           driver.showListRate();
                                       }
                                       else if(log==3)
                                       {
                                           ArrayList<Passenger> list = driver.getRideRequests().getPendingRide();
                                           boolean check = driver.showRideRequests();
                                           if(!check)
                                           {
                                               continue;
                                           }
                                           System.out.print("Enter the Request Number that you accepted: ");
                                           int numRequest = scan.nextInt();
                                           Ride ride = list.get(numRequest-1).getPendingRide();
                                           System.out.print("Enter the offer: ");
                                           int offer = scan.nextInt();
                                           ride.setPrice(offer);
                                           list.remove(numRequest-1);
                                           ride.setDriverInfo(driver.getUserName());
                                           ride.setStatus(true);
                                       }
                                       else if(log==4)
                                       {
                                            ArrayList<String>notification =  driver.getNotification();
                                            for(String l :notification)
                                            {
                                               System.out.println(l);
                                            }
                                       }
                                       else if(log==5)
                                       {
                                           HashMap<Passenger,Ride> list = driver.getListFARide();
                                           for(Passenger passenger : list.keySet())
                                           {
                                               System.out.println("Passenger Ride:");
                                               System.out.println("Name: "+passenger.getUserName()+ " Source: "+list.get(passenger).getSource()
                                               + " Destination: " + list.get(passenger).getDestination());
                                           }
                                       }
                                       else
                                       {
                                           break;
                                       }
                                   }
                                }
                                else
                                {
                                    break;
                                }
                            }
                            break;
                        }
                        case 2:
                        {
                            Passenger passenger = null;
                            while (true)
                            {
                                System.out.println("--- Passenger Menu ---");
                                System.out.println("1) Register");
                                System.out.println("2) Login");
                                System.out.println("3) Back to User Menu");
                                int log = 0;
                                log= scan.nextInt();
                                if(log==1)
                                {
                                    System.out.print("Enter your User Name: ");
                                    userName = scan.next();
                                    System.out.print("Enter your Password: ");
                                    password = scan.next();
                                    System.out.print("Enter your Mobile Phone: ");
                                    mobile = scan.next();
                                    passenger = new Passenger(userName,mobile,password);
                                    boolean data = database.addUserList(passenger);
                                    if(data)
                                    {
                                        System.out.println("Register has been done...");
                                    }
                                    else
                                    {
                                        System.out.println("Something has been Wrong with (Username/NationalId)...");
                                    }
                                    break;
                                }
                                else if(log==2) {
                                    System.out.print("Enter your User Name: ");
                                    userName = scan.next();
                                    System.out.print("Enter your Password: ");
                                    password = scan.next();
                                    passenger = (Passenger) login.Login(password, userName);
                                    if (passenger == null) {
                                        System.out.println("Invalid Login...");
                                        continue;
                                    }
                                    while (true) {
                                        System.out.println("--- Passenger Option ---");
                                        System.out.println("1) Request Ride");
                                        System.out.println("2) List Rides");
                                        System.out.println("3) Check Request");
                                        System.out.println("4) Back to Passenger Menu");
                                        log = scan.nextInt();
                                        if (log == 1) {
                                            System.out.print("Enter the source: ");
                                            String source = scan.next();
                                            System.out.print("Enter the destination: ");
                                            String destination = scan.next();
                                            Ride ride = new Ride(source, destination);
                                            passenger.requestRide(ride);
                                            rideRequests.addPendingRide(passenger);
                                        } else if (log == 2) {
                                            ArrayList<Ride> list = passenger.getRidelist();
                                            for (Ride ride : list) {
                                                System.out.println("Source: " + ride.getSource() + " Destination: " + ride.getDestination()
                                                        + "Price: " + ride.getPrice());
                                            }
                                        }
                                        else if(log==3)
                                        {
                                            Ride pending = passenger.getPendingRide();
                                            if(pending.getStatus() == true)
                                            {
                                                passenger.addRide(pending);
                                                ArrayList<User> list = database.getUserList();
                                                String name = pending.getDriverInfo();
                                                Driver driver = null;
                                                for(int i=0;i<list.size();i++)
                                                {
                                                    if(Driver.class.isInstance(list.get(i)) && name.equals(list.get(i).getUserName()))
                                                    {
                                                        driver = (Driver) list.get(i);
                                                        break;
                                                    }
                                                }
                                                driver.showDriver();
                                                System.out.print("Enter the Rate(1/5): ");
                                                int rate = 0;
                                                rate = scan.nextInt();
                                                driver.addListRate(passenger,rate);
                                                driver.addFavouriteAreaRide(passenger,passenger.getPendingRide());
                                            }
                                            else
                                            {
                                                System.out.println("Request still pending...");
                                            }
                                        }
                                        else
                                        {
                                            break;
                                        }
                                    }
                                }
                                else
                                {
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
}
