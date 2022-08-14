package SpireLocations.patches.nodemodifierhooks;


import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.nodemodifiers.challenges.HideIntentsModifier;
import SpireLocations.patches.NodeModifierField;
import basemod.abstracts.CustomMonster;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.util.ArrayList;


public class HideIntentsPatch {

    public static boolean hideIntents() {
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        ArrayList<AbstractNodeModifier> mods = NodeModifierField.modifiers.get(room);
        boolean hideIntents = false;
        for (AbstractNodeModifier mod : mods) {
            if (mod instanceof HideIntentsModifier) {
                hideIntents = true;
                break;
            }
        }
        return hideIntents;
    }


    @SpirePatch2(clz = AbstractMonster.class, method = "render")
    public static class IntentRender {

        @SpireInstrumentPatch
        public static ExprEditor checkForModifier() {
            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException
                {
                    if (m.getClassName().equals(AbstractPlayer.class.getName())
                            && m.getMethodName().equals("hasRelic"))
                        m.replace("{$_ = ($proceed($$) || SpireLocations.patches.nodemodifierhooks.HideIntentsPatch.hideIntents());}");
                }
            };
        }
    }

    @SpirePatch2(clz = AbstractMonster.class, method = "renderTip")
    public static class IntentTips {

        @SpireInstrumentPatch
        public static ExprEditor checkForModifier() {
            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException
                {
                    if (m.getClassName().equals(AbstractPlayer.class.getName())
                            && m.getMethodName().equals("hasRelic"))
                        m.replace("{$_ = ($proceed($$) || SpireLocations.patches.nodemodifierhooks.HideIntentsPatch.hideIntents());}");
                }
            };
        }
    }

    @SpirePatch2(clz = CustomMonster.class, method = "render")
    public static class IntentRenderCustomMonster {

        @SpireInstrumentPatch
        public static ExprEditor checkForModifier() {
            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException
                {
                    if (m.getClassName().equals(AbstractPlayer.class.getName())
                            && m.getMethodName().equals("hasRelic"))
                        m.replace("{$_ = ($proceed($$) || SpireLocations.patches.nodemodifierhooks.HideIntentsPatch.hideIntents());}");
                }
            };
        }
    }
}
