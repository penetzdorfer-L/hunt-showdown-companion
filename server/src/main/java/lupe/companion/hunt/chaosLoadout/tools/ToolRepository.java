package lupe.companion.hunt.chaosLoadout.tools;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
    Tool findToolsByNameContainingIgnoreCase(String parameter);
}
