package app.employee.management.repository.jpa;

import app.employee.management.repository.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
  @Query(value = "select * from \"user\" u where u.username = :username limit 1", nativeQuery = true)
  Optional<UserEntity> findByUsername(String username);
}
