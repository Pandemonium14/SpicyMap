package SpireLocations.patches.nodemodifierhooks;


import SpicyShops.patches.SpicyPurgePatches;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.NodeModifierField;
import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.shop.ShopScreen;

import java.beans.FeatureDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;

@SpirePatch2(clz = ShopScreen.class, method = "updatePurge")
public class ModifyShopPurgeEffectPatch {

    @SpireInsertPatch(rloc = 1)
    public static SpireReturn<Void> modifyShopPurge(ShopScreen __instance) {
        boolean interruptPurge = false;
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        ArrayList<AbstractCard> selectedCards = AbstractDungeon.gridSelectScreen.selectedCards;
        for (AbstractNodeModifier mod : NodeModifierField.modifiers.get(room)) {
            interruptPurge = interruptPurge || mod.modifyShopPurge(selectedCards);
        }
        if (interruptPurge) {
            AbstractDungeon.gridSelectScreen.selectedCards.clear();

            boolean doSecondPurge = false;
            if (Loader.isModLoaded("spicyShops")) {
                doSecondPurge = SpicyPurgePatches.additionalPurge;
                SpicyPurgePatches.additionalPurge = false;
            }
            AbstractDungeon.shopScreen.purgeAvailable = doSecondPurge;
            return SpireReturn.Return();
        } else {
            return SpireReturn.Continue();
        }
    }
}
