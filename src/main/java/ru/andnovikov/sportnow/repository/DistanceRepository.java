package ru.andnovikov.sportnow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.andnovikov.sportnow.domain.Distance;

@Repository
public interface DistanceRepository extends MongoRepository<Distance, String> {

}
