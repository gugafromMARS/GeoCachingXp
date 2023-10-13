package gsc.projects.cachesmcs.repository;

import gsc.projects.cachesmcs.model.Cache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheRepository extends JpaRepository<Cache, Long> {

    Cache findByCacheCode(String cacheCode);
}
