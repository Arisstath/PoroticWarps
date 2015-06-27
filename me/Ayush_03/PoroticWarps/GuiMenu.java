package me.Ayush_03.PoroticWarps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiMenu {
	
	PoroticWarps plugin;
	public GuiMenu(PoroticWarps plugin) {
		this.plugin = plugin;
	}
	
	public void openInv(Player p) {
		int size = this.plugin.getWarps().size();
		int res = Math.round(size / 9);
		
		if ((res < size) && (res * 9 != size)) {
		      res++;
		}
	
		 Inventory inventory = Bukkit.createInventory(null, res * 9, "§4§lPorotic Warp Menu");
		 
		 String nameColor = this.plugin.getConfig().getString("item_name-color");
		    ChatColor color = parseChatColor(nameColor);

		    String lore = this.plugin.getConfig().getString("display_item-lore");
		    lore = replaceColors(lore);

		    for (String warp : this.plugin.getWarps()) {
		      String item = this.plugin.getConfig().getString("display_item");
		      ItemStack stack = parseItemStack(item);
		      
		      ItemStack finalStack = createItemStack(stack, color + warp, new String[] { lore });
		      inventory.addItem(new ItemStack[] { finalStack });
		    }
		    p.openInventory(inventory);
		  }
	
	

		  private ItemStack parseItemStack(String in)
		  {
		    Material material = null;
		    byte data = 0;

		    if (in.contains(":")) {
		      String[] split = in.split(":");
		      String m = split[0];
		      String d = split[1];
		      try
		      {
		        material = Material.valueOf(m);
		      } catch (IllegalArgumentException ex) {
		        Bukkit.getLogger().warning(m + " is not a valid Material! Using default: WOOL");
		        material = Material.WOOL;
		      }

		      if ((d.equalsIgnoreCase("r")) || (d.equalsIgnoreCase("random"))) {
		        Random random = new Random();
		        DyeColor randColor = DyeColor.values()[random.nextInt(DyeColor.values().length)];

		        if (material == Material.WOOL)
		          data = randColor.getWoolData();
		        else
		          data = randColor.getData();
		      }
		      else
		      {
		        try {
		          data = Byte.parseByte(d);
		        } catch (NumberFormatException ex) {
		          Bukkit.getLogger().warning(d + " is not a valid data! Using default: 0");
		          data = 0;
		        }
		      }
		    }
		    else {
		      try {
		        material = Material.valueOf(in);
		      } catch (IllegalArgumentException ex) {
		        Bukkit.getLogger().warning(in + " is not a valid Material! Using default: WOOL");
		        material = Material.WOOL;
		      }
		    }

		    return new ItemStack(material, 1, data);
		  }

		  private ChatColor parseChatColor(String in) {
		    ChatColor chatColor = ChatColor.WHITE;
		    try
		    {
		      chatColor = ChatColor.valueOf(in);
		    }
		    catch (IllegalArgumentException localIllegalArgumentException) {
		    }
		    for (ChatColor color : ChatColor.values()) {
		      if (in.substring(1).equals(String.valueOf(color.getChar()))) {
		        chatColor = color;
		        break;
		      }
		    }

		    return chatColor;
		  }

		  private String replaceColors(String in) {
		    return ChatColor.translateAlternateColorCodes('&', in);
		  }

		  private ItemStack createItemStack(ItemStack stack, String name, String[] lores) {
		    ItemMeta meta = stack.getItemMeta();

		    meta.setDisplayName(name);

		    List loreList = new ArrayList();

		    for (String lore : lores) {
		      loreList.add(lore);
		    }

		    meta.setLore(loreList);

		    stack.setItemMeta(meta);
		    return stack;
		  }
		
	}



