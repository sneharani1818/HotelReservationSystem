package model;

import java.util.Date;

public class Reservation {
    final private Customer customer;
    final private IRoom room;
    final private Date checkInDate;
    final private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "Customer=" + customer +
                ", Room=" + room +
                ", Check-in Date=" + checkInDate +
                ", Check-out Date=" + checkOutDate +
                '}';
    }
}
