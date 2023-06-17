package lupe.companion.hunt.chaosLoadout.loadout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadoutsRepository extends JpaRepository<Loadouts, Long> {
}
