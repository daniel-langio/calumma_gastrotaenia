package swarm.calumma.brain.repository.identity;

import org.springframework.data.repository.CrudRepository;
import swarm.calumma.brain.model.identity.Host;

import java.util.Set;

public interface HostRepo extends CrudRepository<Host, String> {
  @Override
  public <S extends Host> S save(S entity);

  public Set<Host> findByIsSelfIsTrue();
}
