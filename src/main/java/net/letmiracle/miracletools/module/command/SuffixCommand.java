package net.letmiracle.miracletools.module.command;

import net.letmiracle.miracletools.settings.Settings;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.SuffixNode;
import org.bukkit.entity.Player;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.SimpleCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AutoRegister
public final class SuffixCommand extends SimpleCommand {
    public SuffixCommand() {
        super("suffix", Arrays.asList("suff"));
    }

    public void setSuffix(Player player, String suffix, int priority) {
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
        user.data().clear();
        user.data().add(SuffixNode.builder(suffix, priority).build());
        LuckPermsProvider.get().getUserManager().saveUser(user);
        this.tellSuccess("Суффикс " + this.args[0].toLowerCase() + " успешно установлен");
    }

    @Override
    protected void onCommand() {
        this.checkConsole();
        this.checkPerm("miracletools.command.suffix");
        this.checkArgs(1, "Укажите суффикс");

        String param = this.args[0].toLowerCase();
        Player player = (Player) this.getSender();

        List<String> suffixesFromConfig = new ArrayList<String>(Settings.SampleSection.LIST.keySet());

        if (suffixesFromConfig.contains(param)) {
            // Check if WHITESPACE is enabled
            String format = Settings.SampleSection.WHITESPACE
                    ? " " + Settings.SampleSection.LIST.get(param).getValues(true).get("Format")
                    : String.valueOf(Settings.SampleSection.LIST.get(param).getValues(true).get("Format"));

            String permission = String.valueOf(Settings.SampleSection.LIST.get(param).getValues(true).get("Permission"));

            this.checkPerm(permission);

            setSuffix(player, format, 1);
        } else
            this.returnInvalidArgs("Суффикс не найден");
    }

    @Override
    protected List<String> tabComplete() {
        if (this.args.length == 1) {

            ArrayList<String> suffixList = new ArrayList<String>(Settings.SampleSection.LIST.keySet());
            return this.completeLastWord(suffixList);
        }

        return super.tabComplete();
    }
}
