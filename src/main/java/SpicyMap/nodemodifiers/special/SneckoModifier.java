package SpicyMap.nodemodifiers.special;

import SpicyMap.SpicyMapMod;
import SpicyMap.nodemodifiers.AbstractNodeModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.DrawPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class SneckoModifier extends AbstractNodeModifier {

    public static final String ID = SpicyMapMod.makeID("Snecko");

    public SneckoModifier() {
        super(ID, NodeModType.SPECIAL);
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
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ConfusionPower(AbstractDungeon.player)));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DrawPower(AbstractDungeon.player,1)));
    }


}
