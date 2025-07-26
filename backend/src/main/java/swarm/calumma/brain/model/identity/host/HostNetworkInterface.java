package swarm.calumma.brain.model.identity.host;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HostNetworkInterface {
  @Id
  private String macAddress;
  private String name;
  private String displayName;
}
