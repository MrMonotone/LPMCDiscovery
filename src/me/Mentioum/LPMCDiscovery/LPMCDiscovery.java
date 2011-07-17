package me.Mentioum.LPMCDiscovery;

//Imports

import com.mini.Mini;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public class LPMCDiscovery extends JavaPlugin {
    public static LPMCDiscovery plugin;
    public static final Logger logger = Logger.getLogger("Minecraft");
    private DiscoveryBlockListener blocklistener = new DiscoveryBlockListener(this); //Defines Block Listener
    public HashMap<Player, ArrayList<Block>> discoveryUsers = new HashMap(); //Defines HashMap
    public HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>(); //Defines HashMap Debugees
    public static String mainDirectory = "plugins/LPMCDiscovery";//Set main directory for easy reference
    private PluginManager manager;
    private File directory;
    public Mini database;
    
   
    
    
    @Override
    public void onDisable() {
        PluginManager pm = getServer().getPluginManager();
        PluginDescriptionFile pdfFile = this.getDescription();
        LPMCDiscovery.logger.info( pdfFile.getName() + " V." + pdfFile.getVersion() + " by " + pdfFile.getAuthors() + " is disabled");
    }

    @Override
    public void onEnable() {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvent(Type.BLOCK_PLACE, this.blocklistener, Priority.Normal, this);
    PluginDescriptionFile pdfFile = this.getDescription();
    LPMCDiscovery.logger.info( pdfFile.getName() + " V" + pdfFile.getVersion() + " by " + pdfFile.getAuthors() + " is enabled");
    directory = getDataFolder();
    database = new Mini(directory.getPath(), "playerlocations.mini");
    
    pm.registerEvent(Type.PLAYER_JOIN, new PlayerJoin(this), Priority.Low, this);
    
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if (commandLabel.equalsIgnoreCase("discovery")
                || commandLabel.equalsIgnoreCase("disc")){
            toggleVision ((Player) sender);
            return true;
        }
            return false;
    }
    
    public void toggleVision(Player player){
        if (enabled(player)) {
            this.discoveryUsers.remove(player);
            player.sendMessage("Discovery Disabled");
        } else {
            this.discoveryUsers.put(player, null);
            player.sendMessage("Discovery Enabled");
        }
        
    }   
    
    public boolean isDebugging(Player player){
        if (debugees.containsKey(player)){
            return debugees.get(player);
        } else {
            return false;
        }
        
    }
    
    //Below checks to see if the player is in the HashMap
    public void setDebugging (Player player, boolean value) {
        debugees.put(player, value);
    }
    
    public boolean enabled(Player player){
        return this.discoveryUsers.containsKey(player);
        }
    

}