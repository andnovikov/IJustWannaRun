package ru.andnovikov.sportnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.andnovikov.sportnow.domain.Authority;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
