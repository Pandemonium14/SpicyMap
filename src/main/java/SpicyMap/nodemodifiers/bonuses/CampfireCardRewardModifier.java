package SpicyMap.nodemodifiers.bonuses;

import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

import java.util.ArrayList;

public class CampfireCardRewardModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("CampfireCardReward");

    public CampfireCardRewardModifier() {
        super(ID, NodeModType.BONUS);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(RestRoom.class);
        return result;
    }

    @Override
    public void postEnterRoom(AbstractRoom room) {
        AbstractDungeon.cardRewardScreen.open(AbstractDungeon.getRewardCards(),null, CardRewardScreen.TEXT[1]);
    }
}
