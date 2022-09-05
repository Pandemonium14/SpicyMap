package SpireLocations.events;

import SpireLocations.SpireLocationsMod;
import basemod.CustomEventRoom;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;

import java.util.ArrayList;
import java.util.Iterator;

public class ChooseOneEvent extends AbstractImageEvent {

    public static final String ID = SpireLocationsMod.makeID("ChooseOne");
    public static final EventStrings strings = CardCrawlGame.languagePack.getEventString(ID);

    public AbstractEvent firstChoice;
    public AbstractEvent secondChoice;

    public ChooseOneEvent(AbstractEvent event1, AbstractEvent event2) {
        super(strings.NAME, strings.DESCRIPTIONS[0],"images/events/winding.jpg");

        firstChoice = event1;
        secondChoice = event2;
        String firstOption = strings.OPTIONS[0] + getEventTitle(firstChoice).replace(" ", " #p") + strings.OPTIONS[1];
        String secondOption = strings.OPTIONS[0] + getEventTitle(secondChoice).replace(" ", " #p") + strings.OPTIONS[1];

        imageEventText.setDialogOption(firstOption);
        imageEventText.setDialogOption(secondOption);
    }

    @Override
    protected void buttonEffect(int i) {
        switch (i) {
            case 0 :
                switchToEvent(firstChoice);
                break;
            case 1 :
                switchToEvent(secondChoice);
                break;
        }
    }

    public static String getEventTitle(AbstractEvent event) {
        if (event instanceof AbstractImageEvent) {
            return ReflectionHacks.getPrivate(event, AbstractImageEvent.class, "title");
        } else {
            try {
                String titleFromId = ReflectionHacks.getPrivate(event, event.getClass(), "ID");
                if (titleFromId != null) {
                    return titleFromId;
                } else {
                    return event.getClass().getSimpleName();
                }
            } catch (Exception ex) {
                return event.getClass().getSimpleName();
            }

        }
    }

    private static String getEventID(AbstractEvent event) {
        return ReflectionHacks.getPrivate(event, event.getClass(), "ID");
    }

    public static void switchToEvent(AbstractEvent event) {
        RoomEventDialog.optionList.clear();// 90
        MapRoomNode cur = AbstractDungeon.currMapNode;// 94
        MapRoomNode node = new MapRoomNode(cur.x, cur.y);// 95
        node.room = new SequencedEventRoom(event);
        ArrayList<MapEdge> curEdges = cur.getEdges();// 98
        Iterator var8 = curEdges.iterator();// 99

        while(var8.hasNext()) {
            MapEdge edge = (MapEdge)var8.next();
            node.addEdge(edge);// 100
        }

        AbstractDungeon.previousScreen = null;// 105
        AbstractDungeon.dynamicBanner.hide();// 106
        AbstractDungeon.dungeonMapScreen.closeInstantly();// 107
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.topPanel.unhoverHitboxes();
        AbstractDungeon.fadeIn();
        AbstractDungeon.effectList.clear();
        AbstractDungeon.topLevelEffects.clear();
        AbstractDungeon.topLevelEffectsQueue.clear();
        AbstractDungeon.effectsQueue.clear();
        AbstractDungeon.dungeonMapScreen.dismissable = true;
        AbstractDungeon.nextRoom = node;
        AbstractDungeon.setCurrMapNode(node);
        AbstractDungeon.getCurrRoom().onPlayerEntry();
        AbstractDungeon.scene.nextRoom(node.room);
        AbstractDungeon.rs = node.room.event instanceof AbstractImageEvent ? AbstractDungeon.RenderScene.EVENT : AbstractDungeon.RenderScene.NORMAL;
    }
}
