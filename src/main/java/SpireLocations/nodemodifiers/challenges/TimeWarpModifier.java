package SpireLocations.nodemodifiers.challenges;

import SpireLocations.NodeModifierHelper;
import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.NodeModifierField;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.TimeWarpPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.function.Predicate;

public class TimeWarpModifier extends AbstractNodeModifier {

    public static String ID = SpireLocationsMod.makeID("TimeWarp");

    public TimeWarpModifier() {
        super(ID, NodeModType.CHALLENGE, iconPath("TimeWarp"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(MonsterRoom.class);
        }};
    }

    @Override
    public void atBattleStart() {
        AbstractMonster m = m = AbstractDungeon.getRandomMonster();;
        while (m.hasPower(MinionPower.POWER_ID)) {
            m = AbstractDungeon.getRandomMonster();
        }
        addToBot(new ApplyPowerAction(m, m, new TimeWarpPower(m)));
    }

    @Override
    public void onGeneration(AbstractRoom room) {
        boolean addedReward = false;
        ArrayList<AbstractNodeModifier> mods = NodeModifierField.modifiers.get(room);
        while (!addedReward) {
            AbstractNodeModifier mod = NodeModifierHelper.getModifier(room.getClass(), NodeModType.REWARD);
            if (mod != null) {
                BaseMod.logger.log(Level.INFO,"Trying to add reward " + mod.MODIFIER_ID + " to Time warp node...");
                if (mods.stream().noneMatch(m -> m.MODIFIER_ID.equals(mod.MODIFIER_ID))) {
                    BaseMod.logger.log(Level.INFO, "Successfully added" + mod.MODIFIER_ID + "modifier to Time Warp node.");
                    mods.add(mod);
                    addedReward = true;
                } else {
                    BaseMod.logger.log(Level.INFO, mod.MODIFIER_ID + " is already present in that room.");
                }
            } else {
                break;
            }
        }

    }
}
