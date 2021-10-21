package com.training.microservices.birthday.models;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

// use @CompoundIndex(def="{'firstName':1, 'birthday': -1}") to index with multiple fields (1 = ascending, -1 = descending)
// in case you omit the collection specification, the collection name will be taken from the class name
@Document("birthdayPersons")
public class BirthdayPerson {

    @Field(name="FIRST_NAME")
    private String firstName;

    // use @Transient if you want to prevent a property from being persisted, will have a default value when deserialized
    // @DBRef links value below to existing document e.g. address document in person document
    @Field(name="LAST_NAME")
    private String lastName;

    @Indexed(direction= IndexDirection.ASCENDING)
    private String birthday;

    public String getBirthday() {
        return birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "BirthdayPerson{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
