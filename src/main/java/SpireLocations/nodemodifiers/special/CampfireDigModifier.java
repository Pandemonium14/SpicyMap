package SpireLocations.nodemodifiers.special;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.ui.campfire.*;

import java.util.ArrayList;

public class CampfireDigModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("CampfireDig");

    public CampfireDigModifier() {
        super(ID, NodeModType.SPECIAL, iconPath("Dig"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(RestRoom.class);
        return result;
    }

    @Override
    public void modifyCampfireOptions(ArrayList<AbstractCampfireOption> options) {
        int indexOfRest = -1;
        AbstractCampfireOption digOption = new DigOption();
        for (AbstractCampfireOption option : options) {
            if (option instanceof RestOption) {
                indexOfRest = options.indexOf(option);
            }
        }
        if (indexOfRest != -1) {
            options.set(indexOfRest, digOption);
        }
    }
}