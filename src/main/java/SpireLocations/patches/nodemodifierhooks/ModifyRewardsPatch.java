package SpireLocations.patches.nodemodifierhooks;


import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.NodeModifierField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;

@SpirePatch2(clz = CombatRewardScreen.class, method = "setupItemReward")
public class ModifyRewardsPatch {

    @SpirePostfixPatch
    public static void addModifiersRewards(CombatRewardScreen __instance) {
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        if (room == null) return;

        for (AbstractNodeModifier mod : NodeModifierField.modifiers.get(room)) {
            mod.modifyRewards(__instance.rewards);
        }
    }
}
