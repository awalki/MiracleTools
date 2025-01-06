package net.letmiracle.miracletools.module.command;

import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.*;

import java.util.Arrays;

@AutoRegister
public final class MiracleToolsCommandGroup extends SimpleCommandGroup {

    public MiracleToolsCommandGroup() {
        super("miracletools", Arrays.asList("mt"));
    }

    @Override
    protected void registerSubcommands() {
        this.registerDefaultSubcommands();
    }

    @Override
    protected String getCredits() {
        return "Как ты сюда попал? Работай с лучшими\n <click:open_url:'https://discord.gg/mnFwKfPAP2'><color:#214eff><b>Наш дискорд</b></color></click>";
    }

    @Override
    protected String getHeaderPrefix() {
        return "<b><gradient:#7AD671:#F9FDAD>";
    }
}
