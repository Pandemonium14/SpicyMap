package SpireLocations.patches.nodemodifierhooks;

import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.NodeModifierField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;

@SpirePatch2(clz = MapRoomNode.class, method = "isConnectedTo")
public class AccessibleFromPatch {
    @SpirePostfixPatch
    public static boolean checkModifiers(MapRoomNode __instance, MapRoomNode node, boolean __result) {
        AbstractRoom room = node.room;
        ArrayList<AbstractNodeModifier> mods = NodeModifierField.modifiers.get(room);
        boolean accessible = false;
        for (AbstractNodeModifier mod : mods) {
            accessible = accessible || mod.modifyAccessToThis(__instance.y, __instance, node);
        }
        AbstractRoom currentRoom = __instance.room;
        ArrayList<AbstractNodeModifier> currentMods = NodeModifierField.modifiers.get(currentRoom);
        for (AbstractNodeModifier mod : currentMods) {
            accessible = accessible || mod.modifyAccessFromThis(__instance.y, __instance, node);
        }
        return (__result || accessible);
    }

    public static int getFloor(MapRoomNode node) {
        int nodeFloor = -1;
        if (node != null) {
            for (ArrayList<MapRoomNode> floor : AbstractDungeon.map) {
                if (floor.contains(node)) {
                    nodeFloor = AbstractDungeon.map.indexOf(floor);
                    break;
                }
            }
        }
        return nodeFloor;
    }
}
