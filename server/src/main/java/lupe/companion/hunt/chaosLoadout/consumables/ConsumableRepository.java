package lupe.companion.hunt.chaosLoadout.consumables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumableRepository extends JpaRepository<Consumable, Long> {
    Consumable findConsumableByConsumableID(String parameter);
}
