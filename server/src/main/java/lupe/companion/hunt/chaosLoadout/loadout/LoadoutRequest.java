package lupe.companion.hunt.chaosLoadout.loadout;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoadoutRequest {
    private final int bloodlineRank;
    private final boolean dualWield;
    private final boolean quarterMaster;
    private final boolean forceFirstAidKit;
    private final boolean forceMelee;
    private final boolean specialAmmo;
}
