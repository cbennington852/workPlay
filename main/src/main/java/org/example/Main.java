package org.example;

public class Main {
    public static void main(String[] args) {

        College college = new College("Auburn University");
        System.out.println("State " + college.getState());
        System.out.println("City " + college.getCity());
        System.out.println("Virtual Tour " + college.getVirtualTourLink());


        CollegeList listOne = new CollegeList();
        listOne.colleges.add(new College("University of Mobile"));
        listOne.colleges.add(new College("Gonzaga University"));
        listOne.colleges.add(new College("Central Baptist College"));

        System.out.println(listOne.getAllVirtualTourLinks());
    }
}