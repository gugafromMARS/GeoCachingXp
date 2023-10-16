package gsc.projects.registermcs.repository;

import gsc.projects.registermcs.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {

    List<Register> findAllByUserEmail(String userEmail);


    Register findRegisterByUserEmailAndCacheCode(String userEmail, String cacheCode);
}
