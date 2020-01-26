package ru.andnovikov.sportnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andnovikov.sportnow.domain.Distance;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {

}
