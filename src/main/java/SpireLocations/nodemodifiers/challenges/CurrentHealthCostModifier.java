package SpireLocations.nodemodifiers.challenges;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;

import java.util.ArrayList;

public class CurrentHealthCostModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("CurrentHealthCost");

    private static final int HP_PROPORTION = 20;

    public CurrentHealthCostModifier() {
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
        AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, (int)Math.floor(AbstractDungeon.player.currentHealth * (HP_PROPORTION / 100.0))));
    }
}
