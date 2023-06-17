package lupe.companion.hunt.chaosLoadout.tools;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.tools.Tool;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/tools")
public class ToolsController {
    private ToolsRepository toolsRepository;
    private ToolsService toolsService;
    public ToolsController(ToolsRepository toolsRepository, ToolsService toolsService) {
        this.toolsRepository = toolsRepository;
        this.toolsService = toolsService;
    }
    @GetMapping(path="/")
    public List<Tools> getAll() {
        return toolsRepository.findAll();
    }
    @GetMapping(path = "/{name}")
    public Tools getBySearch(
            @PathVariable String name
    ) {
        return toolsRepository.findToolsByNameContainingIgnoreCase(name);
    }
}
