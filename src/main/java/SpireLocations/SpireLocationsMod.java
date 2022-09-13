package SpireLocations;

import SpireLocations.nodemodifiers.*;
import SpireLocations.patches.NodeModifierField;
import SpireLocations.relic.RemovalRelic;
import basemod.*;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.DisplayConfig;
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
    public static String BONUS_MOD_CONFIG = "ADDITIONAL_MODIFIER_PROB";
    public static String DISABLE_SPECIFIC_EVENT ="DISABLE_SPECIFIC_EVENT_MODIFIER";

    public static float mapNodeScaling = 1.3f;
    public static int bonusModifierProb = 0;
    public static boolean disableSpecificEvent = false;




    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public SpireLocationsMod() {
        BaseMod.subscribe(this);


        //config setup in the file's constructor
        spireLocationsDefault.put(NODE_SCALE_CONFIG, 1.3f);
        spireLocationsDefault.put(BONUS_MOD_CONFIG, 0);
        spireLocationsDefault.put(DISABLE_SPECIFIC_EVENT, false);
        try {
            spireLocationsConfig = new SpireConfig("spirelocations","spireLocationsConfig", spireLocationsDefault);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            spireLocationsConfig.load();
            mapNodeScaling = spireLocationsConfig.getFloat(NODE_SCALE_CONFIG);
            bonusModifierProb = spireLocationsConfig.getInt(BONUS_MOD_CONFIG);
            disableSpecificEvent = spireLocationsConfig.getBool(DISABLE_SPECIFIC_EVENT);
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
                .any(AbstractNodeModifier.class, ((info, nodeMod) -> NodeModifierHelper.registerModifier(nodeMod)));
        initializing = false;


        //config screen in receivePostInitialize
        ModPanel modPanel = new ModPanel();

        //Node scale config
        ModLabel nodeScaleLabel = new ModLabel("Map node scaling", 450.0f, 700f, Settings.CREAM_COLOR, FontHelper.charDescFont, modPanel, modLabel -> {});
        float yOffsetNodeScale = FontHelper.getWidth(FontHelper.charDescFont,"Map node scaling", Settings.scale);
        ModMinMaxSlider nodeScaleSlider = new ModMinMaxSlider("",450.0f + yOffsetNodeScale + 25f,700f,
                0f,200f, mapNodeScaling, "%.0f %%", modPanel,
                (modSlider) -> {
                    mapNodeScaling = modSlider.getValue();
                    if (spireLocationsConfig != null) {
                        spireLocationsConfig.setFloat(NODE_SCALE_CONFIG, modSlider.getValue());
                        saveConfig(spireLocationsConfig);
                    }

                });
        nodeScaleSlider.setValue(mapNodeScaling);
        modPanel.addUIElement(nodeScaleLabel);
        modPanel.addUIElement(nodeScaleSlider);

        //Bonus probability config
        ModLabel bonusProbLabel = new ModLabel("Additional modifier spawn chance", 450.0f, 650f, Settings.CREAM_COLOR, FontHelper.charDescFont, modPanel, modLabel -> {});
        float yOffsetBonusProb = FontHelper.getWidth(FontHelper.charDescFont,"Additional modifier spawn chance", Settings.scale);
        ModMinMaxSlider bonusProbSlider = new ModMinMaxSlider("",450.0f + yOffsetBonusProb + 25f,650f,
                -20f,20f, mapNodeScaling, "%.0f %%", modPanel,
                (modSlider) -> {
                    bonusModifierProb = (int)modSlider.getValue();
                    if (spireLocationsConfig != null) {
                        spireLocationsConfig.setInt(BONUS_MOD_CONFIG, bonusModifierProb);
                        saveConfig(spireLocationsConfig);
                    }

                });
        bonusProbSlider.setValue(bonusModifierProb);
        modPanel.addUIElement(bonusProbLabel);
        modPanel.addUIElement(bonusProbSlider);

        //Disable SpecificEventModifier config

        ModLabeledToggleButton disableSpecificEventButton = new ModLabeledToggleButton("Disable the Useful Map node modifier (requires a restart)",
                450.0f, 600.0f, Settings.CREAM_COLOR, FontHelper.charDescFont, // Position (trial and error it), color, font
                disableSpecificEvent, // Boolean it uses
                modPanel, // The mod panel in which this button will be in
                (label) -> {}, // thing??????? idk
                (button) -> { // The actual button:

                    disableSpecificEvent = button.enabled; // The boolean true/false will be whether the button is enabled or not
                    try {
                        spireLocationsConfig.setBool(DISABLE_SPECIFIC_EVENT, disableSpecificEvent);
                        spireLocationsConfig.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        modPanel.addUIElement(disableSpecificEventButton);



        BaseMod.registerModBadge(ImageMaster.loadImage("SpireLocationsResources/images/ui/Badge.png"),
                "Spire Locations", "Pandemonium", "Adds modifiers to nodes on the map. Config includes node scale, modifier spawn chance and disabling some modifiers, for stability purposes", modPanel);
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
