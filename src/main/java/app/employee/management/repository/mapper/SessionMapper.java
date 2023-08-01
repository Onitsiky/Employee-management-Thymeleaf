package app.employee.management.repository.mapper;

import app.employee.management.model.Session;
import app.employee.management.repository.entity.SessionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SessionMapper {
  private final UserMapper userMapper;

  public Session toDomain(SessionEntity entity) {
    return Session.builder()
        .id(entity.getId())
        .sessionId(entity.getSessionId())
        .timeout(entity.getTimeout())
        .user(userMapper.toDomain(entity.getUser()))
        .build();
  }

  public SessionEntity toEntity(Session domain) {
    return SessionEntity.builder()
        .id(domain.getId())
        .sessionId(domain.getSessionId())
        .user(userMapper.toEntity(domain.getUser()))
        .timeout(domain.getTimeout())
        .build();
  }
}
