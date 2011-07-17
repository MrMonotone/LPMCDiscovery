
package me.Mentioum.LPMCDiscovery;
import com.mini.Arguments;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class Players extends PlayerListener {
    public static LPMCDiscovery plugin;
    public Players(LPMCDiscovery instance) {
        plugin = instance;
    }

    
    
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        if(player != null) {
            Arguments lastPlayerEntry = plugin.database.getArguments(player.getDisplayName()); //reads the last entry for the "getDisplayName" and sets it to "lastPlayerEntry" for easy calling.
            
            if (lastPlayerEntry != null){ //if the lasPlayerEntry exists (i.e if they have logged in before then...
                String lastPlayerLocation = "Last Player Co-ordinates: X: " + lastPlayerEntry.getValue("x") + "Y " + lastPlayerEntry.getValue("y") + "Z " + lastPlayerEntry.getValue("z"); //get the following data information from their entry and set it as "lastPlaterLocation"
                player.sendMessage(lastPlayerLocation); //send the player their last login location.
            }
            Arguments entry = new Arguments(player.getDisplayName());
            Location current = player.getLocation();
            entry.setValue("x", String.valueOf(current.getX()));
            entry.setValue("y", String.valueOf(current.getY()));
            entry.setValue("z", String.valueOf(current.getZ()));

            plugin.database.addIndex(entry.getKey(), entry);
            
            plugin.database.update();
            
        }
    }

}