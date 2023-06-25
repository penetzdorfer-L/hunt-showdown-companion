package lupe.companion.hunt.chaosLoadout.weapons;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/weapons")
@AllArgsConstructor
public class WeaponsController {
    private final WeaponsService weaponsService;
    private final WeaponsRepository weaponsRepository;

    @GetMapping(path = "/")
    public List<Weapons> getAllWeapons() {
        return weaponsRepository.findAll();
    }

    @GetMapping(path = "/{name}")
    public List<Weapons> getWeaponsByName(
            @PathVariable String name
    ) {
        return weaponsRepository.findWeaponsByNameIsContainingIgnoreCase(name);
    }
}
