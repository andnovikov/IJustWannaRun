package ru.andnovikov.ijustwannarun.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.andnovikov.ijustwannarun.domain.Authority;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {
}
