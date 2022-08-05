package SpicyMap.patches.nodemodifierhooks;


import SpicyMap.nodemodifiers.AbstractNodeModifier;
import SpicyMap.patches.NodeModifierField;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch2(clz = AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {SaveFile.class})
public class OnEnterRoomPatch {


    @SpirePostfixPatch
    public static void spicyMapOnEnterRoom() {
        if (AbstractDungeon.nextRoom != null) {
            AbstractRoom room = AbstractDungeon.nextRoom.getRoom();
            if (room != null) {
                for (AbstractNodeModifier mod : NodeModifierField.modifiers.get(room)) {
                    mod.postEnterRoom(room);
                }
                if (room.event != null) {
                    ArrayList<AbstractNodeModifier> mods = NodeModifierField.modifiers.get(room);
                    for (AbstractNodeModifier mod : mods) {
                        mod.modifyEvent(room.event);
                    }
                }
            }

        }
    }
}
