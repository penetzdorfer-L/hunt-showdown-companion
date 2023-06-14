package lupe.companion.hunt.weapons_ammo_connection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="api/v1/tableConnection")
public class WeaponsAmmoController {

    private WeaponAmmunitionRepository weaponAmmunitionRepository;

    public WeaponsAmmoController(WeaponAmmunitionRepository weaponAmmunitionRepository) {
        this.weaponAmmunitionRepository = weaponAmmunitionRepository;
    }



    @GetMapping("/")
    public String saveWeapon() {
        WeaponsAmmunitionConnection w1 = new WeaponsAmmunitionConnection(1, 111, "Winfield", 3, 1, 1, "Compact");
        weaponAmmunitionRepository.save(w1);
        return "saved";
    }
}
