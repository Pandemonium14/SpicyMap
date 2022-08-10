package SpireLocations.nodemodifiers.bonuses;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.rooms.TreasureRoom;

import java.util.ArrayList;

public class GoldModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("Gold");

    public GoldModifier() {
        super(ID, NodeModType.BONUS);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoom.class);
        result.add(MonsterRoomElite.class);
        result.add(TreasureRoom.class);
        return result;
    }

    @Override
    public void modifyRewards(ArrayList<RewardItem> rewards) {
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
            rewards.add(new RewardItem(50));
        } else {
            rewards.add(new RewardItem(35));
        }
    }

    @Override
    public void onOpenChest() {
        AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(50));
    }
}
