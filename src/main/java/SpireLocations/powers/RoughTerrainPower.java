package SpireLocations.powers;

import SpireLocations.SpireLocationsMod;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RoughTerrainPower extends AbstractPower {

    public RoughTerrainPower() {
        this.name = "Rough Terrain";
        this.ID = SpireLocationsMod.makeID("RoughTerrainPower");
        this.owner = AbstractDungeon.player;
        this.updateDescription();
        this.loadRegion("confusion");
        this.priority = 25;
    }


    @Override
    public void updateDescription() {
        description = "At the start of your turn, draw #b1 card, then discard #b2.";
    }

    public void atStartOfTurnPostDraw() {
        this.flash();// 36
        this.addToBot(new DrawCardAction(this.owner, 1));// 37
        this.addToBot(new DiscardAction(this.owner, this.owner, 2, false));// 38
    }
}
