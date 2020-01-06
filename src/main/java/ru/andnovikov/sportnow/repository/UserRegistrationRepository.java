package ru.andnovikov.sportnow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.andnovikov.sportnow.domain.UserRegistration;

@Repository
public interface UserRegistrationRepository extends MongoRepository<UserRegistration, String> {
}
