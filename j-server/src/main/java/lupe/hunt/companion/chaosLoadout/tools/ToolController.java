package lupe.hunt.companion.chaosLoadout.tools;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/tools")
@AllArgsConstructor
public class ToolController {
    private final ToolService toolService;
    private final ToolRepository toolRepository;
    @GetMapping(path="/")
    public List<Tool> getAll() {
        return toolRepository.findAll();
    }
    @GetMapping(path = "/{toolID}")
    public Tool findByID(
            @PathVariable String toolID
    ) {

        return toolRepository.findToolsByNameContainingIgnoreCase(toolID);
    }
    @PutMapping(path = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveTool(
            @RequestBody Tool tool
    ) {
        toolRepository.save(tool);
        return "Saved";
    }
}
