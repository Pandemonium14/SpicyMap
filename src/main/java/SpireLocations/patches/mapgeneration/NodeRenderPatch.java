package SpireLocations.patches.mapgeneration;


import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.NodeModifierField;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;

import java.util.ArrayList;

@SpirePatch2(clz = MapRoomNode.class, method = "render")
public class NodeRenderPatch {

    @SpirePostfixPatch
    public static void renderModifier(MapRoomNode __instance, SpriteBatch sb) {
        if (__instance.room == null) return;
        ArrayList<AbstractNodeModifier> mods = NodeModifierField.modifiers.get(__instance.room);
        if (mods.size() > 0) {
            float SPACING_X = ReflectionHacks.getPrivateStatic(MapRoomNode.class, "SPACING_X");
            float OFFSET_X = ReflectionHacks.getPrivateStatic(MapRoomNode.class, "OFFSET_X");
            float OFFSET_Y = ReflectionHacks.getPrivateStatic(MapRoomNode.class, "OFFSET_Y");
            float scale = ReflectionHacks.getPrivate(__instance, MapRoomNode.class, "scale");

            float x = __instance.x * SPACING_X + OFFSET_X - 64f + __instance.offsetX;
            float y = __instance.y * Settings.MAP_DST_Y + OFFSET_Y + DungeonMapScreen.offsetY - 64f + __instance.offsetY;
            float drawScale = scale * Settings.scale;
            if (Settings.isMobile) drawScale *= 2f;

            for (AbstractNodeModifier mod : mods) {
                mod.render(sb, drawScale, x, y);
            }


        }
    }
}
