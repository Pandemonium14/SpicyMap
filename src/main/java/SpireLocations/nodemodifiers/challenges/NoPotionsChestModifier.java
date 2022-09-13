package SpireLocations.nodemodifiers.challenges;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;

import java.util.ArrayList;

public class NoPotionsChestModifier extends AbstractNodeModifier {
    public static final String ID = SpireLocationsMod.makeID("NoPotionsChest");

    public NoPotionsChestModifier() {
        super(ID, NodeModType.CHALLENGE);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(TreasureRoom.class);
        }};
    }


    @Override
    public boolean onTryOpenChest() {
        boolean openChest = true;
        for (AbstractPotion p : AbstractDungeon.player.potions) {
            if (!(p instanceof PotionSlot)) {
                openChest = false;
                p.flash();
            }
        }
        return openChest;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
