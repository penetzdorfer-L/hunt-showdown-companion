package lupe.companion.hunt.chaosLoadout.tools;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.chaosLoadout.loadout.Loadout;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tools")
public class Tool {
    @Id
    private String toolID;
    private String name;
    private int bloodlineRank;
    private int price;
    private String type;
}
