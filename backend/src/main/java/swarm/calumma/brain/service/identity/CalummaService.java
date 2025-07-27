package swarm.calumma.brain.service.identity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swarm.calumma.brain.model.identity.Calumma;
import swarm.calumma.brain.repository.identity.CalummaRepo;
import swarm.calumma.brain.service.file.RessourceFileService;

import java.util.Set;

@Service
@AllArgsConstructor
public class CalummaService {
  private final RessourceFileService ressourceFileService;
  private final CalummaRepo repo;

  public Calumma getIdentity() {
    return repo.findByIsSelfIsTrue().stream()
        .findFirst()
        .orElseGet(() -> {
          Calumma calumma = generateSelf();
          repo.save(calumma);
          return calumma;
        });
  }

  public Calumma generateSelf() {
    return new Calumma(generateName(), true);
  }

  public String generateName() {
    Set<String> names = ressourceFileService.readFile("names");

    String firstName = names.stream().skip((int) (Math.random() * names.size())).findFirst().orElse("Danian");
    String lastName = names.stream().skip((int) (Math.random() * names.size())).findFirst().orElse("Calumma");
    String fullName = firstName + " " + lastName;
    if (repo.existsByFullName(fullName)) {
      return generateName();
    }
    return fullName;
  }
}
