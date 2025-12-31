//package ui;
//
//import api.HotelResource;
//import model.IRoom;
//import model.Reservation;
//
//import java.text.SimpleDateFormat;
//import java.util.Collection;
//import java.util.Date;
//import java.util.Scanner;
//
//public class MainMenu {
//
//    private static final HotelResource hotelResource = HotelResource.getInstance();
//    private static final Scanner scanner = new Scanner(System.in);
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//    public static void start() {
//        boolean running = true;
//
//        while (running) {
//            System.out.println("\n=== Main Menu ===");
//            System.out.println("1. Find and reserve a room");
//            System.out.println("2. See my reservations");
//            System.out.println("3. Create an account");
//            System.out.println("4. Admin");
//            System.out.println("5. Exit");
//            System.out.print("Choose an option: ");
//
//            String choice = scanner.nextLine();
//
//            switch (choice) {
//                case "1":
//                    findAndReserveRoom();
//                    break;
//                case "2":
//                    seeMyReservations();
//                    break;
//                case "3":
//                    createAccount();
//                    break;
//                case "4":
//                    AdminMenu.start();
//                    break;
//                case "5":
//                    running = false;
//                    System.out.println("Goodbye!");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }
//
//private static void findAndReserveRoom() {
//    try {
//        System.out.print("Check-in date (dd/MM/yyyy): ");
//        Date checkIn = sdf.parse(scanner.nextLine());
//
//        System.out.print("Check-out date (dd/MM/yyyy): ");
//        Date checkOut = sdf.parse(scanner.nextLine());
//
//        Collection<IRoom> rooms = hotelResource.findARoom(checkIn, checkOut);
//
//        if (rooms.isEmpty()) {
//            System.out.println("No available rooms.");
//            return;
//        }
//
//        rooms.forEach(System.out::println);
//
//        System.out.print("Book a room? (y/n): ");
//        if (!scanner.nextLine().equalsIgnoreCase("y")) return;
//
//        System.out.print("Email: ");
//        String email = scanner.nextLine();
//
//        if (hotelResource.getCustomer(email) == null) {
//            System.out.println("Customer not found. Please create an account first.");
//            return;
//        }
//
//        System.out.print("Room number: ");
//        String roomNumber = scanner.nextLine();
//        IRoom room = hotelResource.getRoom(roomNumber);
//
//        Reservation reservation =
//                hotelResource.bookARoom(email, room, checkIn, checkOut);
//
//        System.out.println("Reservation successful!");
//        System.out.println(reservation);
//
//    } catch (Exception e) {
//        System.out.println("Error: " + e.getMessage());
//    }
//}
//
//
//    private static void seeMyReservations() {
//        System.out.print("Enter your email: ");
//        String email = scanner.nextLine();
//
//        Collection<Reservation> reservations =
//                hotelResource.getCustomersReservations(email);
//
//        if (reservations == null || reservations.isEmpty()) {
//            System.out.println("No reservations found.");
//        } else {
//            reservations.forEach(System.out::println);
//        }
//    }
//
//    private static void createAccount() {
//        try {
//            System.out.print("First Name: ");
//            String firstName = scanner.nextLine();
//
//            System.out.print("Last Name: ");
//            String lastName = scanner.nextLine();
//
//            System.out.print("Email: ");
//            String email = scanner.nextLine();
//
//            hotelResource.createACustomer(email, firstName, lastName);
//            System.out.println("Account created successfully.");
//
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//}
package ui;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void start() {
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    findAndReserveRoom();
                    break;
                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    AdminMenu.start();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void findAndReserveRoom() {
        try {
            System.out.print("Check-in date (dd/MM/yyyy): ");
            Date checkIn = sdf.parse(scanner.nextLine());

            System.out.print("Check-out date (dd/MM/yyyy): ");
            Date checkOut = sdf.parse(scanner.nextLine());

            Collection<IRoom> rooms = hotelResource.findARoom(checkIn, checkOut);

            if (rooms.isEmpty()) {
                System.out.println("No rooms available for selected dates.");

                Collection<IRoom> recommended =
                        hotelResource.findRecommendedRooms(checkIn, checkOut);

                if (recommended.isEmpty()) {
                    System.out.println("No alternative dates found.");
                    return;
                }

                System.out.println("Rooms available on alternative dates:");
                recommended.forEach(System.out::println);
                return;
            }

            rooms.forEach(System.out::println);

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter room number: ");
            String roomNumber = scanner.nextLine();

            Reservation reservation =
                    hotelResource.bookARoom(email,
                            hotelResource.getRoom(roomNumber),
                            checkIn, checkOut);

            System.out.println("Reservation successful!");
            System.out.println(reservation);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void seeMyReservations() {
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Collection<Reservation> reservations =
                hotelResource.getCustomersReservations(email);

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(System.out::println);
        }
    }

    private static void createAccount() {
        try {
            System.out.print("First name: ");
            String first = scanner.nextLine();
            System.out.print("Last name: ");
            String last = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();

            hotelResource.createACustomer(email, first, last);
            System.out.println("Account created.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
