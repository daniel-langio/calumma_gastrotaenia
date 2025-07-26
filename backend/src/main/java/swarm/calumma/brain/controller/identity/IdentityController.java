package swarm.calumma.brain.controller.identity;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swarm.calumma.brain.dto.SimpleResponseDTO;
import swarm.calumma.brain.mapper.identity.CalummaMapper;
import swarm.calumma.brain.service.identity.CalummaService;

@RestController
@RequestMapping("/identity")
@AllArgsConstructor
public class IdentityController {

  private CalummaService calummaService;
  private CalummaMapper calummaMapper;

  @GetMapping
  public ResponseEntity<?> getIdentity() {
    try {
      return ResponseEntity.ok(calummaMapper.toDTO(calummaService.getIdentity()));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(new SimpleResponseDTO("Something went wrong: " + e.getMessage()));
    }
  }
}
