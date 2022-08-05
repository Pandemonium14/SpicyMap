package SpicyMap;

import SpicyMap.nodemodifiers.*;
import SpicyMap.nodemodifiers.bonuses.DexterityModifier;
import SpicyMap.nodemodifiers.bonuses.GoldModifier;
import SpicyMap.nodemodifiers.bonuses.HealModifier;
import SpicyMap.nodemodifiers.bonuses.ShopRelicChestModifier;
import SpicyMap.nodemodifiers.challenges.GremlinModifier;
import SpicyMap.nodemodifiers.challenges.StrongEnemiesModifier;
import SpicyMap.nodemodifiers.rewards.ElitesRemovalModifier;
import SpicyMap.nodemodifiers.rewards.SecondCardRewardModifier;
import SpicyMap.nodemodifiers.rewards.UpgradedRewardsModifier;
import SpicyMap.nodemodifiers.special.*;
import SpicyMap.patches.NodeModifierField;
import SpicyMap.relic.RemovalRelic;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class SpicyMapMod implements PostInitializeSubscriber, OnStartBattleSubscriber, EditStringsSubscriber, EditRelicsSubscriber {

    public static final String modID = "spicymap";

    public static boolean initializing = true;

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
        new AutoAdd(modID)
                .packageFilter("SpicyMap.nodemodifiers")
                .any(AbstractNodeModifier.class, ((info, nodeMod) -> NodeModifierHelper.nodeModifiers.add(nodeMod)));
        initializing = false;
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
        BaseMod.loadCustomStringsFile(RelicStrings.class, "SpicyMapResources/localization/eng/RelicStrings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "SpicyMapResources/localization/eng/EventStrings.json");
    }


    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new RemovalRelic(), RelicType.SHARED);
    }
}
