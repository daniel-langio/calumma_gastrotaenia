package swarm.calumma.brain.model.identity.host;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swarm.calumma.brain.model.identity.Host;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HostNetworkInterface {
  @Id
  private String macAddress;
  private String name;
  private String displayName;

  @ManyToOne
  @JoinColumn(name = "host_id")
  private Host host;

  public HostNetworkInterface(String macAddress, String name, String displayName) {
    this.macAddress = macAddress;
    this.name = name;
    this.displayName = displayName;
  }

  public void setHost(Host host) {
    this.host = host;
  }
}
