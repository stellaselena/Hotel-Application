package com.example.stellajovanovic.hotelapplication.CheckIn;


/**
 * Created by Stella on 06.05.2017.
 */

public class Customer {
    private int id;
    private String name;
    private String surname;
    private String roomNumber;
    private String checkInDate;


    public Customer(){}

    public Customer(String name, String surname, String roomNumber, String checkIn) {
        super();
        this.name = name;
        this.surname = surname;
        this.roomNumber = roomNumber;
        this.checkInDate = checkIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", checkInDate=" + checkInDate +
                '}';
    }
}
