package com.company;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Discount {
    private ArrayList<String> holidayDates;
    private ArrayList<String>discountAreas;
    private static Discount discount = null;
    public static Discount getInstance() {
        if (discount == null) {
            discount = new Discount();
            discount.holidayDates = new ArrayList<>(Arrays.asList("06/10","01/05","20/04","02/01"));
            discount.discountAreas = new ArrayList<>();
        }
        return discount;
    }
    public double calculateDiscount(int numberofpassenger, String birthday, int first)
    {
        double dis = 0;
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        String date = dateObj.format(formatter);
        // 21/05/2001
        birthday = birthday.substring(0,4); // 21/05
        if(numberofpassenger==2)
            dis+=0.05;
        if(first==0)
            dis+=0.1;
        if(birthday.equals(date))
            dis+=0.1;
        if(holidayDates.contains(date))
            dis+=0.05;
        return dis;
    }
    public ArrayList<String> getDiscountAreas() {
        return discountAreas;
    }
}
