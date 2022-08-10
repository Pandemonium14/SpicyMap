package SpicyMap.nodemodifiers.rewards;

import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.relics.Strawberry;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class StrawberryModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("Strawberry");

    public StrawberryModifier() {
        super(ID, NodeModType.REWARD);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoomElite.class);
        return result;
    }

    @Override
    public void modifyRewards(ArrayList<RewardItem> rewards) {
        rewards.add(new RewardItem(new Strawberry()));
    }
}
