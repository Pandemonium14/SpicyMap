package SpireLocations.patches.nodemodifierhooks;


import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.NodeModifierField;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpirePatch2(clz = AbstractChest.class, method = "update")
public class OnTryOpenChestPatch {

    @SpireInsertPatch(rloc = 2)
    public static void checkModifiers(AbstractChest __instance) {
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        boolean openChest = true;
        for (AbstractNodeModifier mod : NodeModifierField.modifiers.get(room)) {
            openChest = openChest && mod.onTryOpenChest();
        }
        if (!openChest) {
            Hitbox hb = ReflectionHacks.getPrivate(__instance, AbstractChest.class, "hb");
            hb.hovered = false;
        }
    }
}