package SpicyMap.nodemodifiers.bonuses;

import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.shop.ShopScreen;

import java.util.ArrayList;

public class ShopUpgradeModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("ShopUpgrade");
    private static final Texture replacementImg = ImageMaster.loadImage("SpicyMapResources/images/ui/purge/Upgrade.png");
    private Texture savedPurgeTexture;

    public ShopUpgradeModifier() {
        super(ID, NodeModType.BONUS);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(ShopRoom.class);
        }};
    }

    @Override
    public boolean modifyShopPurge(ArrayList<AbstractCard> selectedCards) {
        upgradeCard(selectedCards);
        return false;
    }

    private void upgradeCard(ArrayList<AbstractCard> selectedCards) {

        ArrayList<AbstractCard> upgradableCards = AbstractDungeon.player.masterDeck.getUpgradableCards().group;
        boolean upgradedACard = false;

        while (!upgradedACard && upgradableCards.size() > 0) {
            int r = AbstractDungeon.cardRng.random(upgradableCards.size() - 1 );
            AbstractCard c = upgradableCards.get(r);
            if (!selectedCards.contains(c)) {
                c.upgrade();
                upgradedACard = true;
            } else {
                upgradableCards.remove(c);
            }
        }
    }

    @Override
    public void postEnterRoom(AbstractRoom room) {
        savedPurgeTexture = ReflectionHacks.getPrivateStatic(ShopScreen.class, "removeServiceImg");
        ReflectionHacks.setPrivateStatic(ShopScreen.class, "removeServiceImg",replacementImg);
    }

    @Override
    public void onLeaveRoom() {
        ReflectionHacks.setPrivateStatic(ShopScreen.class, "removeServiceImg",savedPurgeTexture);
    }



}
