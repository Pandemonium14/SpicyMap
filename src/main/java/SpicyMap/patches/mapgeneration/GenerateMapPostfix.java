package SpicyMap.patches.mapgeneration;


import SpicyMap.NodeModifierHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;

import java.util.ArrayList;
import java.util.Map;

@SpirePatch2(clz = AbstractDungeon.class, method = "generateMap")
public class GenerateMapPostfix {
    @SpirePostfixPatch
    public static void spicyMapPostfix() {
        addModifiers(AbstractDungeon.map);
    }


    private static void addModifiers(ArrayList<ArrayList<MapRoomNode>> map) {
        for (ArrayList<MapRoomNode> floor : map) {
            for (MapRoomNode node : floor) {
                if (node.room != null) NodeModifierHelper.addModifier(node, map.indexOf(floor));
            }
        }
    }
}
