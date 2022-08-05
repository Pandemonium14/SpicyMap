package SpicyMap.patches.miscfixes;


import SpicyMap.nodemodifiers.AbstractNodeModifier;
import SpicyMap.patches.NodeModifierField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;

@SpirePatch2(clz = AbstractDungeon.class, method = "generateRoom")
public class KeepModifiersWithGenerateRoomPatch {

    private static ArrayList<AbstractNodeModifier> originalMods;

    @SpirePrefixPatch
    public static void saveModifiers() {
        AbstractRoom originalRoom = AbstractDungeon.nextRoom.room;
        originalMods = NodeModifierField.modifiers.get(originalRoom);
    }

    @SpirePostfixPatch
    public static AbstractRoom applyModifiers(AbstractRoom __result) {
        NodeModifierField.modifiers.set(__result, originalMods);
        return __result;
    }
}
