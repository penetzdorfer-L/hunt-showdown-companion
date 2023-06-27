package lupe.companion.hunt.chaosLoadout.loadout;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadoutRequest {
    private int bloodlineRank;
    private boolean dualWield;
    private boolean quarterMaster;
    private boolean forceFirstAidKit;
    private boolean forceMelee;
    private boolean specialAmmo;
}
