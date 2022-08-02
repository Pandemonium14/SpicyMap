package SpicyMap.nodemodifiers.challenges;

import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;

import java.util.ArrayList;

public class MaxHPLossChestModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("MaxHPLossChest");

    private static final int HP_LOSS = 7;

    public MaxHPLossChestModifier() {
        super(ID, NodeModType.CHALLENGE);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(TreasureRoom.class);
        }};
    }

    @Override
    public void onOpenChest() {
        AbstractDungeon.player.decreaseMaxHealth(HP_LOSS);
    }
}
