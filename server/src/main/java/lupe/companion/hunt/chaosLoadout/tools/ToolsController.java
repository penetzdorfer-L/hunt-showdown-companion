package lupe.companion.hunt.chaosLoadout.tools;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/tools")
@AllArgsConstructor
public class ToolsController {
    private final ToolsService toolsService;
    private final ToolsRepository toolsRepository;
    @GetMapping(path="/")
    public List<Tools> getAll() {
        return toolsRepository.findAll();
    }
    @GetMapping(path = "/{name}")
    public Tools getBySearch(
            @PathVariable String name
    ) {
        System.out.println(name);
        return toolsRepository.findToolsByNameContainingIgnoreCase(name);
    }
}
