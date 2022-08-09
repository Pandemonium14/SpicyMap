package SpicyMap.nodemodifiers.challenges;

import SpicyMap.NodeModifierHelper;
import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import SpicyMap.patches.NodeModifierField;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

public class CursedChestOnEventModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("CursedChestEvent");

    public CursedChestOnEventModifier() {
        super(ID, NodeModType.CHALLENGE);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(EventRoom.class);
        return result;
    }

    @Override
    public EventHelper.RoomResult forceEventResult() {
        return EventHelper.RoomResult.TREASURE;
    }

    @Override
    public void onOpenChest() {
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(CardLibrary.getCurse(), Settings.WIDTH/2f, Settings.HEIGHT/2f));
    }

    @Override
    public void onGeneration(AbstractRoom room) {
        ArrayList<AbstractNodeModifier> mods = NodeModifierField.modifiers.get(room);
        ArrayList<AbstractNodeModifier> toRemove = new ArrayList<>();
        for (AbstractNodeModifier mod : mods) {
            if (mod.type == NodeModType.REWARD) {
                toRemove.add(mod);
            }
        }
        for (AbstractNodeModifier mod : toRemove) {
            mods.remove(mod);
        }
        mods.add(NodeModifierHelper.getModifier(TreasureRoom.class, NodeModType.REWARD));
    }
}
