package SpireLocations.patches.miscfixes;


import SpireLocations.actionsandeffects.AddModifierEffect;
import SpireLocations.actionsandeffects.RemoveModifierEffect;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

@SpirePatch2(clz = AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {SaveFile.class})
public class UpdateEffectQueueOncePatch {

    @SpirePrefixPatch
    public static void updateEffectQueueOnce(AbstractDungeon __instance) {
        for (AbstractGameEffect e : AbstractDungeon.effectsQueue) {
            if (e instanceof AddModifierEffect || e instanceof RemoveModifierEffect) {
                e.update();
            }
        }
    }
}
