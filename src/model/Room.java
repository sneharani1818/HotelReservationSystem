package model;

public class Room implements IRoom{
    final private String roomNumber;
    final private Double price;
    final private RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return price == 0.0;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber +
                "\n Room Type: " + roomType +
                "\n Price: $" + price;
    }
}
