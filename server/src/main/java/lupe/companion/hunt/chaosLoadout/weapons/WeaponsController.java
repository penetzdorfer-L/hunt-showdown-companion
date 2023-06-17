package lupe.companion.hunt.chaosLoadout.weapons;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/weapons")
@AllArgsConstructor
public class WeaponsController {
    private final WeaponsService weaponsService;
    private final WeaponsRepository weaponsRepository;

}
