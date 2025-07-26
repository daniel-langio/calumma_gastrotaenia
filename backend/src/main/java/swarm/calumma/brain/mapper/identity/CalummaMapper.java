package swarm.calumma.brain.mapper.identity;

import org.springframework.stereotype.Component;
import swarm.calumma.brain.dto.identity.CalummaDTO;
import swarm.calumma.brain.model.identity.Calumma;

import java.time.format.DateTimeFormatter;

@Component
public class CalummaMapper {
  public CalummaDTO toDTO(Calumma e) {
    return new CalummaDTO(e.getFullName(), e.getBirthDate().format(DateTimeFormatter.ISO_DATE_TIME));
  }
}
