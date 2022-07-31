package SpicyMap.nodemodifiers.challenges;

import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import java.util.ArrayList;

public class StrongEnemiesModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("StrongEnemies");
    public static final int STR_AMOUNT = 2;

    public StrongEnemiesModifier() {
        super(ID, NodeModType.CHALLENGE);
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
            addToBot(new ApplyPowerAction(m,m, new StrengthPower(m, STR_AMOUNT)));
        }
    }
}
