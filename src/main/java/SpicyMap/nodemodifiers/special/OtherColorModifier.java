package SpicyMap.nodemodifiers.special;

import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import java.util.ArrayList;

public class OtherColorModifier extends AbstractNodeModifier {
    public static final String ID = SpicyMapMod.makeID("OtherColor");

    public final CardLibrary.LibraryType modColor;

    public OtherColorModifier() {
        super(ID, NodeModType.SPECIAL);
        modColor = randomizeColor();
    }

    public OtherColorModifier(CardLibrary.LibraryType color) {
        super(ID, NodeModType.SPECIAL);
        modColor = color;
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoom.class);
        return result;
    }

    public CardLibrary.LibraryType randomizeColor() {
        CardLibrary.LibraryType color = CardLibrary.LibraryType.CURSE;
        while (color == CardLibrary.LibraryType.CURSE || CardLibrary.getCardList(color).get(0).color == AbstractDungeon.player.getCardColor()) {
            int i = AbstractDungeon.mapRng.random(CardLibrary.LibraryType.values().length - 1);
            color = CardLibrary.LibraryType.values()[i];
        }
        return color;
    }

    @Override
    public void modifyRewards(ArrayList<RewardItem> rewards) {
        int rewardSize = 3;
        ArrayList<AbstractCard> cards = new ArrayList<>();
        ArrayList<AbstractCard> pool = CardLibrary.getCardList(modColor);
        while (cards.size() < rewardSize) {
            int randomIndex = AbstractDungeon.treasureRng.random(pool.size()-1);
            AbstractCard c = pool.get(randomIndex);
            if (!cards.contains(c) && c.rarity != AbstractCard.CardRarity.SPECIAL) {
                cards.add(c);
            }
        }
        RewardItem reward = new RewardItem();
        reward.cards = cards;
        rewards.add(reward);
    }

    @Override
    public String[] getTooltipStrings() {
        String[] tipStrings = new String[2];
        tipStrings[0] = strings.TEXT[0];
        tipStrings[1] = strings.EXTRA_TEXT[0] + modColor.name().toLowerCase().replace("_"," ") + strings.EXTRA_TEXT[1];
        return tipStrings;
    }
}
