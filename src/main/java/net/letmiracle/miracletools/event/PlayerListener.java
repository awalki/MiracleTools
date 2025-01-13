package net.letmiracle.miracletools.event;

import net.letmiracle.miracletools.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Messenger;
import org.mineacademy.fo.settings.Lang;

import java.util.List;

/**
 * A simple listener for player actions.
 */
public final class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerInteraction(PlayerInteractEntityEvent event) {
        final Player player = event.getPlayer();
        final String playerName = event.getRightClicked().getName();

        if (!(event.getRightClicked() instanceof Player)) return;
        List<String> blacklistedPlayers = Settings.HideNameTag.BLACKLIST;
        String message = !blacklistedPlayers.contains(playerName)
                ? Lang.dictionary().get("actionbar-message").getAsString().replace("{player}", playerName)
                : Lang.dictionary().get("actionbar-message-blacklisted").getAsString().replace("{player}", playerName);

        Common.tell(player, "<actionbar>" + message);

    }

    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        String playerName = event.getPlayer().getName();
        String block = event.getBlock().getType().toString();

        List<String> blocks = Settings.Observer.BLOCKS;

        String message = Lang.dictionary()
                .get("observer-message")
                .getAsString()
                .replace("{player}", playerName)
                .replace("{block}", block);

        if (!blocks.contains(block)) return;
        if (player.hasPermission("miracletools.observer.listen")) return;

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("miracletools.observer.disable")) continue;
            if (!p.hasPermission("miracletools.observer.listen")) continue;

            Messenger.announce(p, message);
        }
    }
}

