package cn.hellp.hpwriterkyle.hellppedalstance;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class HellpPedalStance extends JavaPlugin {
    public static PluginInitial pi;
    private static Map<PedalType, Boolean> enabledMap = new HashMap<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        pi = new PluginInitial(this);
        pi.onEnable();
        pi.loadConfig();
        registerListeners();
        registerCommanders();
        loadData();
    }

    public static void loadData(){
        for(String s:PluginInitial.plugin.getConfig().getConfigurationSection("default.enabled").getKeys(false)){
            enabledMap.put(PedalType.spawn(s),PluginInitial.plugin.getConfig().getBoolean("default.enabled."+s));
        }
    }

    public static void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new MainListener(),PluginInitial.plugin);
    }

    public static void registerCommanders(){
        PluginInitial.plugin.getCommand("hellppedalstance").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        pi.onDisable();
    }

    public enum PedalType{
        PEDAL_WALL("pedal-wall");

        String originName;
        PedalType(String s){
            this.originName = s;
        }

        boolean isEnabled(){
            return enabledMap.getOrDefault(this,false);
        }

        static PedalType spawn(String originName){
            return PedalType.valueOf(originName.toUpperCase().replace("-","_"));
        }
    }

    public static String Auto(String s){
        return ChatColor.translateAlternateColorCodes('&',s);
    }
}
