package info.battlecats.cats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.json.JsonObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import java.util.List;

@Document(collection = "cats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    //ID, Number, Image,Name,Form,Rarity,Level,Health,Damage,Range,KB,Speed,DPS,Target,Time Between Attacks,
    // Atk. Animation,Recharging Time,Cost,Enemy Types,Ability

    @Id
    private ObjectId id;
    private String catID;
    private Integer number;
    private String image;
    private String name;
    private String form;
    private String rarity;
    private Integer level;
    private Integer health;
    private Integer damage;
    private Integer range;
    private Integer kb;
    private Integer speed;
    private Integer dps;

    private String target;
    private Double timeBetweenAttacks;
    private Double attackAnimationTime;
    private Double rechargingTime;
    private Integer cost;
    private List<String> enemyTypes;
    private List<String> ability;
    private List<Review> reviewIds;

}
