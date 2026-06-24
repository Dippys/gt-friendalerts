package xyz.barrawi.gtfriendalerts.events;

import com.eu.habbo.Emulator;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.barrawi.gtfriendalerts.Main;

public class EmulatorLoadEvent implements EventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmulatorLoadEvent.class);

    @EventHandler
    public static void onEmulatorLoaded(EmulatorLoadedEvent event) {
        try {
            loadConfig();
            registerTexts();
            Emulator.getPluginManager().registerEvents(Main.INSTANCE, new FriendStatusEvent());
            LOGGER.info("[GT FriendAlerts] Plugin loaded");
        } catch (Exception ex) {
            LOGGER.error("[GT FriendAlerts] Error while handling EmulatorLoadedEvent", ex);
        }
    }

    private static void loadConfig() {
        Emulator.getConfig().register("gtfriendalerts.login_alerts_enabled", "true");
        Emulator.getConfig().register("gtfriendalerts.logout_alerts_enabled", "true");
        Emulator.getConfig().register("gtfriendalerts.bubble_key", "gtfriendalerts");
        Emulator.getConfig().register("gtfriendalerts.imager_url",
                "https://imager.gthotel.org/?figure={figure}&headonly=1&direction=3&size=l");
    }

    private static void registerTexts() {
        Emulator.getTexts().register("gtfriendalerts.alert.login", "Your friend %username% just logged in!");
        Emulator.getTexts().register("gtfriendalerts.alert.logout", "Your friend %username% just logged out.");
    }
}
