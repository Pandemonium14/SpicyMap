package SpireLocations.nodemodifiers.rewards;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class RareCardRewardModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("RareCardReward");

    public RareCardRewardModifier() {
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
        RewardItem reward = new RewardItem();
        ReflectionHacks.setPrivate(reward, RewardItem.class, "isBoss", true);

        CardGroup rares = CardLibrary.getEachRare(AbstractDungeon.player);

        ArrayList<AbstractCard> cards = new ArrayList<>();
        while (cards.size() < 3) {
            AbstractCard card = rares.getRandomCard(AbstractDungeon.cardRng);
            if (!cards.contains(card)) {
                cards.add(card);
            }
        }
        reward.cards = cards;
        rewards.add(reward);
    }
}
