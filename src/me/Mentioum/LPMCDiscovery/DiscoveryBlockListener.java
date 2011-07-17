package me.Mentioum.LPMCDiscovery;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

class DiscoveryBlockListener extends BlockListener {
    public static LPMCDiscovery plugin;
    public DiscoveryBlockListener(LPMCDiscovery instance){
        plugin = instance;
    }

    @Override
    public void onBlockPlace (BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        
        if (block.getType() == Material.DIRT && plugin.enabled(player)){
            player.sendMessage("You dirty boy you!");
        }
        
    }
    
}
