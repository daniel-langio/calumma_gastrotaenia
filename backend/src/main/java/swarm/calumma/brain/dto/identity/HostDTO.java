package swarm.calumma.brain.dto.identity;

import swarm.calumma.brain.dto.identity.host.HostNetworkInterfaceDTO;

import java.util.Set;

public record HostDTO(String name, Set<HostNetworkInterfaceDTO> networkInterfaces) {}
