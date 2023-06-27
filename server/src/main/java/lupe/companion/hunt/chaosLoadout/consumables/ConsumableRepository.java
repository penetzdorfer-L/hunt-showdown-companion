package lupe.companion.hunt.chaosLoadout.consumables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumableRepository extends JpaRepository<Consumable, Long> {
    Consumable findConsumableByConsumableID(String parameter);
    List<Consumable> findConsumableByBloodlineRankLessThanEqual(int bloodline);
}
