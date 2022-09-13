package SpireLocations.nodemodifiers.challenges;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import basemod.BaseMod;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class ReduceHandSizeModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("ReduceHandSize");

    private static final int HAND_SIZE_REDUCTION = 3;

    public ReduceHandSizeModifier() {
        super(ID, NodeModType.CHALLENGE, iconPath("Tight"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoomElite.class);
        result.add(MonsterRoom.class);
        return result;
    }

    @Override
    public void postEnterRoom(AbstractRoom room) {
        BaseMod.MAX_HAND_SIZE -= HAND_SIZE_REDUCTION;
    }

    @Override
    public void onLeaveRoom() {
        BaseMod.MAX_HAND_SIZE += HAND_SIZE_REDUCTION;
    }

    @Override
    public boolean enableInAct(int actNum) {
        return actNum >= 2;
    }
}
