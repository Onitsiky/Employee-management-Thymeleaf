package app.employee.management.repository.mapper;

import app.employee.management.model.User;
import app.employee.management.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
  public UserEntity toEntity(User domain) {
    return UserEntity.builder()
        .id(domain.getId())
        .username(domain.getUsername())
        .password(domain.getPassword())
        .build();
  }

  public User toDomain(UserEntity entity) {
    return User.builder()
        .id(entity.getId())
        .username(entity.getUsername())
        .password(entity.getPassword())
        .build();
  }
}
