package net.letmiracle.miracletools.module.command;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleSubCommand;

import java.util.List;

public class ToggleObserverSubCommand extends SimpleSubCommand {
    protected ToggleObserverSubCommand() {
        super("observer");
        setPermission("miracletools.observer.listen");
    }

    @Override
    protected void onCommand() {
        LuckPerms api = LuckPermsProvider.get();
        Player player = (Player) getSender();
        User user = api.getPlayerAdapter(Player.class).getUser((Player) getSender());

        String param = args[0].toLowerCase();

        if ("on".equals(param)) {
            api.getUserManager().modifyUser(player.getUniqueId(), user1 -> {
                user.data().remove(Node.builder("miracletools.observer.disable").build());

                tellInfo("Наблюдатель включен");
            });
        } else if ("off".equals(param)) {
            api.getUserManager().modifyUser(player.getUniqueId(), user1 -> {
                user.data().add(Node.builder("miracletools.observer.disable").build());

                tellInfo("Наблюдатель отключен");
            });
        }
    }

    @Override
    protected List<String> tabComplete() {
        if (args.length == 1) {
            return completeLastWord("on", "off");
        }

        return super.tabComplete();
    }
}
