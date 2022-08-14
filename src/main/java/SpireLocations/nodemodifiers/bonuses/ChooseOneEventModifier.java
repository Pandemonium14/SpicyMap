package SpireLocations.nodemodifiers.bonuses;

import SpireLocations.SpireLocationsMod;
import SpireLocations.events.ChooseOneEvent;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.exordium.Mushrooms;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;

import java.util.ArrayList;

public class ChooseOneEventModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("ChooseOneEvent");

    public ChooseOneEventModifier() {
        super(ID, NodeModType.BONUS);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(EventRoom.class);
        return result;
    }

    @Override
    public EventHelper.RoomResult forceEventResult() {
        return EventHelper.RoomResult.EVENT;
    }

    @Override
    public void modifyEvent(AbstractEvent intendedEvent) {
        Random eventRngDuplicate = new Random(Settings.seed, AbstractDungeon.eventRng.counter);
        AbstractEvent otherChoice = AbstractDungeon.generateEvent(eventRngDuplicate);
        AbstractDungeon.nextRoom.room.event = new ChooseOneEvent(intendedEvent, otherChoice);
    }
}
