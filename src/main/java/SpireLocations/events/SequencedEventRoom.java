package SpireLocations.events;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.rooms.EventRoom;

public class SequencedEventRoom extends EventRoom {

    private AbstractEvent eventToHave;

    public SequencedEventRoom(AbstractEvent event) {
        eventToHave = event;
    }

    @Override
    public void onPlayerEntry() {
        AbstractDungeon.overlayMenu.proceedButton.hide();
        try {
            this.event = eventToHave.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        event.onEnterRoom();
    }
}
