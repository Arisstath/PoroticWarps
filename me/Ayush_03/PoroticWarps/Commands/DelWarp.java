package me.Ayush_03.PoroticWarps.Commands;

import java.io.File;

import me.Ayush_03.PoroticWarps.PoroticWarps;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DelWarp implements CommandExecutor {
	
	PoroticWarps plugin;
	public DelWarp(PoroticWarps plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String [] args) {
		
		if (cmd.getName().equalsIgnoreCase("delwarp")) {
			
			if (!(sender.hasPermission("pw.delwarp"))) {
				sender.sendMessage("§cYou do not have permission to execute this command!");
				return true;
			}
			
			if (args.length == 0) {
				sender.sendMessage("§cUsage: /delwarp <warp>");
				return true;
				
			}
			
			File file = new File("plugins/PoroticWarps/Warps", args[0].toLowerCase() + ".yml");
			
			 if (!file.exists()) {
		         sender.sendMessage("§cWarp doesnt exists!");
		          return true;
		}
			 
			 FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		        String str = args[0].toLowerCase();

		        String name = "";

		        if (cfg.getString(str + ".name") == null)
		          name = str;
		        else {
		          name = ChatColor.translateAlternateColorCodes('&', cfg.getString(str + ".name"));
		        }

		        file.delete();
		      sender.sendMessage("§aSuccessfully deleted warp " + args[0] + ".");
		        return true;
		
	}
		return true;
	
	}
}
