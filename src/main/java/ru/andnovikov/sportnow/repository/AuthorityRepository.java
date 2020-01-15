package ru.andnovikov.sportnow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.andnovikov.sportnow.domain.Authority;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {
}
