package lupe.companion.hunt.chaosLoadout.consumables;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/consumables")
@AllArgsConstructor
public class ConsumablesController {
    private final ConsumablesService consumablesService;
    private final ConsumablesRepository consumablesRepository;
}
