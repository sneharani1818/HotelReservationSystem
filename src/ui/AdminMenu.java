package ui;

import api.AdminResource;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. See all customers");
            System.out.println("2. See all rooms");
            System.out.println("3. See all reservations");
            System.out.println("4. Add a room");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    seeAllReservations();
                    break;
                case "4":
                    addRoom();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    private static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    private static void seeAllReservations() {
        adminResource.displayAllReservations();
    }

private static void addRoom() {
    List<IRoom> rooms = new ArrayList<>();

    while (true) {
        System.out.print("Room number: ");
        String roomNumber = scanner.nextLine();

        System.out.print("Is this a free room? (y/n): ");
        boolean isFree = scanner.nextLine().equalsIgnoreCase("y");

        System.out.print("Room type (SINGLE/DOUBLE): ");
        RoomType roomType = RoomType.valueOf(scanner.nextLine().toUpperCase());

        if (isFree) {
            rooms.add(new FreeRoom(roomNumber, roomType));
        } else {
            System.out.print("Room price: ");
            double price = Double.parseDouble(scanner.nextLine());
            rooms.add(new Room(roomNumber, price, roomType));
        }

        System.out.print("Add another room? (y/n): ");
        if (!scanner.nextLine().equalsIgnoreCase("y")) break;
    }

    adminResource.addRoom(rooms);
    System.out.println("Rooms added successfully.");
}

}
