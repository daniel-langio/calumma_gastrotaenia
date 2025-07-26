package swarm.calumma.brain.service.file;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RessourceFileService {

  public Set<String> readFile(String fileName) {
     try (InputStream fileStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
       if (fileStream == null) {
         throw new RuntimeException("File '" + fileName + "' not found in ressources.");
       }

       return new BufferedReader(new InputStreamReader(fileStream))
           .lines()
           .collect(Collectors.toSet());

     } catch (IOException e) {
       throw new RuntimeException(e);
     }
  }
}
