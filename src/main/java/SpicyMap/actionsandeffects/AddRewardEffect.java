package SpicyMap.actionsandeffects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class AddRewardEffect extends AbstractGameEffect {

    private RewardItem reward;

    public AddRewardEffect(RewardItem reward) {
        this.reward = reward;
    }

    @Override
    public void update() {
        AbstractDungeon.combatRewardScreen.rewards.add(reward);
        isDone = true;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
