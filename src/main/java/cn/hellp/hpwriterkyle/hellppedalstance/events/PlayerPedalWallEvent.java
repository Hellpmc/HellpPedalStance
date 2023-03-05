package cn.hellp.hpwriterkyle.hellppedalstance.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.util.BlockVector;

import java.util.*;

public class PlayerPedalWallEvent extends PlayerEvent implements Cancellable {
    public static HandlerList handlerList = new HandlerList();
    private Location location;
    private int endurance;
    private boolean cancelled;
    public static List<UUID> flyersMap = new ArrayList<>();
    public static List<UUID> pedaller = new ArrayList<>();

    public PlayerPedalWallEvent(Player who,Location pedalLocation,int endurance) {
        super(who);
        this.location = pedalLocation;
        this.endurance = endurance;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    @SuppressWarnings("bugged")
    public void work(){
        if(!flyersMap.contains(getPlayer().getUniqueId())){
            flyersMap.add(getPlayer().getUniqueId());
        }
        if(!getPlayer().getAllowFlight()){
            flyersMap.remove(getPlayer().getUniqueId());
        }
        getPlayer().setAllowFlight(true);
        Bukkit.getPluginManager().callEvent(this);
        if(isCancelled()){
            return;
        }
        pedaller.add(getPlayer().getUniqueId());
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
