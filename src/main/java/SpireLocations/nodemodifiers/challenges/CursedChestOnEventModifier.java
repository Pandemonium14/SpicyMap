package SpireLocations.nodemodifiers.challenges;

import SpireLocations.NodeModifierHelper;
import SpireLocations.SpireLocationsMod;
import SpireLocations.actionsandeffects.AddModifierEffect;
import SpireLocations.actionsandeffects.RemoveModifierEffect;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.NodeModifierField;
import basemod.BaseMod;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;

public class CursedChestOnEventModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("CursedChestEvent");

    public CursedChestOnEventModifier() {
        super(ID, NodeModType.CHALLENGE, iconPath("CursedChest"));
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
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(AbstractDungeon.returnRandomCurse(), Settings.WIDTH/2f, Settings.HEIGHT/2f));
    }

    @Override
    public void onGeneration(AbstractRoom room, int floor) {
        //BaseMod.logger.log(Level.INFO, "calling onGeneration() on Cursed Chest Modifier");
        ArrayList<AbstractNodeModifier> mods = NodeModifierField.modifiers.get(room);
        ArrayList<AbstractNodeModifier> toRemove = new ArrayList<>();
        for (AbstractNodeModifier mod : mods) {
            if (mod.type == NodeModType.REWARD) {
                AbstractDungeon.effectsQueue.add(new RemoveModifierEffect(mod, room));
            }
        }
        AbstractDungeon.effectsQueue.add(new AddModifierEffect(NodeModifierHelper.getModifier(TreasureRoom.class, NodeModType.REWARD, AbstractDungeon.actNum), room, floor));
    }
}
