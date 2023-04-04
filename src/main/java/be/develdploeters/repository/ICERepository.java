package be.develdploeters.repository;

import be.develdploeters.domain.ICE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ICE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ICERepository extends JpaRepository<ICE, Long> {

    List<ICE> findAllByUserId(Long id);

}
