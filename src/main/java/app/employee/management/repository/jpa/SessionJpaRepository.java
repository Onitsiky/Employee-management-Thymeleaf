package app.employee.management.repository.jpa;

import app.employee.management.repository.entity.SessionEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionJpaRepository extends JpaRepository<SessionEntity, String> {
  @Query(value = "select * from session s where s.session_id = :sessionId order by s.timeout desc limit 1", nativeQuery = true)
  Optional<SessionEntity> findBySessionId(String sessionId);
  List<SessionEntity> findAllBySessionId(String sessionId);
}
