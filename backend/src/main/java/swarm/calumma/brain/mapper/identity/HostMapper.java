package swarm.calumma.brain.mapper.identity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import swarm.calumma.brain.dto.identity.HostDTO;
import swarm.calumma.brain.mapper.identity.host.HostNetworkInterfaceMapper;
import swarm.calumma.brain.model.identity.Host;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class HostMapper {
  private HostNetworkInterfaceMapper hostNetworkInterfaceMapper;

  public HostDTO toDTO(Host e) {
    return new HostDTO(e.getName(), e.getNetworkInterfaces().stream()
        .map(hostNetworkInterfaceMapper::toDTO)
        .collect(Collectors.toSet())
    );
  }
}
