package SpireLocations;

import SpireLocations.nodemodifiers.*;
import SpireLocations.patches.NodeModifierField;
import SpireLocations.relic.RemovalRelic;
import basemod.*;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.io.IOException;
import java.util.Properties;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class SpireLocationsMod implements PostInitializeSubscriber, OnStartBattleSubscriber, EditStringsSubscriber, EditRelicsSubscriber {

    public static final String modID = "spirelocations";


    //config stuff
    public static Properties spireLocationsDefault = new Properties();
    public static SpireConfig spireLocationsConfig = null;
    public static String NODE_SCALE_CONFIG = "NODE_SCALE";

    public static float mapNodeScaling = 1.3f;




    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public SpireLocationsMod() {
        BaseMod.subscribe(this);


        //config setup in the file's constructor
        spireLocationsDefault.put(NODE_SCALE_CONFIG, 1.3f);
        try {
            spireLocationsConfig = new SpireConfig("spirelocations","spireLocationsConfig", spireLocationsDefault);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            spireLocationsConfig.load();
            mapNodeScaling = spireLocationsConfig.getFloat(NODE_SCALE_CONFIG);
        } catch (Exception e) {
            e.printStackTrace();
        }
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



    public static boolean initializing = true;
    @Override
    public void receivePostInitialize() {
        new AutoAdd(modID)
                .packageFilter("SpireLocations.nodemodifiers")
                .any(AbstractNodeModifier.class, ((info, nodeMod) -> NodeModifierHelper.nodeModifiers.add(nodeMod)));
        initializing = false;


        //config screen in receivePostInitialize
        ModPanel modPanel = new ModPanel();

        ModLabel label = new ModLabel("Map node scaling", 450.0f, 700f, Settings.CREAM_COLOR, FontHelper.charDescFont, modPanel, modLabel -> {});
        float yOffset = FontHelper.getWidth(FontHelper.charDescFont,"Map node scaling", Settings.scale);
        ModMinMaxSlider slider = new ModMinMaxSlider("",450.0f + yOffset + 25f,700f,
                0f,200f, mapNodeScaling, "%.0f %%", modPanel,
                (modSlider) -> {
                    mapNodeScaling = modSlider.getValue();
                    if (spireLocationsConfig != null) {
                        spireLocationsConfig.setFloat(NODE_SCALE_CONFIG, modSlider.getValue());
                        saveConfig(spireLocationsConfig);
                    }

                });
        slider.setValue(mapNodeScaling);
        modPanel.addUIElement(label);
        modPanel.addUIElement(slider);

        BaseMod.registerModBadge(ImageMaster.loadImage("SpireLocationsResources/images/ui/Badge.png"),
                "Spire Locations", "Pandemonium", "This is a description", modPanel);
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

    public void saveConfig(SpireConfig config) {
        try {
            config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
