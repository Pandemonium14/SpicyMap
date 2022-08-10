package SpireLocations.patches.nodemodifierhooks;


import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.NodeModifierField;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.shop.ShopScreen;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch2(clz = ShopScreen.class, method = "updatePurgeCard")
public class PurgeTooltipPatch {

    @SpireInsertPatch(locator = Locator.class)
    public static SpireReturn<Void> renderModifiedTip() {
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        for (AbstractNodeModifier mod : NodeModifierField.modifiers.get(room)) {
            if (mod.changePurgeTooltip) {
                String[] tip = mod.purgeTooltip();
                TipHelper.renderGenericTip((float) InputHelper.mX - 360.0F * Settings.scale, (float)InputHelper.mY - 70.0F * Settings.scale, tip[0], tip[1]);
                return SpireReturn.Return();
            }
        }
        return SpireReturn.Continue();
    }



    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {

            Matcher finalMatcher = new Matcher.MethodCallMatcher(TipHelper.class, "renderGenericTip");

            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }
}
