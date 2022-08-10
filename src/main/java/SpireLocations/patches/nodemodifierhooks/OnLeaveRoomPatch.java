package SpireLocations.patches.nodemodifierhooks;


import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.NodeModifierField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpirePatch2(clz = AbstractDungeon.class, method = "nextRoomTransitionStart")
public class OnLeaveRoomPatch {
    @SpirePrefixPatch
    public static void onLeaveRoom() {
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        for (AbstractNodeModifier mod : NodeModifierField.modifiers.get(room)) {
            mod.onLeaveRoom();
        }
    }
}
