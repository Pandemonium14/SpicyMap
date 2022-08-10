package SpireLocations.nodemodifiers.challenges;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import java.util.ArrayList;

public class SlimedModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("Slimed");
    public static final int STR_AMOUNT = 2;

    public SlimedModifier() {
        super(ID, NodeModType.CHALLENGE);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoom.class);
        return result;
    }

    @Override
    public void atBattleStart() {
        addToBot(new MakeTempCardInDrawPileAction(new Slimed(),3, true, false));
    }
}
