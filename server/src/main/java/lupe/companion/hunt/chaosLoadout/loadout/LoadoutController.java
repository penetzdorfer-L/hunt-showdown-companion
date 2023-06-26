package lupe.companion.hunt.chaosLoadout.loadout;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/loadouts")
@AllArgsConstructor
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
        return loadoutRepository.findDistinctFirstByPrimary();
    }
    @PutMapping(path = "/generate")
    @ResponseStatus(HttpStatus.OK)
    public Loadout generateLoadout(
            @RequestBody LoadoutRequest loadoutRequest
    ) {
        Loadout generateLoadout = loadoutService.generateLoadout(loadoutRequest);
        loadoutRepository.save(generateLoadout);
        return generateLoadout;
    }
}