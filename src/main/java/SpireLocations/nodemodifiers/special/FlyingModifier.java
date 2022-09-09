package SpireLocations.nodemodifiers.special;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;

import java.util.ArrayList;

public class FlyingModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("Flying");

    public FlyingModifier() {
        super(ID, NodeModType.SPECIAL, iconPath("Flying"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(EventRoom.class);
        return result;
    }

    @Override
    public boolean modifyAccessFromThis(int currentFloor, MapRoomNode currentNode, MapRoomNode destination) {
        return destination.y == currentFloor + 1;
    }
}
