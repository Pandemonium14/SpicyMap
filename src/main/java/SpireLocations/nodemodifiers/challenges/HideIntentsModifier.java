package SpireLocations.nodemodifiers.challenges;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class HideIntentsModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("HideIntents");

    public HideIntentsModifier() {
        super(ID, NodeModType.CHALLENGE, iconPath("DarkFog"));
    }


    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(MonsterRoom.class);
            add(MonsterRoomElite.class);
        }};
    }

}
