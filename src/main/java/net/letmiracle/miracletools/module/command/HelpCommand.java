package net.letmiracle.miracletools.module.command;

import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.settings.Lang;

@AutoRegister
public final class HelpCommand extends SimpleCommand {
    public HelpCommand() {
        super("help");
    }

    @Override
    protected void onCommand() {
        tellNoPrefix(Lang.component("help-message"));
    }
}
