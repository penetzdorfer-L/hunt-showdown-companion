package lupe.companion.hunt.ammos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmmunitionsRepository extends JpaRepository<Ammunitions, Long> {
}
