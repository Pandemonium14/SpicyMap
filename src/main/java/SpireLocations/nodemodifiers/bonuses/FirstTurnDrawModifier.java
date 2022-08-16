package SpireLocations.nodemodifiers.bonuses;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class FirstTurnDrawModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("FirstTurnDraw");
    private static final int DRAW_AMOUNT = 2;

    public FirstTurnDrawModifier() {
        super(ID, NodeModType.BONUS, iconPath("Surprise"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoomElite.class);
        result.add(MonsterRoom.class);
        return result;
    }

    @Override
    public void atBattleStart() {
        addToBot(new DrawCardAction(DRAW_AMOUNT));
    }
}
