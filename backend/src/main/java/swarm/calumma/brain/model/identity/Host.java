package swarm.calumma.brain.model.identity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swarm.calumma.brain.model.identity.host.HostNetworkInterface;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Host {
    @Id
    @GeneratedValue(generator = "uuid4")
    private String id;
    private String name;
    
    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HostNetworkInterface> networkInterfaces;
    
    private boolean isSelf;
    
    @Version
    private Long version = 0L;

    public Host(String name, boolean isSelf) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.networkInterfaces = new HashSet<>();
        this.isSelf = isSelf;
    }

    public Host(String name, Set<HostNetworkInterface> networkInterfaces, boolean isSelf) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.networkInterfaces = networkInterfaces;
        this.isSelf = isSelf;
    }

    public void addNetworkInterface(HostNetworkInterface networkInterface) {
        this.networkInterfaces.add(networkInterface);
        networkInterface.setHost(this);
    }
}