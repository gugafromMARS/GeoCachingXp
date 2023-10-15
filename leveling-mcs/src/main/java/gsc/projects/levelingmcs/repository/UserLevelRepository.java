package gsc.projects.levelingmcs.repository;

import gsc.projects.levelingmcs.model.ULevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserLevelRepository extends JpaRepository<ULevel, Long> {

    ULevel findByUserEmail(String userEmail);
}
