package SpireLocations.nodemodifiers.special;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

public class ShopTransformModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("ShopTransform");

    public int savedPurgeCost = 50;
    public static final int TRANSFORM_COST = 75;
    private static final Texture replacementImg = ImageMaster.loadImage("SpireLocationsResources/images/ui/purge/Transform.png");
    private Texture savedPurgeTexture;


    public ShopTransformModifier() {
        super(ID, NodeModType.SPECIAL);
        changePurgeTooltip = true;
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(ShopRoom.class);
        }};
    }

    @Override
    public boolean modifyShopPurge(ArrayList<AbstractCard> selectedCards) {
        for (AbstractCard c : selectedCards) {
            AbstractDungeon.player.masterDeck.removeCard(c);// 79
            AbstractDungeon.transformCard(c, false, AbstractDungeon.miscRng);// 80
            AbstractCard transCard = AbstractDungeon.getTransformedCard();// 81
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(transCard, c.current_x, c.current_y));
        }
        return true;
    }

    @Override
    public void postEnterRoom(AbstractRoom room) {
        savedPurgeCost = ShopScreen.actualPurgeCost;
        savedPurgeTexture = ReflectionHacks.getPrivateStatic(ShopScreen.class, "removeServiceImg");
        ShopScreen.actualPurgeCost = TRANSFORM_COST;
        ReflectionHacks.setPrivateStatic(ShopScreen.class, "removeServiceImg",replacementImg);
    }

    @Override
    public void onLeaveRoom() {
        ShopScreen.actualPurgeCost = savedPurgeCost;
        ReflectionHacks.setPrivateStatic(ShopScreen.class, "removeServiceImg",savedPurgeTexture);
    }

    @Override
    public String[] purgeTooltip() {
        String[] tip = new String[2];
        tip[0] = strings.EXTRA_TEXT[1];
        tip[1] = strings.EXTRA_TEXT[2];
        return tip;
    }
}
