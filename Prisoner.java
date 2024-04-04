import java.util.*;
public class Prisoner {
    public static int[] gPrisoner;
    public static int gCenterCounter;
    public static final Scanner gSCANNER = new Scanner(System.in);
    public static final int gMAX_CENTERS = 12;
    public static void main(String[] args) {
        System.out.println("Spring 2024 - UTSA - CS1083 - Section 004 - Project 2 - Prisoner - written by CHRISTIAN CHRISTOVICH\n");
        System.out.print("Please, enter the initial number of detention centers in the database (Max 12): ");
        gCenterCounter = gSCANNER.nextInt();
        while ((gCenterCounter > gMAX_CENTERS) || (gCenterCounter < 0)) {
            System.out.println("ERROR, you need to enter a valid value based on the next message.");
            System.out.print("Please, enter the initial number of detention centers in the database (Max 12): ");
            gCenterCounter = gSCANNER.nextInt();
        }
        gPrisoner = new int[gCenterCounter];
        while (true) {
            System.out.println("\nMAIN MENU");
            System.out.println("0 - Clear centers, 1 - List centers, 2 - Add/Subtract prisoners, 3 - Add new center, 4 - Center analysis, 5 - Transfer prisoners, 6 - Exit");
            System.out.print("Select an option : ");
            int option = gSCANNER.nextInt();
            while ((option > 6) || (option < 0)) {
                System.out.println("ERROR, you need to enter a valid value based on the next message.");
                System.out.println("\nMAIN MENU");
                System.out.println("0 - Clear centers, 1 - List centers, 2 - Add/Subtract prisoners, 3 - Add new center, 4 - Center analysis, 5 - Transfer prisoners, 6 - Exit");
                System.out.print("Select an option : ");
                option = gSCANNER.nextInt();
            }
            if (option == 0) {
                clear();
            } else if (option == 1) {
                list();
            } else if (option == 2) {
                addSub();
            } else if (option == 3) {
                append();
            } else if (option == 4) {
                analysis();
            } else if (option == 5) {
                transfer();
            } else {
                System.out.println("Thank you for using the Prisoner detention center program!");
                break;
            }
        }
    }
    public static void clear() {
        for (int i = 0; i < gCenterCounter; i++) {
            gPrisoner[i] = 0;
        }
    }
    public static void addSub() {
        int index;
        int quantity;
        char add;
        System.out.printf("Enter the index (%d to %d) : ", 0, gCenterCounter - 1);
        index = gSCANNER.nextInt();
        while ((index >= gCenterCounter) || (index < 0)) {
            System.out.println("ERROR, you need to enter a valid value based on the next message.");
            System.out.printf("Enter the index (%d to %d) : ", 0, gCenterCounter - 1);
            index = gSCANNER.nextInt();
        }
        System.out.printf("The current occupancy of the center at index %d is : %d\n", index, gPrisoner[index]);
        System.out.printf("Enter the number of the prisoners in this operation (%d to %d) : ", 0, 200);
        quantity = gSCANNER.nextInt();
        while ((quantity > 200) || (quantity < 0)) {
            System.out.println("ERROR, you need to enter a valid value based on the next message.");
            System.out.printf("The current occupancy of the center at index %d is : %d\n", index, gPrisoner[index]);
            System.out.printf("Enter the number of the prisoners in this operation (%d to %d) : ", 0, 200);
            quantity = gSCANNER.nextInt();
        }
        System.out.printf("Are the prisoners to be added to the center at index %d? (Y/N): ", index);
        add = gSCANNER.next().charAt(0);
        while ((add != 'Y') && (add != 'N')) {
            System.out.println("ERROR, you need to enter a valid value based on the next message.");
            System.out.printf("Are the prisoners to be added to the center at index %d? (Y/N): ", index);
            add = gSCANNER.next().charAt(0);
        }
        if (add == 'Y') {
            gPrisoner[index] += quantity;
        } else if (quantity > gPrisoner[index]){
            System.out.printf("ERROR, you can't subtract more prisoners than the inmates at center at index %d. Try again\n", index);
        } else {
            gPrisoner[index] -= quantity;
        }
    }
    public static void list() {
        System.out.println("List of Prisoner Detention Center Occupancy");
        for(int i = 0; i < gCenterCounter; i++) {
            System.out.printf("Center[%d] : %d\n", i, gPrisoner[i]);
        }
    }
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
    public static void append() {
        if (gCenterCounter >= gMAX_CENTERS) {
            System.out.println("Cannot add a new detention center as the maximum number of detention centers has been reached.");
        } else {
            System.out.printf("Enter the number of prisoners to assign to the new center (0 - 200) : ");
            int quantity = gSCANNER.nextInt();
            while ((quantity > 200) || (quantity < 0)) {
                System.out.println("ERROR, you need to enter a valid value based on the next message.");
                System.out.printf("Enter the number of prisoners to assign to the new center (0 - 200) : ");
                quantity = gSCANNER.nextInt();
            }
            gPrisoner = Arrays.copyOf(gPrisoner, gPrisoner.length + 1);
            gPrisoner[gPrisoner.length - 1] = quantity;
            gCenterCounter += 1;
        }
    }
    public static void transfer() {
        int idxFrom;
        int idxTo;
        int quantity;
        System.out.printf("Enter the center where the prisoners will be transfered from (%d to %d) : ", 0, gCenterCounter - 1);
        idxFrom = gSCANNER.nextInt();
        while ((idxFrom >= gCenterCounter) || (idxFrom < 0)) {
            System.out.println("ERROR, you need to enter a valid value based on the next message.");
            System.out.printf("Enter the center where the prisoners will be transfered from (%d to %d) : ", 0, gCenterCounter - 1);
            idxFrom = gSCANNER.nextInt();
        }
        System.out.printf("The current occupancy of the center at index %d is : %d\n", idxFrom, gPrisoner[idxFrom]);
        System.out.printf("Enter the number of the prisoners to transfer to the other center (%d to %d) : ", 0, gPrisoner[idxFrom]);
        quantity = gSCANNER.nextInt();
        while ((quantity > gPrisoner[idxFrom]) || (quantity < 0)) {
            System.out.println("ERROR, you need to enter a valid value based on the next message.");
            System.out.printf("The current occupancy of the center at index %d is : %d\n", idxFrom, gPrisoner[idxFrom]);
            System.out.printf("Enter the number of the prisoners to transfer to the other center (%d to %d) : ", 0, gPrisoner[idxFrom]);
            quantity = gSCANNER.nextInt();
        }
        System.out.printf("Enter the center where the prisoners will be transfered to (%d to %d) that is not %d: ", 0, gCenterCounter - 1, idxFrom);
        idxTo = gSCANNER.nextInt();
        while ((idxTo >= gCenterCounter) || (idxTo < 0) || (idxTo == idxFrom)) {
            System.out.println("ERROR, you need to enter a valid value based on the next message.");
            System.out.printf("Enter the center where the prisoners will be transfered to (%d to %d) that is not %d: ", 0, gCenterCounter - 1, idxFrom);
            idxTo = gSCANNER.nextInt();
        }
        gPrisoner[idxFrom] -= quantity;
        gPrisoner[idxTo] += quantity;
    }
}
