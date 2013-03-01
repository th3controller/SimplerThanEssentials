package be.th3controller.simplerthanessentials;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import be.th3controller.simplerthanessentials.commands.CmdBan;
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
	
	public static ArrayList<String> godlist = new ArrayList<String>();
	private static File banfile;
	public static FileConfiguration banlist;
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new SimplerThanEssentialsListener(this), this);
		this.pdfile = getDescription();
		this.log.info("[SimplerThanEssentials] Successfully initiated the plugin!");
		this.log.info("[SimplerThanEssentials] Running version: "+this.pdfile.getVersion());
		banfile = new File("plugins/SimplerThanEssentials", "banned.yml");
		if(!banfile.exists()) {
			try {
				banfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getConfig().options().copyDefaults(true);
		saveConfig();
		banlist = YamlConfiguration.loadConfiguration(banfile);
		getCommands();
	}
	public void getCommands(){
		getCommand("ban", new CmdBan());
		getCommand("chunkreset", new CmdChunkReset());
		getCommand("gamemode", new CmdGamemode());
		getCommand("god", new CmdGod());
		getCommand("kick", new CmdKick());
		getCommand("notify", new CmdNotify());
		getCommand("setspawn", new CmdSetSpawn());
		getCommand("spawn", new CmdSpawn());
		getCommand("time", new CmdTime());
		getCommand("tp", new CmdTp());
	}
	public void getCommand(String command, CommandExecutor commandexecutor){
		Bukkit.getServer().getPluginCommand(command).setExecutor(commandexecutor);
	}
	public static void saveBan() {
		try {
			banlist.save(banfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
