// Written by Christian Christovich
// I couldn't do the formatting in an elegant way I apologize

import java.util.*;

public class Trucking {
    public static void main(String[] args) {
        System.out.println("Spring 2024 - CS1083 - Section 004 - Project 1 - Trucking - written by CHRISTIAN CHRISTOVICH\n");
        
        // Get user input for each day
        Scanner scnr = new Scanner(System.in);
        System.out.println("Miles driven on Monday: ");
        int m_hours = scnr.nextInt();
        System.out.println("Miles driven on Tuesday: ");
        int t_hours = scnr.nextInt();
        System.out.println("Miles driven on Wednesday: ");
        int w_hours = scnr.nextInt();
        System.out.println("Miles driven on Thursday: ");
        int r_hours = scnr.nextInt();
        System.out.println("Miles driven on Friday: ");
        int f_hours = scnr.nextInt();
        System.out.println("Miles driven on Saturday: ");
        int s_hours = scnr.nextInt();
        System.out.println("Miles driven on Sunday: \n");
        int d_hours = scnr.nextInt();
        
        HashMap<Integer, Integer> day_hours = new HashMap<Integer, Integer>(); // declare new hashmap to hold integer representation of day and associated hours worked

        day_hours.put(1, m_hours); // \
        day_hours.put(2, t_hours); //  |
        day_hours.put(3, w_hours); //  |
        day_hours.put(4, r_hours); //   > initialize hashmap values
        day_hours.put(5, f_hours); //  |
        day_hours.put(6, s_hours); //  |
        day_hours.put(0, d_hours); // /
        int total = 0;
        int week = 0;

        System.out.println("Week\tMonday\tTuesday\tWednesday\tThursday\tFriday\tSaturday\tSunday\t\tTotal/Week"); // banner
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + "\t\t"); // print week number woth padding
            for (int j = 0; j < 7; j++) {
                int day = (7*(i-1) + j) + 1; // print day number with some weird arithmetic
                if (i == 5 && j >= 2) {
                    if ((j == 3) || (j == 4) || (j == 6)) { // padding for null days
                        System.out.print("\t");
                    }
                    System.out.print("0-0\t\t"); // null days
                } else if ((((7*(i-1) + j) + 1) % 7 == 3) || (((7*(i-1) + j) + 1) % 7 == 4) || (((7*(i-1) + j) + 1) % 7 == 6)) { // padding for specific columns for formatting sake
                    System.out.printf("%d-%d\t\t", day, day_hours.get(((7*(i-1) + j) + 1) % 7)); // print day with associated hours worked with more weird arithmetic
                } else {
                    System.out.printf("%d-%d\t", day, day_hours.get(((7*(i-1) + j) + 1) % 7)); // same thing
                }
            }
            if (i < 5) {
                for (int k = 0; k < 7; k++) {
                    week += day_hours.get(k); // hours per week
                }
            } else {
                week += day_hours.get(1); // hours worked on last week
                week += day_hours.get(2); // ''
            }
            total += week; // total hours
            System.out.printf("\tW%d-%d\n", i, week);
            week = 0; // reset week hours
        }
        System.out.println("Total Miles Driven: " + total);
    }
}
