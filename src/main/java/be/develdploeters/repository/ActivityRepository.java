package be.develdploeters.repository;

import be.develdploeters.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Activity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query(value = "SELECT a FROM Activity a WHERE Year(a.date) = :year ORDER BY a.date ASC")
    List<Activity> findActivitiesByYear(@Param("year") int year);

    @Query(value = "SELECT a FROM Activity a left join a.report ORDER BY a.date DESC ")
    List<Activity> findActivitiesFetchedReport();

}
