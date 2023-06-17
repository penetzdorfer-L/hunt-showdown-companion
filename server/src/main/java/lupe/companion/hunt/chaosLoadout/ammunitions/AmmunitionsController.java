package lupe.companion.hunt.chaosLoadout.ammunitions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/ammunition")
public class AmmunitionsController {
    private AmmunitionsRepository ammunitionsRepository;
    public AmmunitionsController(AmmunitionsRepository ammunitionsRepository) {
        this.ammunitionsRepository = ammunitionsRepository;
    }

    @GetMapping(path="/")
    public String initAmmo() {
        System.out.println("hello from ammunition");
        return "saved";
    }
}
