package be.th3controller.simplerthanessentials;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import be.th3controller.simplerthanessentials.commands.CmdChunkReset;
import be.th3controller.simplerthanessentials.commands.CmdGamemode;
import be.th3controller.simplerthanessentials.commands.CmdGod;
import be.th3controller.simplerthanessentials.commands.CmdKick;
import be.th3controller.simplerthanessentials.commands.CmdNotify;
import be.th3controller.simplerthanessentials.commands.CmdSetSpawn;
import be.th3controller.simplerthanessentials.commands.CmdSpawn;
import be.th3controller.simplerthanessentials.commands.CmdTime;
import be.th3controller.simplerthanessentials.commands.CmdTp;

public class SimplerThanEssentials extends JavaPlugin{
	
	Logger log = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfile;
	
	public static ArrayList<Player> godlist = new ArrayList<Player>();
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new SimplerThanEssentialsListener(this), this);
		this.pdfile = getDescription();
		this.log.info("[SimplerThanEssentials] Successfully initiated the plugin!");
		this.log.info("[SimplerThanEssentials] Running version: "+this.pdfile.getVersion());
		getConfig().options().copyDefaults(true);
		saveConfig();
		getCommands();
	}
	public void getCommands(){
		getCommand("gamemode", new CmdGamemode());
		getCommand("time", new CmdTime());
		getCommand("kick", new CmdKick());
		getCommand("god", new CmdGod());
		getCommand("notify", new CmdNotify());
		getCommand("tp", new CmdTp());
		getCommand("chunkreset", new CmdChunkReset());
		getCommand("spawn", new CmdSpawn());
		getCommand("setspawn", new CmdSetSpawn());
	}
	public void getCommand(String command, CommandExecutor commandexecutor){
		Bukkit.getServer().getPluginCommand(command).setExecutor(commandexecutor);
	}
}
