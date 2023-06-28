package lupe.companion.hunt.chaosLoadout.loadout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/loadouts")
@AllArgsConstructor
@Slf4j
public class LoadoutController {
    private final LoadoutService loadoutService;
    private final LoadoutRepository loadoutRepository;
    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<Loadout> getAllLoadouts() {
        return loadoutRepository.findAll();
    }
    @GetMapping(path = "/filter-most-rolled")
    @ResponseStatus(HttpStatus.OK)
    public List<Loadout> filterByMostRolled() {
        return loadoutRepository.findAll();
    }
    @PostMapping(path = "/generate")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean generateLoadout(
            @RequestBody String json
    ) {
        try {
            System.out.println(json);
            ObjectMapper objectMapper = new ObjectMapper();
            LoadoutRequest loadoutRequest = objectMapper.readValue(json, LoadoutRequest.class);
            Loadout generateLoadout = loadoutService.generateLoadout(loadoutRequest);
            log.info(generateLoadout.getConsumables().toString());
            Loadout saved = loadoutRepository.save(generateLoadout);
            log.info(saved.getConsumables().toString());
            return saved.getConsumables().equals(generateLoadout.getConsumables());
           // return saved;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping(path = "/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public String deleteLoadouts() {
        loadoutRepository.deleteAll();
        return "Deleted all Loadouts!";
    }
}
