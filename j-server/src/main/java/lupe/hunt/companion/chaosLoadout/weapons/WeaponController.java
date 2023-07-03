package lupe.hunt.companion.chaosLoadout.weapons;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/weapons")
@AllArgsConstructor
public class WeaponController {
    private final WeaponService weaponService;
    private final WeaponRepository weaponRepository;
    @GetMapping(path = "/")
    public List<Weapon> getAllWeapons() {
        return weaponRepository.findAll();
    }

    @GetMapping(path = "/search/{weapon}")
    @ResponseStatus(HttpStatus.FOUND)
    public Weapon getByID(
            @PathVariable String weapon
    ) {
        return weaponRepository.findWeaponsByWeaponID(weapon);
    }
    @GetMapping(path = "/filter/{slots}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Weapon> getBySlots(
            @PathVariable int slots
    ) {
        return weaponRepository.findWeaponsBySlotsEquals(slots);
    }
    @PutMapping(path = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveWeapon(
            @RequestBody Weapon weapon
    ) {
        weaponRepository.save(weapon);
        return "Saved";
    }
}
