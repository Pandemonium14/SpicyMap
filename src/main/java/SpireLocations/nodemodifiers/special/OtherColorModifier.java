package SpireLocations.nodemodifiers.special;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import java.util.ArrayList;
import java.util.Map;

public class OtherColorModifier extends AbstractNodeModifier {
    public static final String ID = SpireLocationsMod.makeID("OtherColor");

    public final AbstractCard.CardColor modColor;

    public OtherColorModifier() {
        super(ID, NodeModType.SPECIAL, iconPath("ColoredCardReward"));
        if (SpireLocationsMod.initializing) {
            modColor = AbstractCard.CardColor.CURSE;
        } else {
            modColor = randomizeColor();
        }
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoom.class);
        return result;
    }

    public AbstractCard.CardColor randomizeColor() {
        ArrayList<AbstractCard.CardColor> characterColors = new ArrayList<>();
        for (AbstractPlayer chara : CardCrawlGame.characterManager.getAllCharacters()) {
            if (chara.chosenClass != AbstractDungeon.player.chosenClass) {
                characterColors.add(chara.getCardColor());
            }
        }
        int r = AbstractDungeon.mapRng.random(characterColors.size() - 1);
        return characterColors.get(r);
    }

    private AbstractCard.CardRarity rollRarity() {
        int roll = AbstractDungeon.cardRng.random(99);
        if (roll < 20) {
            return AbstractCard.CardRarity.RARE;
        } else if (roll < 35) {
            return AbstractCard.CardRarity.COMMON;
        } else {
            return AbstractCard.CardRarity.UNCOMMON;
        }
    }

    private ArrayList<AbstractCard> getCardList(AbstractCard.CardColor color) {
        ArrayList<AbstractCard> cardPool = new ArrayList<>();
        for (Map.Entry<String,AbstractCard> c : CardLibrary.cards.entrySet()) {
            if (c.getValue().color == color) {
                cardPool.add(c.getValue());
            }
        }
        return cardPool;
    }

    @Override
    public void modifyRewards(ArrayList<RewardItem> rewards) {
        int rewardSize = 3;
        ArrayList<AbstractCard> cards = new ArrayList<>();
        ArrayList<AbstractCard> pool = getCardList(modColor);
        AbstractCard.CardRarity rarity = rollRarity();

        //this part is awful and lazy
        int tries = 1;
        while (cards.size() < rewardSize) {
            int randomIndex = AbstractDungeon.treasureRng.random(pool.size()-1);
            AbstractCard c = pool.get(randomIndex);
            if (!cards.contains(c) && c.rarity == rarity && c.type != AbstractCard.CardType.STATUS) {
                cards.add(c);
                rollRarity();
                tries = 0;
            }
            tries += 1;
            if (tries > 4) {
                rarity = rollRarity();
                tries = 1;
            }
        }
        //end of the awful and lazy part

        RewardItem reward = null;
        for (RewardItem r : rewards) {
            if (r.cards != null && r.type == RewardItem.RewardType.CARD) {
                reward = r;
                break;
            }
        }
        if (reward != null) {
            reward.cards = cards;
        }
    }

    @Override
    public String[] getTooltipStrings() {
        String[] tipStrings = new String[2];
        tipStrings[0] = strings.TEXT[0];
        tipStrings[1] = strings.EXTRA_TEXT[0] + modColor.name().toLowerCase().replace("_"," ") + strings.EXTRA_TEXT[1];
        return tipStrings;
    }
}
