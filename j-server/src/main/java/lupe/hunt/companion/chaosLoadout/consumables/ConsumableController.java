package lupe.hunt.companion.chaosLoadout.consumables;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/consumables")
@AllArgsConstructor
public class ConsumableController {
    private final ConsumableService consumableService;
    private final ConsumableRepository consumableRepository;

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<Consumable> getAllConsumables() {
        return consumableRepository.findAll();
    }
    @GetMapping(path = "/{consumableid}")
    @ResponseStatus(HttpStatus.FOUND)
    public Consumable findByID(
            @PathVariable String consumableid
    ) {
        return consumableRepository.findConsumableByConsumableID(consumableid);
    }

    @PutMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveConsumeable(
            @RequestBody Consumable consumable
    ) {
        consumableRepository.save(consumable);
        return "Saved";
    }
}
