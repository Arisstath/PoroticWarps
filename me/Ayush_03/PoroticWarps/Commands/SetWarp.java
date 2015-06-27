package me.Ayush_03.PoroticWarps.Commands;

import java.io.File;
import java.io.IOException;

import me.Ayush_03.PoroticWarps.PoroticWarps;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor {
	
	PoroticWarps plugin;
	public SetWarp(PoroticWarps plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("setwarp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cThe console cannot execute this command!");
				return true;
			}
			
			Player p = (Player) sender;
			if (!(p.hasPermission("pw.set"))) {
				p.sendMessage("§cYou do not have permission to execute this command!");
				return true;
			}
			
			if (args.length == 0) {
				p.sendMessage("§cUsage: /setwarp <name>");
				return true;
			}
			
			 String str = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', args[0].toLowerCase()));
		        File warp = new File("plugins/PoroticWarps/Warps", str + ".yml");
		        FileConfiguration cfg = YamlConfiguration.loadConfiguration(warp);
		        Location loc = p.getLocation();
		        
		        cfg.set(str + ".name", args[0]);
		        cfg.set(str + ".world", loc.getWorld().getName());
		        cfg.set(str + ".x", Double.valueOf(loc.getX()));
		        cfg.set(str + ".y", Double.valueOf(loc.getY()));
		        cfg.set(str + ".z", Double.valueOf(loc.getZ()));
		        cfg.set(str + ".yaw", Float.valueOf(loc.getYaw()));
		        cfg.set(str + ".pitch", Float.valueOf(loc.getPitch()));
		        
		        try {
		        	 cfg.save(warp);
		        	p.sendMessage("§aSuccessfully created warp " + args[0] + ".");
		        } catch (IOException e) {
		        	p.sendMessage("§cCould not create a warp.");
		        	e.printStackTrace();
		        }
		}
		return true;
	}

}
