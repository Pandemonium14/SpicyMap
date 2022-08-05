package SpicyMap.patches.nodemodifierhooks;


import SpicyMap.NodeModifierHelper;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import SpicyMap.patches.NodeModifierField;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;

import java.util.ArrayList;

@SpirePatch2(clz = AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {SaveFile.class})
public class ModifyEventHook {

    @SpireInsertPatch(rloc = 112, localvars = {"roomResult"})
    public static void forceEvent(@ByRef(type = "helpers.EventHelper.RoomResult") EventHelper.RoomResult[] roomResult) {
        AbstractRoom room = AbstractDungeon.nextRoom.room;
        ArrayList<AbstractNodeModifier> mods = NodeModifierField.modifiers.get(room);
        EventHelper.RoomResult forceEvent = null;

        for (AbstractNodeModifier mod : mods) {
            if (mod.forceEvent() != null) {
                forceEvent = mod.forceEvent();
                break;
            }
        }

        if (forceEvent != null) {
            roomResult[0] = forceEvent;
        }
    }

    @SpireInsertPatch(rloc = 131)
    public static void modifyEvent() {
        AbstractRoom room = AbstractDungeon.nextRoom.getRoom();
        if (room.event != null) {
            ArrayList<AbstractNodeModifier> mods = NodeModifierField.modifiers.get(room);
            for (AbstractNodeModifier mod : mods) {
                mod.modifyEvent(room.event);
            }
        }
    }
}
