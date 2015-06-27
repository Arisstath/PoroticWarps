package me.Ayush_03.PoroticWarps.Commands;

import me.Ayush_03.PoroticWarps.GuiMenu;
import me.Ayush_03.PoroticWarps.PoroticWarps;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cmd implements CommandExecutor{
	
	PoroticWarps plugin;
	public Cmd (PoroticWarps plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("warp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cThe console cannot execute this command!");
				return true;
			}
			
			Player p = (Player) sender;
			
			if (!(p.hasPermission("pw.open"))) {
				p.sendMessage("§cYou do not have permission to execute this command!");
				return true;
			}
			
			 if (this.plugin.getWarps().size() == 0) {
		          p.sendMessage(ChatColor.RED + "No warps found.");
		          return true;
		}
			 
			   GuiMenu menu = new GuiMenu(this.plugin);
		        menu.openInv(p);
		        return true;
	}
		
		if (cmd.getName().equalsIgnoreCase("pw")) {
			if (args.length == 0) {
				sender.sendMessage("§8=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				sender.sendMessage("§6Author :- Ayush_03");
				sender.sendMessage("§4§lFor Porotic Servers");
				sender.sendMessage("§8Available commands:-");
				sender.sendMessage("§6/pw reload :- Reloads the configuration.");
				sender.sendMessage("§8=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			} else
			
			if (args[0].equalsIgnoreCase("reload")) {
				Player p = (Player) sender;
				if (!(p.hasPermission("pw.reload"))) {
					sender.sendMessage("§cYou do not have permission to execute this command!");
					return true;
				} else {
					this.plugin.warpLoad();
					p.sendMessage("§aSuccessfully reloaded warps!");
					return true;
				}
			}
		}
		return true;
		
		
		
	}
}
