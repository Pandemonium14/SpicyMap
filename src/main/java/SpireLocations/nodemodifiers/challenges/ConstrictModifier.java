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
    public int constrictAmount;

    public ConstrictModifier() {
        super(ID, NodeModType.CHALLENGE, iconPath("Constrict"));
        constrictAmount = AbstractDungeon.actNum*2;
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
        addToBot(new ApplyPowerAction(p, p, new ConstrictedPower(p, p, constrictAmount)));
    }

    @Override
    public String[] getTooltipStrings() {
        String[] result = new String[2];
        result[0] = strings.TEXT[0];
        result[1] = strings.EXTRA_TEXT[0] + constrictAmount + strings.EXTRA_TEXT[1];
        return result;
    }
}
