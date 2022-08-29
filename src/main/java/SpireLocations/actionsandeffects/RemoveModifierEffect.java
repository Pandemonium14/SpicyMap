package SpireLocations.actionsandeffects;

import SpireLocations.nodemodifiers.AbstractNodeModifier;
import SpireLocations.patches.NodeModifierField;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class RemoveModifierEffect extends AbstractGameEffect {

    private AbstractNodeModifier mod;
    private AbstractRoom room;

    public RemoveModifierEffect(AbstractNodeModifier mod, AbstractRoom room) {
        this.mod = mod;
        this.room = room;
    }

    @Override
    public void update() {
        NodeModifierField.modifiers.get(room).remove(mod);
        isDone = true;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
