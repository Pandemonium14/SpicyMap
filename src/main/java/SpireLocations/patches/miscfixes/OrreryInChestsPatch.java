package SpireLocations.patches.miscfixes;


import SpireLocations.actionsandeffects.AddRewardEffect;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.Orrery;
import com.megacrit.cardcrawl.rewards.RewardItem;

@SpirePatch2(clz = Orrery.class, method = "onEquip")
public class OrreryInChestsPatch {

    @SpirePrefixPatch
    public static SpireReturn<Void> ChestFix() {
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            for (int i = 0 ; i < 5; i++) {
                RewardItem r = new RewardItem();
                AbstractDungeon.effectsQueue.add(new AddRewardEffect(r));
            }
            return SpireReturn.Return();
        } else {
            return SpireReturn.Continue();
        }
    }

}
