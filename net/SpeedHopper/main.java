/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.SpeedHopper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 *
 * @author Admin
 */
public class main extends JavaPlugin{
    private static main plugin;
    public static int tics;
    public static main getPlugin(){
        return plugin;
    }
    /**
     *
     */
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(translatecolor("&6&l[&4&lSpeed&f&lHopper&6&l] &3Enable SpeedHopper"));
        plugin=this;
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        this.getCommand("speedhopper").setExecutor(new Commands());
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if(tics==24.000){
                    tics=0;
                    tics++;
                }
                else{
                    tics++;
                }
            }
        }, 0L, 1L);
        defaultconfig();
        changemessages();
    }
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(translatecolor("&6&l[&4&lSpeed&f&lHopper&6&l] &3Disabled SpeedHopper"));
    }
    public static String translatecolor(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static void defaultconfig(){
        plugin.getConfig().addDefault("tics", 10);
        plugin.getConfig().addDefault("prefix", translatecolor("&6&l[&4&lSpeed&f&lHopper&6&l]"));
        plugin.getConfig().addDefault("messages.help", Arrays.asList(
                translatecolor("&7&m------[&4&l Speed&f&lHopper &7&m]------"),
                translatecolor("&9/shopper reload &f: Reload config")
        ));
        plugin.getConfig().addDefault("messages.nopermission", translatecolor("&cOPS!&f You don't have permission for this"));
        plugin.saveDefaultConfig();
    }
    public static void changemessages(){
        plugin.getConfig().set("prefix", translatecolor(plugin.getConfig().getString("prefix")));
        plugin.getConfig().set("messages.help", listreturn("messages.help"));
        plugin.getConfig().set("messages.nopermission", translatecolor(plugin.getConfig().getString("messages.nopermission")));
        plugin.getConfig().set("messages.sucessreload", translatecolor(plugin.getConfig().getString("messages.sucessreload")));
    }
    
    public static List<String> listreturn(String route){
        List<String> list2=plugin.getConfig().getStringList(route);
        List<String> list=new ArrayList<>();
        list2.forEach((l) -> {
            list.add(translatecolor(l));
        });
        return list;
    }
}
