package cinema;

import com.sun.security.jgss.GSSUtil;

import java.util.Scanner;

public class Cinema {

    public static int rows;
    public static int seatsInEachRow;
    public static char[][] cinema;

    public static int scanRows(Scanner scanner){
        System.out.println("Enter the number of rows:");
        return scanner.nextInt();
    }

    public static int scanSeatsInEachRow(Scanner scanner){
        System.out.println("Enter the number of seats in each row:");
        return scanner.nextInt();
    }

    public static int scanRow(Scanner scanner){
        System.out.println("Enter a row number:");
        return scanner.nextInt();
    }

    public static int scanSeat(Scanner scanner){
        System.out.println("Enter a seat number in that row:");
        return scanner.nextInt();
    }

    public static void printCinema(){
        System.out.println("Cinema:");

        System.out.print("  ");
        for(int i = 1; i <= seatsInEachRow; i++)
            System.out.print(i + " ");
        System.out.println();

        for(int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");

            for (int j = 0; j < seatsInEachRow; j++) {
                System.out.print(j == seatsInEachRow - 1 ? cinema[i][j] : cinema[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public static void generateCinema(){
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < seatsInEachRow; j++)
                cinema[i][j] = 'S';
    }

    public static void updateCinema(int row, int seat){
        cinema[row - 1][seat - 1] = 'B';
    }

    public static void printMenu(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");
    }

    public static void interfaceMenu(Scanner scanner){
        int choice = -1;

        while(choice != 0) {
            printMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printCinema();
                    break;
                case 2:
                    interfaceBuy(scanner);
                    break;
                default:
                    return;
            }
        }

    }

    public static void interfaceBuy(Scanner scanner){
        int row = scanRow(scanner);
        int seat = scanSeat(scanner);

        updateCinema(row, seat);
        printTicketPrice(row, seat);
    }

    public static void printInterfaceOfTotalIncome(Scanner scanner){
        System.out.println("Total income:");
        System.out.println("$" + totalIncome());
    }

    public static int totalIncome(){
        int totalSeats = rows * seatsInEachRow;
        return totalSeats <= 60 ? totalSeats * 10 : rows / 2 * seatsInEachRow * 10 + (rows - rows / 2) * seatsInEachRow * 8;
    }

    public static void printTicketPrice(int row, int seat){
        System.out.println("Ticket price: $" + ticketPrice(row, seat));
    }

    public static int ticketPrice(int row, int seat){
        if(rows * seatsInEachRow <= 60 || row <= rows / 2)
            return 10;
        return 8;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        rows = scanRows(scanner);
        seatsInEachRow = scanSeatsInEachRow(scanner);

        cinema = new char[rows][seatsInEachRow];
        generateCinema();

        interfaceMenu(scanner);
    }
}