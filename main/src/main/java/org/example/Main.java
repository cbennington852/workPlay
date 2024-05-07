package org.example;

public class Main {
    public static void main(String[] args) {

        College college = new College("Auburn University");
        System.out.println("State" + college.getState());
        System.out.println("City" + college.getCity());
    }
}