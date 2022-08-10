package SpireLocations;

import SpireLocations.nodemodifiers.*;
import SpireLocations.patches.NodeModifierField;
import SpireLocations.relic.RemovalRelic;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class SpireLocationsMod implements PostInitializeSubscriber, OnStartBattleSubscriber, EditStringsSubscriber, EditRelicsSubscriber {

    public static final String modID = "spirelocations";

    public static boolean initializing = true;

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public SpireLocationsMod() {
        BaseMod.subscribe(this);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static void initialize() {
        SpireLocationsMod thismod = new SpireLocationsMod();
    }

    @Override
    public void receivePostInitialize() {
        new AutoAdd(modID)
                .packageFilter("SpireLocations.nodemodifiers")
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
        BaseMod.loadCustomStringsFile(UIStrings.class, "SpireLocationsResources/localization/eng/UIStrings.json" );
        BaseMod.loadCustomStringsFile(RelicStrings.class, "SpireLocationsResources/localization/eng/RelicStrings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "SpireLocationsResources/localization/eng/EventStrings.json");
    }


    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new RemovalRelic(), RelicType.SHARED);
    }
}
