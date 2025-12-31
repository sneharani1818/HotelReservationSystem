//package service;
//
//import model.Customer;
//import model.IRoom;
//import model.Reservation;
//
//import java.util.*;
//
//public class ReservationService {
//
//    private static final ReservationService INSTANCE = new ReservationService();
//
//    private final Map<String, IRoom> rooms = new HashMap<>();
//    private final Collection<Reservation> reservations = new ArrayList<>();
//
//    private ReservationService() {}
//
//    public static ReservationService getInstance() {
//        return INSTANCE;
//    }
//
//    public void addRoom(IRoom room) {
//        rooms.put(room.getRoomNumber(), room);
//    }
//
//    public IRoom getARoom(String roomID) {
//        return rooms.get(roomID);
//    }
//
//    public Reservation reserveARoom(Customer customer, IRoom room,
//                                    Date checkInDate, Date checkOutDate) {
//
//        if (!isRoomAvailable(room, checkInDate, checkOutDate)) {
//            throw new IllegalArgumentException("Room is not available for selected dates.");
//        }
//
//        Reservation reservation =
//                new Reservation(customer, room, checkInDate, checkOutDate);
//        reservations.add(reservation);
//        return reservation;
//    }
//
//    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
//        Collection<IRoom> availableRooms = new ArrayList<>();
//
//        for (IRoom room : rooms.values()) {
//            if (isRoomAvailable(room, checkInDate, checkOutDate)) {
//                availableRooms.add(room);
//            }
//        }
//        return availableRooms;
//    }
//
//    private boolean isRoomAvailable(IRoom room, Date checkIn, Date checkOut) {
//        for (Reservation reservation : reservations) {
//            if (reservation.getRoom().equals(room)) {
//                if (checkIn.before(reservation.getCheckOutDate()) &&
//                        checkOut.after(reservation.getCheckInDate())) {
//                    return false; // date overlap
//                }
//            }
//        }
//        return true;
//    }
//
//    public Collection<Reservation> getCustomersReservation(Customer customer) {
//        Collection<Reservation> result = new ArrayList<>();
//        for (Reservation reservation : reservations) {
//            if (reservation.getCustomer().equals(customer)) {
//                result.add(reservation);
//            }
//        }
//        return result;
//    }
//
//    public void printAllReservation() {
//        if (reservations.isEmpty()) {
//            System.out.println("No reservations found.");
//        } else {
//            reservations.forEach(System.out::println);
//        }
//    }
//    public Collection<IRoom> findRecommendedRooms(
//            Date checkInDate,
//            Date checkOutDate) {
//
//        final int MAX_DAYS_TO_SEARCH = 7;
//        Calendar calendar = Calendar.getInstance();
//
//        for (int i = 1; i <= MAX_DAYS_TO_SEARCH; i++) {
//            calendar.setTime(checkInDate);
//            calendar.add(Calendar.DATE, i);
//            Date newCheckIn = calendar.getTime();
//
//            calendar.setTime(checkOutDate);
//            calendar.add(Calendar.DATE, i);
//            Date newCheckOut = calendar.getTime();
//
//            Collection<IRoom> availableRooms =
//                    findRooms(newCheckIn, newCheckOut);
//
//            if (!availableRooms.isEmpty()) {
//                return availableRooms;
//            }
//        }
//        return new ArrayList<>();
//    }
//
//}
package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static final ReservationService INSTANCE = new ReservationService();

    private final Map<String, IRoom> rooms = new HashMap<>();
    private final Collection<Reservation> reservations = new ArrayList<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        return INSTANCE;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomID) {
        return rooms.get(roomID);
    }

    public Reservation reserveARoom(Customer customer, IRoom room,
                                    Date checkInDate, Date checkOutDate) {

        if (!isRoomAvailable(room, checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("Room not available for selected dates.");
        }

        Reservation reservation =
                new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>();

        for (IRoom room : rooms.values()) {
            if (isRoomAvailable(room, checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    // 🔥 RECOMMENDED ROOMS METHOD (USED BY UI)
    public Collection<IRoom> findRecommendedRooms(
            Date checkInDate, Date checkOutDate) {

        final int MAX_DAYS = 7;
        Calendar calendar = Calendar.getInstance();

        for (int i = 1; i <= MAX_DAYS; i++) {
            calendar.setTime(checkInDate);
            calendar.add(Calendar.DATE, i);
            Date newCheckIn = calendar.getTime();

            calendar.setTime(checkOutDate);
            calendar.add(Calendar.DATE, i);
            Date newCheckOut = calendar.getTime();

            Collection<IRoom> rooms = findRooms(newCheckIn, newCheckOut);
            if (!rooms.isEmpty()) {
                return rooms;
            }
        }
        return new ArrayList<>();
    }

    private boolean isRoomAvailable(IRoom room, Date checkIn, Date checkOut) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room)) {
                if (checkIn.before(reservation.getCheckOutDate()) &&
                        checkOut.after(reservation.getCheckInDate())) {
                    return false;
                }
            }
        }
        return true;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> result = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                result.add(reservation);
            }
        }
        return result;
    }

    public void printAllReservation() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(System.out::println);
        }
    }
}
