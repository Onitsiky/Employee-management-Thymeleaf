package app.employee.management.service;

import app.employee.management.model.User;
import app.employee.management.repository.entity.SessionEntity;
import app.employee.management.repository.entity.UserEntity;
import app.employee.management.repository.jpa.SessionJpaRepository;
import app.employee.management.repository.jpa.UserJpaRepository;
import app.employee.management.repository.mapper.SessionMapper;
import app.employee.management.utils.exception.ForbiddenException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SessionService {
  private final SessionJpaRepository repository;
  private final UserJpaRepository userJpaRepository;
  private static final Integer SESSION_DURATION = 1;

  public void authenticateUser(User user, String sessionId) {
    Optional<UserEntity> currentUser = userJpaRepository.findByUsername(user.getUsername());
    if(currentUser.isPresent() && checkUserInfo(user, currentUser.get())) {
      log.info("current: {}", currentUser.get());
      SessionEntity entity = SessionEntity.builder()
          .sessionId(sessionId)
          .timeout(Instant.now().plus(SESSION_DURATION, ChronoUnit.HOURS))
          .user(currentUser.get())
          .build();
      repository.save(entity);
    } else {
      throw new ForbiddenException("[Forbidden] Authentication Error. Check your credentials");
    }
  }

  public boolean checkUserInfo(User user, UserEntity entity) {
    return user.getUsername().equals(entity.getUsername()) &&
        user.getPassword().equals(entity.getPassword());
  }

  public void checkConnection(String sessionId) {
    Optional<SessionEntity> currentSession = repository.findBySessionId(sessionId);
    if(currentSession.isEmpty() || currentSession.get().getTimeout().isBefore(Instant.now())) {
      throw new ForbiddenException("[Session Expired] Please login again, your session has " +
          "expired.");
    }
  }

  public void endSession(String sessionId) {
    List<SessionEntity> endedSessions = repository.findAllBySessionId(sessionId).stream()
        .peek(session -> session.setTimeout(Instant.now().minus(5, ChronoUnit.MINUTES)))
        .toList();
    repository.saveAll(endedSessions);
  }
}
