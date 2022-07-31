package SpicyMap.patches;


import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;

@SpirePatch(clz = AbstractRoom.class, method = SpirePatch.CLASS)
public class NodeModifierField {
    public static SpireField<ArrayList<AbstractNodeModifier>> modifiers = new SpireField<>(() -> new ArrayList<>());
}

