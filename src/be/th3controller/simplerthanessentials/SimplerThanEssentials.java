package be.th3controller.simplerthanessentials;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import be.th3controller.simplerthanessentials.commands.CmdGamemode;
import be.th3controller.simplerthanessentials.commands.CmdKick;
import be.th3controller.simplerthanessentials.commands.CmdTime;

public class SimplerThanEssentials extends JavaPlugin{
	
	Logger log = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfile;
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new SimplerThanEssentialsListener(this), this);
		this.pdfile = getDescription();
		this.log.info(this + " is now enabled! Version" + this.pdfile.getVersion() + " Running a developers build!");
		getConfig().options().copyDefaults(true);
		saveConfig();
		getCommands();
	}
	
	public void getCommands(){
		getCommand("gamemode", new CmdGamemode());
		getCommand("time", new CmdTime());
		getCommand("kick", new CmdKick());
	}
	
	public void getCommand(String command, CommandExecutor commandexecutor){
		Bukkit.getServer().getPluginCommand(command).setExecutor(commandexecutor);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("tp")){
			
		}
		else if(cmd.getName().equalsIgnoreCase("notify")){
			if(!(sender instanceof Player)){
				if(args.length < 1){
					sender.sendMessage(ChatColor.RED + "Incorrect arguments!");
					sender.sendMessage(ChatColor.RED + "Usage: /notify <message>");
				}
				else if(args.length >= 1){
					StringBuilder b = new StringBuilder();
					/* 57 */        for (int i = 0; i < args.length; i++) {
					/* 58 */          if (i != 0)
					/* 59 */            b.append(" ");
					/* 60 */          b.append(args[i]);
					}
					getServer().broadcast(ChatColor.YELLOW + "[Notification]: " + ChatColor.GREEN + b, "ste.hearnotify");
				}
				return true;
			}else{
				if(!((Player)sender).hasPermission("ste.notify")){
					((Player)sender).sendMessage(ChatColor.RED + "Insufficient permissions to do this command!");
				}else{
					if(args.length < 1){
						sender.sendMessage(ChatColor.RED + "Incorrect arguments!");
						sender.sendMessage(ChatColor.RED + "Usage: /notify <message>");
					}
					else if(args.length >= 1){
						StringBuilder b = new StringBuilder();
						/* 57 */        for (int i = 0; i < args.length; i++) {
						/* 58 */          if (i != 0)
						/* 59 */            b.append(" ");
						/* 60 */          b.append(args[i]);
						}
						getServer().broadcast(ChatColor.YELLOW + "[Notification]: " + ChatColor.GREEN + b, "ste.hearnotify");
					}
				}
				return true;
			}
		}
		return false;
	}
}
