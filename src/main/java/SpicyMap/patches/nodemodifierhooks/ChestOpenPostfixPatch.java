package SpicyMap.patches.nodemodifierhooks;


import SpicyMap.nodemodifiers.AbstractNodeModifier;
import SpicyMap.patches.NodeModifierField;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;

@SpirePatch2(clz = AbstractChest.class, method = "open")
public class ChestOpenPostfixPatch {

    @SpirePostfixPatch
    public static void onChestOpenModifier() {
        for (AbstractNodeModifier mod : NodeModifierField.modifiers.get(AbstractDungeon.getCurrRoom())) {
            mod.onOpenChest();
        }
    }
}
