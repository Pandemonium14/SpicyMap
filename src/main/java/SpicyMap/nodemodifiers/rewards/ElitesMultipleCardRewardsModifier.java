package SpicyMap.nodemodifiers.rewards;

import SpicyMap.SpicyMapMod;
import SpicyMap.actionsandeffects.AddRewardEffect;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.rooms.RestRoom;

import java.util.ArrayList;

public class ElitesMultipleCardRewardsModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("ElitesMultipleCardRewards");
    public static final int NUMBER_OF_REWARDS = 3;

    public ElitesMultipleCardRewardsModifier() {
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
        for (int i = 0 ; i < NUMBER_OF_REWARDS; i++) {
            RewardItem r = new RewardItem();
            rewards.add(r);
        }
    }
}
