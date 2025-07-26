package swarm.calumma.brain.model.identity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Calumma {

  @Id
  @GeneratedValue(generator = "uuid4")
  private String id;
  private String fullName;
  private LocalDateTime birthDate;
  private boolean isSelf;

  public Calumma(String fullName, boolean isSelf) {
    this.fullName = fullName;
    this.isSelf = isSelf;
    this.birthDate = LocalDateTime.now();
  }
}
