package lupe.companion.hunt.chaosLoadout.loadout;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/loadouts")
@AllArgsConstructor
public class LoadoutsController {
    private final LoadoutsService loadoutsService;
    private final LoadoutsRepository loadoutsRepository;

}
