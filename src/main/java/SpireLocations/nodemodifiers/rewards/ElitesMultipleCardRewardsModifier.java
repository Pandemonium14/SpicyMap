package SpireLocations.nodemodifiers.rewards;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class ElitesMultipleCardRewardsModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("ElitesMultipleCardRewards");
    public static final int NUMBER_OF_REWARDS = 3;

    public ElitesMultipleCardRewardsModifier() {
        super(ID, NodeModType.REWARD, iconPath("MultipleCardRewards"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoomElite.class);
        return result;
    }

    @Override
    public void modifyRewards(ArrayList<RewardItem> rewards) {
        for (int i = 0 ; i < NUMBER_OF_REWARDS; i++) {
            RewardItem r = new RewardItem();
            rewards.add(r);
        }
    }
}
