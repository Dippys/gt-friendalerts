package xyz.barrawi.gtfriendalerts.events;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.messenger.Messenger;
import com.eu.habbo.habbohotel.messenger.MessengerBuddy;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.outgoing.generic.alerts.BubbleAlertComposer;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.users.UserDisconnectEvent;
import com.eu.habbo.plugin.events.users.UserLoginEvent;
import gnu.trove.map.hash.THashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FriendStatusEvent implements EventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(FriendStatusEvent.class);

    @EventHandler
    public static void onUserLogin(UserLoginEvent event) {
        try {
            if (!Emulator.getConfig().getBoolean("gtfriendalerts.login_alerts_enabled", true)) {
                return;
            }
            alertOnlineFriends(event.habbo, "gtfriendalerts.alert.login");
        } catch (Exception ex) {
            LOGGER.error("[GT FriendAlerts] Error while handling UserLoginEvent", ex);
        }
    }

    @EventHandler
    public static void onUserDisconnect(UserDisconnectEvent event) {
        try {
            if (!Emulator.getConfig().getBoolean("gtfriendalerts.logout_alerts_enabled", true)) {
                return;
            }
            alertOnlineFriends(event.habbo, "gtfriendalerts.alert.logout");
        } catch (Exception ex) {
            LOGGER.error("[GT FriendAlerts] Error while handling UserDisconnectEvent", ex);
        }
    }

    private static void alertOnlineFriends(Habbo habbo, String textKey) {
        if (habbo == null || habbo.getHabboInfo() == null) {
            return;
        }

        Messenger messenger = habbo.getMessenger();
        if (messenger == null || messenger.getFriends() == null) {
            return;
        }

        String message = Emulator.getTexts().getValue(textKey)
                .replace("%username%", habbo.getHabboInfo().getUsername());
        String bubbleKey = Emulator.getConfig().getValue("gtfriendalerts.bubble_key", "gtfriendalerts");
        String imageUrl = Emulator.getConfig().getValue("gtfriendalerts.imager_url",
                "https://imager.gthotel.org/?figure={figure}&headonly=1&direction=3&size=l")
                .replace("{figure}", habbo.getHabboInfo().getLook());

        THashMap<String, String> keys = new THashMap<>();
        keys.put("display", "BUBBLE");
        keys.put("image", imageUrl);
        keys.put("message", message);

        for (MessengerBuddy buddy : messenger.getFriends().values()) {
            Habbo friend = Emulator.getGameEnvironment().getHabboManager().getHabbo(buddy.getId());
            if (friend != null && friend.getClient() != null) {
                friend.getClient().sendResponse(new BubbleAlertComposer(bubbleKey, keys));
            }
        }
    }
}
