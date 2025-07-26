package swarm.calumma.brain.model.identity;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CalummaRepo extends CrudRepository<Calumma, String> {

  @Override
  public <S extends Calumma> S save(S entity);

  public Set<Calumma> findByIsSelfIsTrue();

  public Boolean existsByFullName(String fullName);
}
