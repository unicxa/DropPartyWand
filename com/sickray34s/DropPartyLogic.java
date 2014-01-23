/*code by sickray34s (A.K.A) Kevin Connelly
 * 1.7.4 version 
 * beta stage
 */
package com.sickray34s;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;




public class DropPartyLogic extends JavaPlugin implements Listener {
FileConfiguration config;
 HashMap<String , Material> alias = new HashMap<String , Material>();
	 
	

		public void onEnable(){
			getLogger().info("onEnable has been invoked!");	  
			
			alias.put("diamond", Material.DIAMOND);
			alias.put("goldblock", Material.GOLD_BLOCK);
			alias.put("blazerod",Material.BLAZE_ROD );
			alias.put("netherbrick",Material.NETHER_BRICK);
			alias.put("ironingot",Material.IRON_INGOT );
			alias.put("emerald",Material.EMERALD );
			alias.put("bedrock",Material.BED_BLOCK );
			alias.put("arrow",Material.ARROW );
			alias.put("coal",Material.COAL );
			alias.put("bakedpotato",Material.BAKED_POTATO );
			alias.put("enchantmenttable",Material.ENCHANTMENT_TABLE );
			alias.put("coal",Material.DIAMOND_ORE );
			alias.put("diamondblock",Material.DIAMOND_BLOCK);
			
			try{
				
				
				
				config = getConfig();
				File ItemsConfig = new File(getDataFolder() + "ItemsConfig.yml");
				if(!ItemsConfig.exists()){
				getDataFolder().mkdir();
				if(!config.contains("itemslist")){
					config.set("itemslist", "DIAMOND,GOLD_INGOT");
					
					config.options().copyDefaults(true);
		            saveConfig();
					}
				
				
				}
			}catch(Exception e1){
			e1.printStackTrace();
			}
			
			
			
			
			Bukkit.getServer().getPluginManager().registerEvents(this, this);
		}
		public void onDisable(){
			getLogger().info("onDisable has been invoked!");
		
		
		}
			
		static String[] shuffleArray(String[] strings)
		  {
		    Random rnd = new Random();
		    for (int i = strings.length - 1; i > 0; i--)
		    {
		      int index = rnd.nextInt(i + 1);
		      // Simple swap
		      String a = strings[index];
		      strings[index] = strings[i];
		      strings[i] = a;
		    }
		    return strings;
		  }
		
		@EventHandler
		public void onPlayerUse(PlayerInteractEvent event){
			 
           
				if(event.getAction().equals(Action.LEFT_CLICK_AIR)){
						
				if(event.getPlayer().getItemInHand().getType().equals(Material.BLAZE_ROD) && event.getPlayer().hasPermission("dps.wand")){
				  String[] items  = shuffleArray(config.getString("itemslist").toString().split(",")); 
				  Material chosen = null;
				
				  for(String item : items){
					  if(alias.containsKey(item)){
						  
						  chosen = alias.get(item);
						  
						  
					 }
					  else{chosen = Material.getMaterial(item.toUpperCase());
					  
					  
					  
					  }
					 if(chosen == null){event.getPlayer().sendMessage("item format error:" + item);}
					 else break;
				  }
				
				   ItemStack item = new ItemStack(chosen,1);
							
							
							
				
					  Location loc = event.getPlayer().getLocation();
			    	  World world = event.getPlayer().getWorld();
			    	  
					  Item postitem = world.dropItem(loc, item);
					  event.getPlayer().getWorld().playSound(loc,Sound.NOTE_SNARE_DRUM,1, 0);
		              postitem.setVelocity(loc.getDirection().multiply(1.7));
		         
		    }}
					
					}
		    }
		

