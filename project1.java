import java.util.*;

public class HelloWorld {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Miles driven on Monday: ");
        int m_hours = scnr.nextInt();
        System.out.print("Miles driven on Tuesday: ");
        int t_hours = scnr.nextInt();
        System.out.print("Miles driven on Wednesday: ");
        int w_hours = scnr.nextInt();
        System.out.print("Miles driven on Thursday: ");
        int r_hours = scnr.nextInt();
        System.out.print("Miles driven on Friday: ");
        int f_hours = scnr.nextInt();
        System.out.print("Miles driven on Saturday: ");
        int s_hours = scnr.nextInt();
        System.out.print("Miles driven on Sunday: ");
        int d_hours = scnr.nextInt();
        
        System.out.println("Week\tMonday\tTuesday\tWednesday\tThursday\tFriday\tSaturday\tSunday\tTotal/Week");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + "\t\t");
            for (int j = 0; j < 7; j++) {
                if (i == 5 && j >= 2) {
                    System.out.print(String.format("%-8s", "0"));
                } else {
                    int day = (7*(i-1) + j) + 1;
                    System.out.print(String.format("%-8s", day));
                }
            }
            System.out.print("\n");
        }
    }
}
