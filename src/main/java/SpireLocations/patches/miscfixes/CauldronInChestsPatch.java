package SpireLocations.patches.miscfixes;

import SpireLocations.actionsandeffects.AddRewardEffect;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.Cauldron;
import com.megacrit.cardcrawl.rewards.RewardItem;

@SpirePatch2(clz = Cauldron.class, method = "onEquip")
public class CauldronInChestsPatch {

    @SpirePrefixPatch
    public static SpireReturn<Void> ChestFix() {
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            for (int i = 0 ; i < 5; i++) {
                AbstractPotion potion = AbstractDungeon.returnRandomPotion();
                RewardItem r = new RewardItem(potion);
                AbstractDungeon.effectsQueue.add(new AddRewardEffect(r));
            }
            return SpireReturn.Return();
        } else {
            return SpireReturn.Continue();
        }
    }
}
