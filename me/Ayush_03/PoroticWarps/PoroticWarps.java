package me.Ayush_03.PoroticWarps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.Ayush_03.PoroticWarps.Commands.Cmd;
import me.Ayush_03.PoroticWarps.Commands.DelWarp;
import me.Ayush_03.PoroticWarps.Commands.SetWarp;
import me.Ayush_03.PoroticWarps.Commands.Warp;

import org.bukkit.plugin.java.JavaPlugin;

public class PoroticWarps extends JavaPlugin {
	
	private List<String> warps;
	
	public void onEnable() {
		
		getCommand("warp").setExecutor(new Cmd(this));
		getCommand("pw").setExecutor(new Cmd(this));
		getCommand("setwarp").setExecutor(new SetWarp(this));
		getCommand("pwarp").setExecutor(new Warp(this));
		getCommand("delwarp").setExecutor(new DelWarp(this));
		getServer().getPluginManager().registerEvents(new Events(this), this);
		saveDefaultConfig();
		this.warps = new ArrayList<String>();
		warpLoad();
	}
	
	public void warpLoad() {
		 File warpDirectory = new File(this.getDataFolder() + File.separator + "Warps");
		 
		 if ((!warpDirectory.exists()) || (!warpDirectory.isDirectory())) {
		      return;
	}
		 
		 this.warps.clear();
		 
		 for (File file : warpDirectory.listFiles()) {
		      String realName = file.getName().replace(".yml", "");
		      this.warps.add(realName);
		    }
		  }

		  public List<String> getWarps() {
		    return this.warps;
		  }
}