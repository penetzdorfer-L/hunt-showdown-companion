package lupe.companion.hunt.chaosLoadout.weapons;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;
}
