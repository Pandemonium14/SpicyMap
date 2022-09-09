package SpireLocations.nodemodifiers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import java.util.ArrayList;

public abstract class AbstractNodeModifier {

    public final String MODIFIER_ID;
    public final ArrayList<Class<? extends AbstractRoom>> roomClasses;
    public final NodeModType type;
    public final UIStrings strings;
    public Texture modTexture = null;

    public static final Texture BONUS_TEXTURE = ImageMaster.loadImage("SpireLocationsResources/images/ui/map/Bonus.png");
    public static final Texture CHALLENGE_TEXTURE = ImageMaster.loadImage("SpireLocationsResources/images/ui/map/Challenge.png");
    public static final Texture SPECIAL_TEXTURE = ImageMaster.loadImage("SpireLocationsResources/images/ui/map/Special.png");

    public boolean changePurgeTooltip = false;


    public AbstractNodeModifier(String ID, NodeModType modType) {
        this.MODIFIER_ID = ID;
        roomClasses = getRoomClasses();
        type = modType;
        strings = CardCrawlGame.languagePack.getUIString(MODIFIER_ID);
    }

    public AbstractNodeModifier(String ID, NodeModType modType, String iconPath) {
        this(ID, modType);
        modTexture = ImageMaster.loadImage(iconPath);
    }

    public abstract ArrayList<Class<? extends AbstractRoom>> getRoomClasses();

    public void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    public void addToTop(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }

    public void atBattleStart() {}

    public void modifyRewards(ArrayList<RewardItem> rewards) {}

    public void postEnterRoom(AbstractRoom room) {}

    public void onLeaveRoom() {}

    public void onOpenChest() {}

    public void onGeneration(AbstractRoom room) {}

    public void onGeneration(AbstractRoom room, int floor) {}

    public void modifyCampfireOptions(ArrayList<AbstractCampfireOption> options) {}

    public boolean modifyAccessToThis(int currentFloor, MapRoomNode currentNode, MapRoomNode destination) {
        return false;
    }

    public boolean modifyAccessFromThis(int currentFloor, MapRoomNode currentNode, MapRoomNode destination) {
        return false;
    }

    //return false to prevent the chest's opening
    public boolean onTryOpenChest() {return true;}

    public String[] purgeTooltip() {
        return new String[2];
    }


    //returns whether or not it should stop the purging (false = continue)
    public boolean modifyShopPurge(ArrayList<AbstractCard> selectedCards) {
        return false;
    }

    public void modifyEvent(AbstractEvent intendedEvent) {

    }

    public EventHelper.RoomResult forceEventResult() {
        return null;
    }

    public enum NodeModType {
        BONUS, CHALLENGE, SPECIAL, REWARD
    }


    public void render(SpriteBatch sb, float drawScale, float x, float y) {
        Texture texture;
        if (modTexture != null) {
            texture = modTexture;
        } else {
            switch (type) {
                case BONUS:
                    texture = BONUS_TEXTURE;
                    break;
                case CHALLENGE:
                    texture = CHALLENGE_TEXTURE;
                    break;
                case REWARD:
                    return;
                default:
                    texture = SPECIAL_TEXTURE;
            }
        }
        sb.setColor(Color.WHITE);
        sb.draw(texture, x, y, 64f,64f,128f,128f, drawScale, drawScale, 0f,0,0,128,128, false,false);

    }

    public AbstractNodeModifier makeCopy() {
        try {
            return this.getClass().newInstance();
        } catch (IllegalAccessException | InstantiationException var2) {
            throw new RuntimeException("Couldn't make copy of node modifier " + MODIFIER_ID);
        }
    }

    public String[] getTooltipStrings() {
        String[] result = new String[2];
        result[0] = strings.TEXT[0];
        result[1] = strings.EXTRA_TEXT[0];
        return result;
    }

    public static String iconPath(String icon) {
        return "SpireLocationsResources/images/ui/map/"+ icon +".png";
    }
}
