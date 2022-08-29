package SpireLocations.nodemodifiers.rewards;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;

public class ChestRandomUpgradeModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("ChestRandomUpgrade");


    public ChestRandomUpgradeModifier() {
        super(ID, NodeModType.REWARD, iconPath("UpArrowCardReward"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(TreasureRoom.class);
        }};
    }

    @Override
    public void onOpenChest() {
        ArrayList<AbstractCard> upgradableCards = AbstractDungeon.player.masterDeck.getUpgradableCards().group;
        int upgradedCards = 0;
        while (upgradableCards.size() > 0 && upgradedCards < 3) {
            int r = AbstractDungeon.cardRng.random(upgradableCards.size() - 1);
            AbstractCard c = upgradableCards.get(r);
            c.upgrade();
            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect((Settings.WIDTH / 3.0F)*(1+upgradedCards), Settings.HEIGHT / 2.0F));
            AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(),(Settings.WIDTH / 3.0F)*(1+upgradedCards), Settings.HEIGHT / 2.0F));
            upgradedCards += 1;
        }
    }
}
