package me.Ayush_03.PoroticWarps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener {
	
	PoroticWarps plugin;
	public Events(PoroticWarps plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	 public void onClick(InventoryClickEvent event)
	  {
	    Player player = (Player)event.getWhoClicked();
	    Inventory inventory = event.getInventory();
	    
	    if (!(inventory.getTitle() ==  "§4§lPorotic Warp Menu")) {
	    	return;
	    }
	    
	    event.setCancelled(true);
	    
	    if (event.getAction() != InventoryAction.PICKUP_ALL) {
	        return;
	      }
	    
	    ItemStack stack = event.getCurrentItem();

	    if (stack == null) {
	    	
	      return;
	    }

	    if (!stack.hasItemMeta()) {
	    	
	      return;
	    }

	    ItemMeta meta = stack.getItemMeta();

	    if (!meta.hasDisplayName()) {
	      return;
	    }

	    String warp = ChatColor.stripColor(meta.getDisplayName());
	    Bukkit.getServer().dispatchCommand(player, "pwarp " + warp);
	  }
	

}

