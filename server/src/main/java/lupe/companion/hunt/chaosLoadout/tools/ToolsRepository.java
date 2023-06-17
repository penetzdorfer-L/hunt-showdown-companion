package lupe.companion.hunt.chaosLoadout.tools;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolsRepository extends JpaRepository<Tools, Long> {
    Tools findToolsByNameContainingIgnoreCase(String parameter);
}
