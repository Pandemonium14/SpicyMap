package SpireLocations.nodemodifiers.special;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.nodemodifierhooks.AccessibleFromPatch;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;

import java.util.ArrayList;

public class FromAnywhereModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("FromAnywhere");

    public FromAnywhereModifier() {
        super(ID, NodeModType.SPECIAL, iconPath("SpacePortal"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(EventRoom.class);
        return result;
    }


    @Override
    public boolean modifyAccessToThis(int floor, MapRoomNode node, MapRoomNode destination) {
        return floor == destination.y - 1;
    }
}
