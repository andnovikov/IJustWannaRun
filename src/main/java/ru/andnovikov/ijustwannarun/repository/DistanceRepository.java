package ru.andnovikov.ijustwannarun.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.andnovikov.ijustwannarun.domain.Distance;

@Repository
public interface DistanceRepository extends MongoRepository<Distance, String> {

}
