package net.runelite.client.plugins.ogPlugins.ogAgility;

import net.runelite.client.config.*;
import net.runelite.client.plugins.ogPlugins.ogAgility.enums.Rooftops;

@ConfigGroup("ogAgility")
public interface ogAgilityConfig extends Config {

    @ConfigSection(
            name = "Delays",
            description = "Delays",
            position = 0,
            closedByDefault = true
    )
    String delaySection = "delays";

    @ConfigSection(
            name = "AFK",
            description = "AFK",
            position = 1,
            closedByDefault = true
    )
    String afkSection = "afk";

    @ConfigSection(
            name = "Rooftop Course",
            description = "Rooftop Course",
            position = 3,
            closedByDefault = false
    )
    String rooftopCourseSection = "Rooftop Course";



    @ConfigSection(
            name = "Settings",
            description = "Settings",
            position = 5,
            closedByDefault = false
    )
    String settingSection = "settings";


    //Delays
    @ConfigItem(
            keyName = "Delay Min",
            name = "Delay Min",
            description = "Sets minimal delay",
            position = 0,
            section = delaySection
    )
    @Range(
            min = 40,
            max = 70
    )
    @Units(Units.MILLISECONDS)
    default int delayMin() {return 40;}

    @ConfigItem(
            keyName = "Delay Max",
            name = "Delay Max",
            description = "Sets maximum delay",
            position = 1,
            section = delaySection
    )
    @Range(
            min = 80,
            max = 130
    )
    @Units(Units.MILLISECONDS)
    default int delayMax() {return 80;}
    @ConfigItem(
            keyName = "Delay Deviation Chance",
            name = "Delay Deviation Chance",
            description = "The chance for it to go out of your provided ranges",
            position = 2,
            section = delaySection
    )
    @Range(
            min = 30,
            max = 50
    )
    default int delayChance() {return 30;}

    //AFK
    @ConfigItem(
            keyName = "AFK Min",
            name = "AFK Min",
            description = "Sets minimal afk",
            position = 0,
            section = afkSection
    )
    @Range(
            min = 0,
            max = 2
    )
    @Units(Units.MINUTES)
    default int afkMin() {return 0;}

    @ConfigItem(
            keyName = "AFK Max",
            name = "AFK Max",
            description = "Sets maximum afk",
            position = 1,
            section = afkSection
    )
    @Range(
            min = 3,
            max = 5
    )
    @Units(Units.MINUTES)
    default int afkMax() {return 3;}
    @ConfigItem(
            keyName = "AFK Chance",
            name = "AFK Chance",
            description = "How often do you want AFKs to happen? 1000 = Almost No AFK",
            position = 2,
            section = afkSection
    )
    @Range(
            min = 250,
            max = 1000
    )
    default int afkChance() {return 500;}


    //Rooftop Course Section
    @ConfigItem(
            keyName = "Rooftop Course",
            name = "Rooftop Course",
            description = "Choose the Rooftop Course?",
            position = 1,
            section = rooftopCourseSection
    )
    default Rooftops selectedCourse() {return Rooftops.SEERS_VILLAGE;}

    //Settings
    @ConfigItem(
            keyName = "Verbose Logging?",
            name = "Verbose Logging?",
            description = "Verbose Logging?",
            position = 1,
            section = settingSection
    )
    default boolean verboseLogging() {return false;}



}
