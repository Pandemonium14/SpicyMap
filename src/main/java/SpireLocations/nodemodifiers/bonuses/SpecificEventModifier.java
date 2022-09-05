package SpireLocations.nodemodifiers.bonuses;

import SpireLocations.SpireLocationsMod;
import SpireLocations.events.ChooseOneEvent;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;

import java.util.ArrayList;

public class SpecificEventModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("SpecificEvent");

    public AbstractEvent event;

    public SpecificEventModifier() {
        super(ID, NodeModType.BONUS, iconPath("Map"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(EventRoom.class);
        return result;
    }

    @Override
    public void onGeneration(AbstractRoom room, int floor) {
        int realFloor = AbstractDungeon.floorNum;
        AbstractDungeon.floorNum = floor;
        try {
            event = AbstractDungeon.generateEvent(AbstractDungeon.mapRng);
        } catch (Exception e) {
            event = null;
        }

        AbstractDungeon.floorNum = realFloor;
    }

    @Override
    public EventHelper.RoomResult forceEventResult() {
        return EventHelper.RoomResult.EVENT;
    }

    @Override
    public void modifyEvent(AbstractEvent intendedEvent) {
        if (event != null) {
            AbstractDungeon.nextRoom.room.event = event;
        }
    }

    @Override
    public String[] getTooltipStrings() {
        String[] result = new String[2];
        result[0] = strings.TEXT[0];
        String eventName;
        if (event != null) {
            eventName = ChooseOneEvent.getEventTitle(event);
            eventName = eventName.replace(" ", " #p");
        } else {
            eventName = strings.EXTRA_TEXT[2];
        }
        result[1] = strings.EXTRA_TEXT[0] + eventName + strings.EXTRA_TEXT[1];
        return result;
    }
}
