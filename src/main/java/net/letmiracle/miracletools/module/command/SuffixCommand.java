package net.letmiracle.miracletools.module.command;

import net.letmiracle.miracletools.settings.Settings;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.SuffixNode;
import org.bukkit.entity.Player;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.model.SimpleComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AutoRegister
public final class SuffixCommand extends SimpleCommand {

    public SuffixCommand() {
        super("suffix", Arrays.asList("suff"));
        setPermissionMessage(SimpleComponent.fromMiniAmpersand("Для использования этой команды необходимо приобрести как минимум один суффикс"));
    }

    public void setSuffix(Player player, String suffix, int priority) {
        LuckPerms api = LuckPermsProvider.get();

        User user = api.getPlayerAdapter(Player.class).getUser(player);
        user.data().clear(NodeType.SUFFIX::matches);
        user.data().add(SuffixNode.builder(suffix, priority).build());
        api.getUserManager().saveUser(user);
        tellSuccess("Суффикс " + args[0].toLowerCase() + " успешно установлен");
    }

    @Override
    protected void onCommand() {
        checkConsole();
        checkPerm("miracletools.command.suffix");
        checkArgs(1, "Укажите суффикс");

        String param = args[0].toLowerCase();
        Player player = (Player) getSender();

        List<String> suffixesFromConfig = new ArrayList<String>(Settings.SampleSection.LIST.keySet());

        if (!suffixesFromConfig.contains(param)) {
            returnInvalidArgs("Суффикс не найден");

            return;
        }

        String format = Settings.SampleSection.WHITESPACE
                ? " " + Settings.SampleSection.LIST.get(param).getValues(true).get("Format")
                : String.valueOf(Settings.SampleSection.LIST.get(param).getValues(true).get("Format"));

        String permission = String.valueOf(Settings.SampleSection.LIST.get(param).getValues(true).get("Permission"));

        checkPerm(permission);

        setSuffix(player, format, 1);
    }

    @Override
    protected List<String> tabComplete() {
        if (args.length == 1) {
            ArrayList<String> suffixList = new ArrayList<>(Settings.SampleSection.LIST.keySet());
            ArrayList<String> resultList = new ArrayList<>();

            for (String suffix : suffixList) {
                String permission = "miracletools.suffix." + suffix;

                if (getSender().hasPermission(permission)) {
                    resultList.add(suffix);
                }
            }

            return completeLastWord(resultList);
        }

        return super.tabComplete();
    }
}
