package SpireLocations.nodemodifiers.special;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.SmithOption;
import com.megacrit.cardcrawl.ui.campfire.TokeOption;

import java.util.ArrayList;

public class CampfireTokeModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("CampfireToke");

    public CampfireTokeModifier() {
        super(ID, NodeModType.SPECIAL, iconPath("Toke"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(RestRoom.class);
        return result;
    }


    @Override
    public void modifyCampfireOptions(ArrayList<AbstractCampfireOption> options) {
        int indexOfSmith = -1;
        AbstractCampfireOption tokeOption = new TokeOption(AbstractDungeon.player.masterDeck.getPurgeableCards().group.size() > 0);
        for (AbstractCampfireOption option : options) {
            if (option instanceof SmithOption) {
                indexOfSmith = options.indexOf(option);
            }
        }
        if (indexOfSmith != -1) {
            options.set(indexOfSmith, tokeOption);
        }
    }
}
