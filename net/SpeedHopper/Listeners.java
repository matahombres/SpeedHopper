/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.SpeedHopper;

import java.util.HashMap;
import org.bukkit.block.Hopper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Admin
 */
public class Listeners implements Listener{
    public Plugin plugin=main.getPlugin();
    public static HashMap<String, Integer> UUIDminecarts= new HashMap<String, Integer>();
    @EventHandler(priority = EventPriority.HIGHEST)
    public void transferhopper(InventoryMoveItemEvent e){
        Inventory desti=e.getDestination();
        if(!desti.getType().equals(InventoryType.HOPPER)){
            return;
        }
        String es;
        //Para detectar si es una minecart tolva o si es una tolva normal
        //funciona como un if, pero me aprovecho de que al comprobar si el inventario
        //es un hopper. Solo hay 2 tipos de hopper y son las minecart hopper y las
        //hoppers normales. Por lo que si no es una, es la otra
        try{
            //Minecart tolva
            HopperMinecart hopper=(HopperMinecart) e.getDestination().getHolder();
            es="minecart";
        }
        catch(Exception exception){
            //Hopper
            es="hopper";
        }
        if(e.getSource().getType() == null){
            return;
        }
        if("minecart".equals(es)){
            HopperMinecart hopper=(HopperMinecart) e.getDestination().getHolder();
            String uuid=String.valueOf(hopper.getUniqueId());
            int items=plugin.getConfig().getInt("tics");
            if(UUIDminecarts.get(uuid) == null){
                UUIDminecarts.put(uuid, 0);
            }
            else{
                int diferencia=main.tics-UUIDminecarts.get(uuid);
                if(main.tics<UUIDminecarts.get(uuid)){
                    UUIDminecarts.put(uuid, main.tics);
                }
                if(diferencia>=items){
                    UUIDminecarts.put(uuid, main.tics);
                }
                else{
                    e.setCancelled(true);
                }
            }
        }
        
    }
}
