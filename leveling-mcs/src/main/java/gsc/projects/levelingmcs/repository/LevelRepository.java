package gsc.projects.levelingmcs.repository;

import gsc.projects.levelingmcs.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

    Level findByUserEmail(String userEmail);
}
