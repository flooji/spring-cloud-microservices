package com.training.microservices.notification.models;

public class BirthdayEvent {
    String firstName;
    String lastName;
    String birthday;

    @Override
    public String toString() {
        return "BirthdayEvent{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
