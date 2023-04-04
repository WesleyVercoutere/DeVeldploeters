package be.develdploeters.repository;

import be.develdploeters.domain.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the Presence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {

    Optional<Presence> findByActivityIdAndUserId(Long activityId, Long userId);

    @Query(value = "SELECT p FROM Presence p left join p.activity left join p.user WHERE p.id = :id")
    Presence findByIdFetched(@Param("id") Long id);

    @Query(value = "SELECT p FROM Presence p left join p.activity left join p.user WHERE p.activity.id = :id")
    List<Presence> findByActivityIdFetched(@Param("id") Long id);

    @Query(value = "SELECT p FROM Presence p left join p.activity left join p.user WHERE Year(p.activity.date) = :year ORDER BY p.activity.date ASC")
    List<Presence> findByYearFetched(@Param("year") int year);

}
