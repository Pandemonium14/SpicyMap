package SpireLocations.nodemodifiers.challenges;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.rooms.*;

import java.util.ArrayList;

public class ConstrictModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("Constrict");
    public static final int CONSTRICT_AMOUNT = 4;

    public ConstrictModifier() {
        super(ID, NodeModType.CHALLENGE, iconPath("Constrict"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoom.class);
        result.add(MonsterRoomElite.class);
        return result;
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new ConstrictedPower(p, p, CONSTRICT_AMOUNT)));
    }
}
