package net.runelite.client.plugins.ogPlugins.ogAgility;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;

@PluginDescriptor(
        name = PluginDescriptor.OG + "Agility",
        description = "OG Agility plugin",
        tags = {"og","agility", "microbot"},
        enabledByDefault = false
)
@Slf4j
public class ogAgilityPlugin extends Plugin {
    @Inject
    private ogAgilityConfig config;
    @Provides
    ogAgilityConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(ogAgilityConfig.class);
    }

    @Inject
    private OverlayManager overlayManager;
    @Inject
    private ogAgilityOverlay ogAgilityOverlay;

    @Inject
    ogAgilityScript ogAgilityScript;

    @Override
    protected void startUp() throws AWTException {
        if (overlayManager != null) {
            overlayManager.add(ogAgilityOverlay);
        }
        ogAgilityScript.run(config);
    }

    protected void shutDown() {
        ogAgilityScript.shutdown();
        overlayManager.remove(ogAgilityOverlay);
    }
}
