package com.training.microservices.birthday.repositories;

import com.training.microservices.birthday.models.BirthdayPerson;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BirthdayRepository extends MongoRepository<BirthdayPerson, Long> {
    List<BirthdayPerson> findByFirstNameAndLastName(String firstName, String LastName);
}
