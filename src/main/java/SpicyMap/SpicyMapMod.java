package SpicyMap;

import SpicyMap.nodemodifiers.*;
import SpicyMap.nodemodifiers.bonuses.DexterityModifier;
import SpicyMap.nodemodifiers.bonuses.HealModifier;
import SpicyMap.nodemodifiers.challenges.GremlinModifier;
import SpicyMap.nodemodifiers.challenges.StrongEnemiesModifier;
import SpicyMap.nodemodifiers.rewards.SecondCardRewardModifier;
import SpicyMap.nodemodifiers.rewards.UpgradedRewardsModifier;
import SpicyMap.nodemodifiers.special.OtherColorModifier;
import SpicyMap.nodemodifiers.special.RoughTerrainModifier;
import SpicyMap.nodemodifiers.special.ShopTransformModifier;
import SpicyMap.patches.NodeModifierField;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class SpicyMapMod implements PostInitializeSubscriber, OnStartBattleSubscriber, EditStringsSubscriber {

    public static final String modID = "spicymap"; //TODO: Change this.

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public SpicyMapMod() {
        BaseMod.subscribe(this);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static void initialize() {
        SpicyMapMod thismod = new SpicyMapMod();
    }

    @Override
    public void receivePostInitialize() {
        NodeModifierHelper.nodeModifiers.add(new DexterityModifier());
        NodeModifierHelper.nodeModifiers.add(new StrongEnemiesModifier());
        NodeModifierHelper.nodeModifiers.add(new HealModifier());
        NodeModifierHelper.nodeModifiers.add(new SecondCardRewardModifier());
        NodeModifierHelper.nodeModifiers.add(new RoughTerrainModifier());
        NodeModifierHelper.nodeModifiers.add(new GremlinModifier());
        NodeModifierHelper.nodeModifiers.add(new UpgradedRewardsModifier());
        NodeModifierHelper.nodeModifiers.add(new OtherColorModifier(CardLibrary.LibraryType.CURSE));
        NodeModifierHelper.nodeModifiers.add(new ShopTransformModifier());


    }









    //hooks for modifiers
    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        for (AbstractNodeModifier mod : NodeModifierField.modifiers.get(abstractRoom)) {
            mod.atBattleStart();
        }
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(UIStrings.class, "SpicyMapResources/localization/eng/UIStrings.json" );
    }
}
