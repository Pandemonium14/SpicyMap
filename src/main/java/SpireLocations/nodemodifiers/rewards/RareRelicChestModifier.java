package SpireLocations.nodemodifiers.rewards;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;

import java.util.ArrayList;

public class RareRelicChestModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("RareRelicChest");

    public RareRelicChestModifier() {
        super(ID, NodeModType.REWARD);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(TreasureRoom.class);
        }};
    }

    @Override
    public void onOpenChest() {
        for (RewardItem reward : AbstractDungeon.combatRewardScreen.rewards) {
            if (reward.type == RewardItem.RewardType.RELIC) {
                AbstractRelic spawnedRelic = reward.relic;
                reward.relic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.RARE);
                reward.text = reward.relic.name;
                AbstractDungeon.rareRelicPool.remove(reward.relic.relicId);
                switch (spawnedRelic.tier) {
                    case COMMON:
                        AbstractDungeon.commonRelicPool.add(spawnedRelic.relicId);
                        break;
                    case UNCOMMON:
                        AbstractDungeon.uncommonRelicPool.add(spawnedRelic.relicId);
                        break;
                    case RARE:
                        AbstractDungeon.rareRelicPool.add(spawnedRelic.relicId);
                        break;
                }
            }
        }
    }
}
