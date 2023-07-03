package lupe.companion.hunt.chaosLoadout.ammunitions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/ammunition")
@AllArgsConstructor
public class AmmunitionController {
    private final AmmunitionService ammunitionService;
    private final AmmunitionRepository ammunitionRepository;
    @GetMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    public List<Ammunition> getAllAmmo() {
        return ammunitionRepository.findAll();
    }

    @GetMapping(path="/{ammoid}")
    @ResponseStatus(HttpStatus.FOUND)
    public Ammunition findByID(
            @PathVariable String ammoid
    ) {
        return ammunitionRepository.findAmmunitionsByAmmoID(ammoid);
    }

    @PutMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveAmmo(
            @RequestBody Ammunition ammo
    ) {
        ammunitionRepository.save(ammo);
        return "Saved";
    }
}
