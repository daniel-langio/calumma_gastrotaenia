package swarm.calumma.brain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "secret")
@Getter
public class SecretProperties {

  private final Health health = new Health();

  @Getter
  @Setter
  public static class Health {
    private String phrase;
  }
}
