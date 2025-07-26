package swarm.calumma.brain.controller.health;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swarm.calumma.brain.config.SecretProperties;
import swarm.calumma.brain.dto.SimpleResponseDTO;

@RestController
@AllArgsConstructor
@RequestMapping("/health/secrets")
public class SecretsHealthController {

  private final SecretProperties secretProperties;

  @GetMapping
  public ResponseEntity<SimpleResponseDTO> getHealthPhrase() {
    SimpleResponseDTO response = new SimpleResponseDTO("I can't find my health phrase");
    int statusCode = 500;

    String HEALTH_PHRASE = secretProperties.getHealth().getPhrase();

    if (HEALTH_PHRASE != null) {
      statusCode = 200;
      response = new SimpleResponseDTO(HEALTH_PHRASE);
    }

    return ResponseEntity.status(statusCode).body(response);
  }
}
