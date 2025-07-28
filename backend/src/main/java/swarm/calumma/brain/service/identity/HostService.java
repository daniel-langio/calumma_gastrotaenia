package swarm.calumma.brain.service.identity;

import org.springframework.stereotype.Service;
import swarm.calumma.brain.model.identity.host.HostNetworkInterface;
import swarm.calumma.brain.service.identity.exception.InterfaceNotFoundException;
import swarm.calumma.brain.service.identity.exception.MacAddressNotFound;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@Service
public class HostService {
  public String getHostName() throws UnknownHostException {
    return InetAddress.getLocalHost().getHostName();
  }

  public Set<HostNetworkInterface> getHostInterfaces() throws SocketException {
    HashSet<HostNetworkInterface> hostInterfaces = new HashSet<HostNetworkInterface>();
    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

    while (interfaces.hasMoreElements()) {
      NetworkInterface netInterface = interfaces.nextElement();

      String name = netInterface.getName();
      String displayName = netInterface.getDisplayName();
      String macAddr = getHostMacAddress(name);
      hostInterfaces.add(
          new HostNetworkInterface(macAddr, name, displayName)
      );
    }

    return hostInterfaces;
  }

  public String getHostMacAddress(String targetInterface) throws SocketException {
    if (targetInterface == null) {
      targetInterface = "wlan0";
    }

    NetworkInterface net = NetworkInterface.getByName(targetInterface);

    if (net == null) {
      throw new InterfaceNotFoundException("Interface '" + targetInterface + "' not found.");
    }

    byte[] mac = net.getHardwareAddress();
    if (mac == null) {
      //throw new MacAddressNotFound("MAC Address of '" + targetInterface + "' not found.");
      return null;
    }

    StringBuilder macStr = new StringBuilder();
    for (int i = 0; i < mac.length; i++) {
      macStr.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
    }

    return macStr.toString();
  }
}
