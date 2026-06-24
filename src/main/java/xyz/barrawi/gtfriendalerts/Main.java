package xyz.barrawi.gtfriendalerts;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.HabboPlugin;

import xyz.barrawi.gtfriendalerts.events.EmulatorLoadEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends HabboPlugin implements EventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static Main INSTANCE = null;

    @Override
    public void onEnable() {
        try {
            INSTANCE = this;
            Emulator.getPluginManager().registerEvents(this, new EmulatorLoadEvent());
            LOGGER.info("[GT FriendAlerts] Plugin initialized");
        } catch (Exception e) {
            LOGGER.error("[GT FriendAlerts] Failed to initialize Plugin", e);
        }
    }

    @Override
    public void onDisable() {
        try {
            INSTANCE = null;
            LOGGER.info("[GT FriendAlerts] Plugin disabled.");
        } catch (Exception e) {
            LOGGER.error("[GT FriendAlerts] Failed to disable Plugin", e);
        }
    }

    public boolean hasPermission(Habbo habbo, String permission) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println("You can't run this separately.");
    }
}
