package lupe.hunt.companion.chaosLoadout.loadout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadoutRepository extends JpaRepository<Loadout, Long> {
}
