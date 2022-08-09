package SpicyMap.nodemodifiers.bonuses;

import SpicyMap.SpicyMapMod;
import SpicyMap.events.ChooseOneEvent;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.shrines.AccursedBlacksmith;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import java.util.ArrayList;

public class ChooseOneEventModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("ChooseOneEvent");

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
