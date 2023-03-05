package cn.hellp.hpwriterkyle.hellppedalstance;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,  Command command, String label, String[] args) {
        if(args.length>0){
            switch (args[0]){
                case "reload":
                    PluginInitial.plugin.reloadConfig();
                    HellpPedalStance.loadData();
                    sender.sendMessage(HellpPedalStance.Auto("&a插件已重载"));
            }
        }
        return false;
    }
}
