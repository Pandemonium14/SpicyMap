package SpicyMap.nodemodifiers.rewards;

import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import java.util.ArrayList;

public class SecondCardRewardModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("SecondCardReward");

    public SecondCardRewardModifier() {
        super(ID, NodeModType.REWARD);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoom.class);
        return result;
    }

    @Override
    public void modifyRewards(ArrayList<RewardItem> rewards) {
        rewards.add(new RewardItem());
    }

}
