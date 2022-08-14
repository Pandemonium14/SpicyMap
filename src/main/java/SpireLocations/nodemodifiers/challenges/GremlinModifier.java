package SpireLocations.nodemodifiers.challenges;

import SpireLocations.SpireLocationsMod;
import SpireLocations.nodemodifiers.AbstractNodeModifier;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class GremlinModifier extends AbstractNodeModifier {

    public static final String ID = SpireLocationsMod.makeID("Gremlin");

    public GremlinModifier() {
        super(ID, NodeModType.CHALLENGE, iconPath("Gremlin"));
    }

    @Override
    public ArrayList<Class<? extends AbstractRoom>> getRoomClasses() {
        return new ArrayList<Class<? extends AbstractRoom>>() {{
            add(MonsterRoom.class);
            add(MonsterRoomElite.class);
        }};
    }

    private ArrayList<AbstractMonster> getGremlins(int act, ArrayList<AbstractMonster> monsters) {
        float leftMostMonsterOffset = Settings.WIDTH;
        AbstractMonster leftMostMonster = null;
        for (AbstractMonster m : monsters) {
            float mOffset = (m.drawX - Settings.WIDTH*0.75f) /Settings.xScale;
            if (mOffset < leftMostMonsterOffset) {
                leftMostMonsterOffset = mOffset;
                leftMostMonster = m;
            }
        }
        assert leftMostMonster != null;
        leftMostMonsterOffset -= leftMostMonster.hb.width;

        ArrayList<AbstractMonster> gremlins = new ArrayList<>();
        while (gremlins.size() < act) {
            int r = AbstractDungeon.mapRng.random(3);
            switch (r) {
                case 0:
                    gremlins.add(new GremlinFat( (gremlins.size()) * -125f * Settings.xScale + leftMostMonsterOffset, 0f));
                    break;
                case 1:
                    gremlins.add(new GremlinTsundere((gremlins.size()) * -125f * Settings.xScale + leftMostMonsterOffset, 0f));
                    break;
                case 2:
                    gremlins.add(new GremlinThief((gremlins.size()) * -125f * Settings.xScale + leftMostMonsterOffset, 0f));
                    break;
                case 3:
                    gremlins.add(new GremlinWarrior((gremlins.size()) * -125f * Settings.xScale + leftMostMonsterOffset, 0f));
                    break;
                default:
                    gremlins.add(new GremlinWizard((gremlins.size()) * -125f * Settings.xScale + leftMostMonsterOffset, 0f));
            }
        }
        return gremlins;
    }

    @Override
    public void postEnterRoom(AbstractRoom room) {
        int nbOfGremlins = AbstractDungeon.actNum;
        ArrayList<AbstractMonster> gremlins = getGremlins(nbOfGremlins, room.monsters.monsters);
        for (AbstractMonster gremlin : gremlins) {
            gremlin.rollMove();
            ReflectionHacks.setPrivate(gremlin, AbstractCreature.class, "targetHealthBarWidth", gremlin.hb.width * (float)gremlin.currentHealth / (float)gremlin.maxHealth);
        }
        room.monsters.monsters.addAll(gremlins);
    }

    @Override
    public String[] getTooltipStrings() {
        String[] result = new String[2];
        result[0] = strings.TEXT[0];
        if (AbstractDungeon.actNum == 1) {
            result[1] = strings.EXTRA_TEXT[0];
        } else {
            result[1] = strings.EXTRA_TEXT[1] + AbstractDungeon.actNum + strings.EXTRA_TEXT[2];
        }
        return result;
    }
}
