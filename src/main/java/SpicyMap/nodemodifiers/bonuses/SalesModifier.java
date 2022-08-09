package SpicyMap.nodemodifiers.bonuses;

import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;

import java.util.ArrayList;

public class SalesModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("Sales");
    private static final float REDUCTION = 0.70f;

    public SalesModifier() {
        super(ID, NodeModType.BONUS);
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(ShopRoom.class);
        }};
    }

    @Override
    public void postEnterRoom(AbstractRoom room) {
        AbstractDungeon.shopScreen.applyDiscount(REDUCTION, true);
    }
}
