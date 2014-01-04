/*code by sickray34s (A.K.A) Kevin Connelly
 * 1.7.4 version 
 * beta stage
 */
package com.sickray34s;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;




public class DropPartyLogic extends JavaPlugin implements Listener {

 
	 
	

		public void onEnable(){
			getLogger().info("onEnable has been invoked!");	  
			Bukkit.getServer().getPluginManager().registerEvents(this, this);
		}
		public void onDisable(){
			getLogger().info("onDisable has been invoked!");
		}
		
		
			
			
		@EventHandler
		public void onPlayerUse(PlayerInteractEvent event){
		    
		if(event.getAction().equals(Action.LEFT_CLICK_AIR)){
				if(event.getPlayer().getItemInHand().getType().equals(Material.BLAZE_ROD) && event.getPlayer().hasPermission("dps.wand")){
					Location loc = event.getPlayer().getLocation();
			    	  World world = event.getPlayer().getWorld();
					  Material[] mat = {Material.GOLDEN_APPLE, Material.DIAMOND, Material.GOLD_INGOT, Material.EYE_OF_ENDER, Material.OBSIDIAN, Material.EMERALD, Material.IRON_BLOCK};
					  ItemStack item = new ItemStack(mat[(int) (Math.random() * mat.length )], 1);
					  Item postitem = world.dropItem(loc, item);
					  event.getPlayer().getWorld().playSound(loc,Sound.NOTE_SNARE_DRUM,1, 0);
		              postitem.setVelocity(loc.getDirection().multiply(1.7));
		         
		    }
		    }
		}

}
