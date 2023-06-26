package lupe.companion.hunt.chaosLoadout.tools;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;

    public Set<Tool> getRandomTools(int bloodlineRank, boolean forceMelee, boolean forceFirstAidKit) {
        return null;
    }
}
