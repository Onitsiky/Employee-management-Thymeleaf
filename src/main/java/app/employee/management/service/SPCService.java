package app.employee.management.service;

import app.employee.management.model.SPC;
import app.employee.management.repository.entity.SPCEntity;
import app.employee.management.repository.jpa.SPCJpaRepository;
import app.employee.management.repository.mapper.SPCMapper;
import app.employee.management.utils.exception.BadRequestException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SPCService {
  private final SPCJpaRepository repository;
  private final SPCMapper mapper;

  public List<SPC> getAll() {
    return mapper.toDomains(repository.findAll());
  }

  public SPC getById(String id) {
    Optional<SPCEntity> actual = repository.findById(id);
    if(actual.isPresent()) {
      return mapper.toDomain(actual.get());
    } else {
      throw new BadRequestException("The specified SPC doesn't exist");
    }
  }
}
