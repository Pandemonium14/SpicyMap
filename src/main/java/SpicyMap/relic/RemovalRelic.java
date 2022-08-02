package SpicyMap.relic;

import SpicyMap.SpicyMapMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class RemovalRelic extends CustomRelic {

    public static String ID = SpicyMapMod.makeID("Removal");
    public static final RelicStrings strings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private boolean cardsSelected = true;

    public RemovalRelic() {
        super(ID, "clean_water.png", RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return strings.DESCRIPTIONS[0];
    }

    public void onEquip() {
        this.cardsSelected = false;// 28
        if (AbstractDungeon.isScreenUp) {// 29
            AbstractDungeon.dynamicBanner.hide();// 30
            AbstractDungeon.overlayMenu.cancelButton.hide();// 31
            AbstractDungeon.previousScreen = AbstractDungeon.screen;// 32
        }

        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;// 34
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);// 36
        Iterator var2 = AbstractDungeon.player.masterDeck.getPurgeableCards().group.iterator();// 37

        while(var2.hasNext()) {
            AbstractCard card = (AbstractCard)var2.next();
            tmp.addToTop(card);// 38
        }

        if (tmp.group.isEmpty()) {// 41
            this.cardsSelected = true;// 42
        } else {
            if (tmp.group.size() <= 2) {// 44
                this.deleteCards(tmp.group);// 45
            } else {
                AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), 1, strings.DESCRIPTIONS[1], false, false, false, true);// 47 48
            }

        }
    }

    public void update() {
        super.update();// 60
        if (!this.cardsSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {// 61 62
            this.deleteCards(AbstractDungeon.gridSelectScreen.selectedCards);// 63
        }
    }

    public void deleteCards(ArrayList<AbstractCard> group) {
        this.cardsSelected = true;// 69
        float displayCount = 0.0F;// 70
        Iterator i = group.iterator();// 71

        while(i.hasNext()) {
            AbstractCard card = (AbstractCard)i.next();// 72
            card.untip();// 73
            card.unhover();// 74
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, (float) Settings.WIDTH / 3.0F + displayCount, (float)Settings.HEIGHT / 2.0F));// 75
            displayCount += (float)Settings.WIDTH / 6.0F;// 77
            AbstractDungeon.player.masterDeck.removeCard(card);// 78
        }

        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;// 81
        AbstractDungeon.gridSelectScreen.selectedCards.clear();// 82
    }
}
