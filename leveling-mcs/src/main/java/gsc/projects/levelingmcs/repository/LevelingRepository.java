package gsc.projects.levelingmcs.repository;

import gsc.projects.levelingmcs.model.Leveling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LevelingRepository extends JpaRepository<Leveling, Long> {
}
