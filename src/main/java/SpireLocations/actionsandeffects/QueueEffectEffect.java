package SpireLocations.actionsandeffects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class QueueEffectEffect extends AbstractGameEffect {

    private final AbstractGameEffect effect;
    private final boolean topLevel;

    public QueueEffectEffect(AbstractGameEffect e, boolean topLevel) {
        this.effect = e;
        this.topLevel = topLevel;
    }

    @Override
    public void update() {
        if (topLevel) {
            AbstractDungeon.topLevelEffects.add(effect);
        } else {
            AbstractDungeon.effectsQueue.add(effect);
        }
        isDone = true;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
