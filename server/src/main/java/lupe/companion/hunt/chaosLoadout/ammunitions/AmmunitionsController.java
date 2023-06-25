package lupe.companion.hunt.chaosLoadout.ammunitions;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/ammunition")
@AllArgsConstructor
public class AmmunitionsController {
    private final AmmunitionsService ammunitionsService;
    private final AmmunitionsRepository ammunitionsRepository;
    @GetMapping(path="/")
    public List<Ammunitions> getAllAmmo() {
        return ammunitionsRepository.findAll();
    }
}
