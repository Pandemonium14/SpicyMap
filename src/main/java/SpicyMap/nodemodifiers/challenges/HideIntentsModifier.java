package SpicyMap.nodemodifiers.challenges;

import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import SpicyMap.patches.nodemodifierhooks.HideIntentsPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class HideIntentsModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("HideIntents");

    public HideIntentsModifier() {
        super(ID, NodeModType.CHALLENGE);
    }


    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(MonsterRoom.class);
            add(MonsterRoomElite.class);
        }};
    }

}
