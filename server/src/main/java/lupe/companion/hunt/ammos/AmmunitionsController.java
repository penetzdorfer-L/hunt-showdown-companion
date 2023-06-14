package lupe.companion.hunt.ammos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/api/v1/ammunition")
public class AmmunitionsController {
    private AmmunitionsRepository ammunitionsRepository;
    public AmmunitionsController(AmmunitionsRepository ammunitionsRepository) {
        this.ammunitionsRepository = ammunitionsRepository;
    }

    @GetMapping(path="/")
    public String initAmmo() {
        return "saved";
    }
}
