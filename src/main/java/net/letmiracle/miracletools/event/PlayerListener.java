package net.letmiracle.miracletools.event;

import net.letmiracle.miracletools.settings.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Messenger;
import org.mineacademy.fo.settings.Lang;

import java.util.ArrayList;
import java.util.List;

/**
 * A sample listener for events.
 */
public final class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerInteraction(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            final Player player = event.getPlayer();
            final String playerName = event.getRightClicked().getName();
            final String message;

            List<String> blacklistedPlayers = Settings.HideNameTag.BLACKLIST;

            if (blacklistedPlayers.contains(playerName)) {
                message = Lang.dictionary().get("actionbar-message-blacklisted").getAsString().replace("{player}", playerName);
            } else {
                message = Lang.dictionary().get("actionbar-message").getAsString().replace("{player}", playerName);
            }

            Common.tell(player, "<actionbar>" + message);
        }
    }
}

