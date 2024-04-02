
/*
TO-DO:
    1. Logic fixed temporarily with a while true loop, may need to do significant refactoring or have two while loops
    2. Write the append method
    3. Write option 3
*/

import java.util.*;

public class Prisoner {
    public static int[] gPrisoner;
    public static int gCenterCounter;
    public static final Scanner gSCANNER = new Scanner(System.in);
    public static final int gMAX_CENTERS = 12;
    public static void main(String[] args) {
        // Print header
        System.out.println("Spring 2024 - UTSA - CS1083 - Section 004 - Project 2 - Prisoner - written by CHRISTIAN CHRISTOVICH\n");

        // Get number of centers
        System.out.print("Please, enter the initial number of detention centers in the database (Max 12): ");
        gCenterCounter = gSCANNER.nextInt();

        // Ensure number of centers is within range of max
        while ((gCenterCounter > gMAX_CENTERS) || (gCenterCounter < 0)) {
            System.out.println("ERROR, you need to enter a valid value based on the next message.");
            System.out.print("Please, enter the initial number of detention centers in the database (Max 12): ");
            gCenterCounter = gSCANNER.nextInt();
        }

        // initialize gPrisoner with 0 for all indexes
        gPrisoner = new int[gCenterCounter];

        // load sample prisoner counts for testing, will be removed
        gPrisoner[1] = 100;
        gPrisoner[2] = 200;
        gPrisoner[3] = 300;
        gPrisoner[4] = 400;
        gPrisoner[5] = 500;
        gPrisoner[6] = 600;

        // main menu
        while (true) {
            System.out.println("\nMAIN MENU");
            System.out.println("0 - Clear centers, 1 - List centers, 2 - Add/Subtract prisoners, 3 - Add new center, 4 - Center analysis, 5 - Exit");
            System.out.print("Select an option : ");
            int option = gSCANNER.nextInt();

            // selected option logic
            if (option == 0) {
                clear();
            } else if (option == 1) {
                list();
            } else if (option == 2) {
                addSub();
            } else if (option == 3) {
                // if (gCenterCounter == gMAX_CENTERS) {
                //     System.out.println("The databse is full, no more centers can be added");
                // } else {
                //     append();
                // }
            } else if (option == 4) {
                analysis();
            } else if (option == 5) {
                System.out.println("Thank you for using the Prisoner detention center program!");
                break;
            } else {
                System.out.println("ERROR, you need to enter a valid value based on the next message.");
                System.out.println("\nMAIN MENU");
                System.out.println("0 - Clear centers, 1 - List centers, 2 - Add/Subtract prisoners, 3 - Add new center, 4 - Center analysis, 5 - Exit");
                System.out.print("Select an option : ");
                option = gSCANNER.nextInt();
            }
        }
    }

    // clear method
    public static void clear() {
        for (int i = 0; i < gCenterCounter; i++) {
            gPrisoner[i] = 0;
        }
    }

    // addSub method
    public static void addSub() {
        // declare variables
        int index;
        int quantity;
        char add;

        // get validated index
        System.out.printf("Enter the index (%d to %d) : ", 0, gCenterCounter - 1);
        index = gSCANNER.nextInt();
        System.out.println();
        while ((index >= gCenterCounter) || (index < 0)) {
            System.out.println("ERROR, you need to enter a valid value based on the next message.");
            System.out.printf("Enter the index (%d to %d) : ", 0, gCenterCounter - 1);
            index = gSCANNER.nextInt();
            System.out.println();
        }

        System.out.printf("The current occupancy of the center at index %d is : %d\n", index, gPrisoner[index]);

        // get validated prisoners quantity
        System.out.printf("Enter the number of the prisoners (%d to %d) : ", 0, 200);
        quantity = gSCANNER.nextInt();
        System.out.println();
        while ((quantity > 200) || (index < 0)) {
            System.out.println("ERROR, you need to enter a valid value based on the next message.");
            System.out.printf("The current occupancy of the center at index %d is : %d\n", index, gPrisoner[index]);
            System.out.printf("Enter the number of the prisoners (%d to %d) : ", 0, 200);
            quantity = gSCANNER.nextInt();
            System.out.println();
        }

        // get validated add value
        System.out.printf("Are the prisoners to be added to the center at index %d? (Y/N): ", index);
        add = gSCANNER.next().charAt(0);
        System.out.println();
        while ((add != 'Y') && (add != 'N')) {
            System.out.println("ERROR, you need to enter a valid value based on the next message.");
            System.out.printf("Are the prisoners to be added to the center at index %d? (Y/N): ", index);
            add = gSCANNER.next().charAt(0);
            System.out.println();
        }
        if (add == 'Y') {
            gPrisoner[index] += quantity;
        } else if (quantity > gPrisoner[index]){
            System.out.println("ERROR, you can't subtract more prisoners than the inmates at center at index &d. Try again");
        } else {
            gPrisoner[index] -= quantity;
        }
    }

    // list method
    public static void list() {
        System.out.println("List of Prisoner Detention Center Occupancy");
        for(int i = 0; i < gCenterCounter; i++) {
            System.out.printf("Center[%d] : %d\n", i, gPrisoner[i]);
        }
    }

    // analysis method
    public static void analysis() {
        System.out.println("Occupancy Classification Summary");
        int count_under = getPrisonerByClass(0);
        int count_just = getPrisonerByClass(1);
        int count_close = getPrisonerByClass(2);
        int count_full = getPrisonerByClass(3);
        int count_over = getPrisonerByClass(4);
        System.out.printf("Under capacity\t\t: %d\n", count_under);
        System.out.printf("Just right\t\t: %d\n", count_just);
        System.out.printf("Close to over capacity\t: %d\n", count_close);
        System.out.printf("Full\t\t\t: %d\n", count_full);
        System.out.printf("Over capacity\t\t: %d\n", count_over);
    }

    // getPrisonerByClass method
    public static int getPrisonerByClass(int code) {
        int count = 0;
        for (int i = 0; i < gCenterCounter; i++) {
            if (code == 0) {
                if ((gPrisoner[i] >= 0) && (gPrisoner[i] <= 50)) {
                    count += 1;
                }
            } else if (code == 1) {
                if ((gPrisoner[i] >= 51) && (gPrisoner[i] <= 150)) {
                    count += 1;
                }
            } else if (code == 2) {
                if ((gPrisoner[i] >= 151) && (gPrisoner[i] < 200)) {
                    count += 1;
                }
            } else if (code == 3) {
                if ((gPrisoner[i] == 200)) {
                    count += 1;
                }
            } else {
                if ((gPrisoner[i] > 200)) {
                    count += 1;
                }
            }
        }
        return count;
    }

    // append method
    public static void append() {
        // code
    }
}
