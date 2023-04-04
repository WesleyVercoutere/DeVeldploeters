package be.develdploeters.repository;

import be.develdploeters.domain.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sponsor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

}
