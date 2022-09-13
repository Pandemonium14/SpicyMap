package SpireLocations.nodemodifiers.challenges;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import java.util.ArrayList;

public class StrongEnemiesModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("StrongEnemies");
    public int strAmount = 2;

    public StrongEnemiesModifier() {
        super(ID, NodeModType.CHALLENGE, iconPath("DemonForm"));
        strAmount = AbstractDungeon.actNum;
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        ArrayList<Class<? extends AbstractRoom>> result = new ArrayList<>();
        result.add(MonsterRoom.class);
        return result;
    }

    @Override
    public void atBattleStart() {
        MonsterGroup monsters = AbstractDungeon.getMonsters();
        for (AbstractMonster m : monsters.monsters) {
            addToBot(new ApplyPowerAction(m,m, new StrengthPower(m, strAmount)));
        }
    }

    @Override
    public String[] getTooltipStrings() {
        String[] result = new String[2];
        result[0] = strings.TEXT[0];
        result[1] = strings.EXTRA_TEXT[0] + strAmount + strings.EXTRA_TEXT[1];
        return result;
    }
}
