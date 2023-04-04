package be.develdploeters.repository;

import be.develdploeters.domain.BannerImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the BannerImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BannerImageRepository extends JpaRepository<BannerImage, Long> {

    List<BannerImage> findByActiveTrue();

}
