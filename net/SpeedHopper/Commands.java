/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.SpeedHopper;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Admin
 */
public class Commands implements CommandExecutor {
    public Plugin plugin=main.getPlugin();
    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String alias, String[] args) {
        String prefix=plugin.getConfig().getString("prefix");
        String nopermission=plugin.getConfig().getString("messages.nopermission");
        if(sender instanceof Player){
            Player p=(Player) sender;
            if(!p.hasPermission("speedhopper.help")){
                p.sendMessage(prefix+" "+nopermission);
                return true;
            }
        }
        if(args.length<=0){
            helpcommand(sender);
        }
        else{
            if(args.length==1){
                if(args[0].toLowerCase().equals("reload")){
                    if(sender instanceof Player){
                        Player p=(Player) sender;
                        if(!p.hasPermission("speedhopper.reload")){
                            p.sendMessage(prefix+" "+nopermission);
                            return true;
                        }
                    }
                    reloadcommand(sender);
                    
                }
                else{
                    helpcommand(sender);
                }
            }
            else{
                helpcommand(sender);
            }
        }
        return true;
    }
    public void helpcommand(CommandSender sender){
        List<String> help=plugin.getConfig().getStringList("messages.help");
        help.forEach((s) -> {
            sender.sendMessage(s);
        });
    }
    public void reloadcommand(CommandSender sender){
        plugin.reloadConfig();
        plugin.saveDefaultConfig();
        main.changemessages();
        sender.sendMessage(plugin.getConfig().getString("prefix")+" "+plugin.getConfig().getString("messages.sucessreload"));
    }
    
}
