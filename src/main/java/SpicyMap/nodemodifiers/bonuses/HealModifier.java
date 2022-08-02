package SpicyMap.nodemodifiers.bonuses;

import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.*;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.floor;

public class HealModifier extends AbstractNodeModifier {
    public static final String ID = SpicyMapMod.makeID("Heal");
    public static final int HEAL_PERCENTAGE = 15;

    public HealModifier() {
        super(ID, NodeModType.BONUS);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(ShopRoom.class);
        result.add(TreasureRoom.class);
        result.add(RestRoom.class);
        return result;
    }

    @Override
    public void postEnterRoom(AbstractRoom room) {
        AbstractDungeon.player.heal(floor(AbstractDungeon.player.maxHealth * (HEAL_PERCENTAGE/100f)));
    }
}
