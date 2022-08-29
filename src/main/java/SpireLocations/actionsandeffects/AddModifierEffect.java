package SpireLocations.actionsandeffects;

import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.NodeModifierField;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class AddModifierEffect extends AbstractGameEffect {

    private AbstractNodeModifier mod;
    private AbstractRoom room;
    private int floor = -1;

    public AddModifierEffect(AbstractNodeModifier mod, AbstractRoom room) {
        this.mod = mod;
        this.room = room;

    }

    public AddModifierEffect(AbstractNodeModifier mod, AbstractRoom room, int floor) {
        this(mod, room);
        this.floor = floor;
    }

    @Override
    public void update() {
        NodeModifierField.modifiers.get(room).add(mod);
        mod.onGeneration(room);
        mod.onGeneration(room, floor);
        isDone = true;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
