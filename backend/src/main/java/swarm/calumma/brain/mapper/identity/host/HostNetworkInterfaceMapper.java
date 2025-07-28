package swarm.calumma.brain.mapper.identity.host;

import org.springframework.stereotype.Component;
import swarm.calumma.brain.dto.identity.host.HostNetworkInterfaceDTO;
import swarm.calumma.brain.model.identity.host.HostNetworkInterface;

@Component
public class HostNetworkInterfaceMapper {
  public HostNetworkInterfaceDTO toDTO(HostNetworkInterface e) {
    return new HostNetworkInterfaceDTO(e.getName(), e.getDisplayName(), e.getMacAddress());
  }
}
