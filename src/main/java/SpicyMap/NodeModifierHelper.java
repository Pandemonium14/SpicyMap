package SpicyMap;

import SpicyMap.nodemodifiers.AbstractNodeModifier;
import SpicyMap.patches.NodeModifierField;
import basemod.BaseMod;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.shop.ShopScreen;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;

public class NodeModifierHelper {

    public static ArrayList<AbstractNodeModifier> nodeModifiers = new ArrayList<>();



    public static void addModifier(MapRoomNode node, int floor) {

        AbstractRoom room = node.getRoom();
        if (shouldAddModifier(room.getClass())) {
            AbstractNodeModifier.NodeModType type = rollForType(floor);
            if (type == null) return;
            AbstractNodeModifier mod = getModifier(room.getClass(), type);
            if (mod != null) {
                NodeModifierField.modifiers.get(room).add(mod);
                if (mod.type == AbstractNodeModifier.NodeModType.CHALLENGE) {

                    AbstractNodeModifier rewardMod = getModifier(room.getClass(), AbstractNodeModifier.NodeModType.REWARD);
                    if (rewardMod != null) {
                        NodeModifierField.modifiers.get(room).add(rewardMod);
                        BaseMod.logger.log(Level.INFO, "Adding reward to challenge node : " + rewardMod.MODIFIER_ID);
                    }
                }
            }
        }

    }



    private static boolean shouldAddModifier(Class<? extends AbstractRoom> roomClass) {
        int r = AbstractDungeon.mapRng.random(99);
        if (roomClass.equals(RestRoom.class)) {
            return r < 100;
        } else if (roomClass.equals(MonsterRoomElite.class)) {
            return r < 100;
        } else if (roomClass.equals(MonsterRoom.class)) {
            return r < 100;
        } else if (roomClass.equals(ShopRoom.class)) {
            return r < 100;
        } else if (roomClass.equals(EventRoom.class)) {
            return r < 100;
        } else if (roomClass.equals(TreasureRoom.class)) {
            return r < 100;
        } else {
            return false;
        }
    }

    private static AbstractNodeModifier.NodeModType rollForType(int floor) {
        int r = AbstractDungeon.mapRng.random(99);
        if (r < 50) {
            return AbstractNodeModifier.NodeModType.SPECIAL;
        } else if (r < 80) {
            if (floor > 2) return AbstractNodeModifier.NodeModType.CHALLENGE;
            else return null;
        } else {
            return AbstractNodeModifier.NodeModType.BONUS;
        }
    }


    private static AbstractNodeModifier getModifier(Class<? extends AbstractRoom> roomClass, AbstractNodeModifier.NodeModType type) {
        ArrayList<AbstractNodeModifier> list = new ArrayList<>();
        for (AbstractNodeModifier mod : nodeModifiers) {
            if (mod.type == type && mod.roomClasses.contains(roomClass)) {
                list.add(mod);
            }
        }
        if (list.size() > 0) {
            int index = AbstractDungeon.mapRng.random(list.size() - 1);
            return list.get(index).makeCopy();
        } else {
            BaseMod.logger.log(Level.INFO,"No suitable " + type.name() + " modifier found for " + roomClass.getName());
            return null;
        }
    }
}
