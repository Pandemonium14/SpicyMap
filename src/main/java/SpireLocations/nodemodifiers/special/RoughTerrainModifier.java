package SpireLocations.nodemodifiers.special;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.powers.RoughTerrainPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class RoughTerrainModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("RoughTerrain");

    public RoughTerrainModifier() {
        super(ID, NodeModType.SPECIAL, iconPath("Tools"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(MonsterRoom.class);
            add(MonsterRoomElite.class);
        }};
    }

    @Override
    public void atBattleStart() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RoughTerrainPower()));

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                addToBot(new DrawCardAction(AbstractDungeon.player, 1));// 37
                addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 2, false));
                isDone = true;
            }
        });
    }
}
