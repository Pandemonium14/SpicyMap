package SpicyMap.patches.nodemodifierhooks;


import SpicyMap.nodemodifiers.AbstractNodeModifier;
import SpicyMap.patches.NodeModifierField;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import java.util.ArrayList;

@SpirePatch2(clz = CampfireUI.class, method = "initializeButtons")
public class ModifyCampfireOptionsPatch {

    @SpirePostfixPatch
    public static void modifyOptions(CampfireUI __instance) {
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        ArrayList<AbstractCampfireOption> options = ReflectionHacks.getPrivate(__instance, CampfireUI.class, "buttons");
        for (AbstractNodeModifier mod : NodeModifierField.modifiers.get(room)) {
            mod.modifyCampfireOptions(options);
        }
    }
}
