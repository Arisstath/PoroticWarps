package me.Ayush_03.PoroticWarps.Commands;

import java.io.File;

import me.Ayush_03.PoroticWarps.PoroticWarps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor{ 
	
	PoroticWarps plugin;
	public Warp(PoroticWarps plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("pwarp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cThe console cannot execute this command!");
				return true;
			}
			
			Player p = (Player) sender;
			
			if (args.length == 0) {
				p.sendMessage("§cUsage: /pwarp <warp>");
				return true;
			}
			
			if (!(p.hasPermission("pw.warp." + args[0]) || p.hasPermission("pw.warps"))) {
				
				p.sendMessage("§cYou do not have permission to use this warp.");
				return true;
				
			} else {
				
				 String str = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', args[0].toLowerCase()));
			        File warp = new File("plugins/PoroticWarps/Warps", str + ".yml");
			        FileConfiguration cfg = YamlConfiguration.loadConfiguration(warp);
			        
			        if (cfg.getConfigurationSection(args[0]) == null) {
			        	p.sendMessage("§cThe warp does not exists!");
			        	return true;
			        } else {
			        	 World w = Bukkit.getWorld(cfg.getString(str + ".world"));
			        	 double x = cfg.getDouble(str + ".x");
			             double y = cfg.getDouble(str + ".y");
			             double z = cfg.getDouble(str + ".z");
			             double yaw = cfg.getDouble(str + ".yaw");
			             double pitch = cfg.getDouble(str + ".pitch");
			             final Location loc = new Location(w, x, y, z, (float)yaw, (float)pitch);
			             p.teleport(loc);
			        }
			}
		}
		return true;
	}

}
