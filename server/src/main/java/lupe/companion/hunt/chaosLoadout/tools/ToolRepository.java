package lupe.companion.hunt.chaosLoadout.tools;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
    Tool findToolsByNameContainingIgnoreCase(String parameter);
    List<Tool> findToolsByBloodlineRankIsLessThanEqual(int bloodline);
    List<Tool> findToolsByTypeIsIgnoreCase(String parameter);
    Tool findToolsByToolID(String tool);
}
