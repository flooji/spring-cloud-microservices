package com.training.microservices.birthday.repositories;

import com.training.microservices.birthday.models.BirthdayPerson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BirthdayRepository extends CrudRepository<BirthdayPerson, Long> {
    List<BirthdayPerson> findByFirstNameAndLastName(String firstName, String LastName);
}
