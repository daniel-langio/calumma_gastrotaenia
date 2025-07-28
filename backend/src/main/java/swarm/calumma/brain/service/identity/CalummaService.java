package swarm.calumma.brain.service.identity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swarm.calumma.brain.model.identity.Calumma;
import swarm.calumma.brain.model.identity.Host;
import swarm.calumma.brain.repository.identity.CalummaRepo;
import swarm.calumma.brain.repository.identity.HostRepo;
import swarm.calumma.brain.service.file.RessourceFileService;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CalummaService {
  private final RessourceFileService ressourceFileService;
  private final CalummaRepo repo;
  private final HostRepo hostRepo;
  private final HostService hostService;

  public Host getHost() {
    try {
      return hostRepo.findByIsSelfIsTrue()
          .stream()
          .findFirst()
          .orElseGet(() -> {
        try {
          Host host = identifyHost();
          hostRepo.save(host);
          return host;
        } catch (SocketException | UnknownHostException e) {
          throw new RuntimeException(e);
        }
      });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Host identifyHost() throws SocketException, UnknownHostException {
    return new Host(
        hostService.getHostName(),
        hostService.getHostInterfaces(),
        true
    );
  }

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
