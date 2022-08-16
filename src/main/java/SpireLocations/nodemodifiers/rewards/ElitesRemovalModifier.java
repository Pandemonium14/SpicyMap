package SpireLocations.nodemodifiers.rewards;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.relic.RemovalRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class ElitesRemovalModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("ElitesRemoval");

    public ElitesRemovalModifier() {
        super(ID, NodeModType.REWARD, iconPath("Purge"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoomElite.class);
        return result;
    }

    @Override
    public void modifyRewards(ArrayList<RewardItem> rewards) {
        rewards.add(new RewardItem(new RemovalRelic()));
    }
}
