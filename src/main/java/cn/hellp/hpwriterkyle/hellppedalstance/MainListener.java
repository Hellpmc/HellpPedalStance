package cn.hellp.hpwriterkyle.hellppedalstance;

import cn.hellp.hpwriterkyle.hellppedalstance.events.PlayerPedalWallEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.BlockVector;

import java.util.HashMap;
import java.util.HashSet;

public class MainListener implements Listener {
    @EventHandler
    public void onPedal(PlayerToggleFlightEvent e){
        if(PlayerPedalWallEvent.pedaller.contains(e.getPlayer().getUniqueId())){
            e.getPlayer().getVelocity().add(new BlockVector(0,1,0));
            e.getPlayer().setAllowFlight(PlayerPedalWallEvent.flyersMap.contains(e.getPlayer().getUniqueId()));
            PlayerPedalWallEvent.pedaller.remove(e.getPlayer().getUniqueId());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Block b = e.getPlayer().getTargetBlock((HashSet)null,1);
        if(b.isEmpty()){
            System.out.println("asdasas");
            PlayerPedalWallEvent.pedaller.remove(e.getPlayer().getUniqueId());
            return;
        }
        if(e.getPlayer().isFlying()){
            return;
        }
        if(!HellpPedalStance.PedalType.PEDAL_WALL.isEnabled()){
            return;
        }
        int endurance = 1; //not done
        new PlayerPedalWallEvent(e.getPlayer(),b.getLocation(),endurance).work();
    }
}
