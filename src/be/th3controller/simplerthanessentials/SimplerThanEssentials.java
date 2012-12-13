package be.th3controller.simplerthanessentials;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SimplerThanEssentials extends JavaPlugin{
	
	Logger log = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfile;
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new SimplerThanEssentialsListener(this), this);
		this.pdfile = getDescription();
		this.log.info(this + " is now enabled! Version" + this.pdfile.getVersion() + " Running a developers build!");
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("gamemode")){
			if(!(sender instanceof Player)){
				if(args.length == 0) {
					sender.sendMessage("Please provide a player name!");
				}
				else if(args.length > 1){
					sender.sendMessage("Too many arguments!");
					sender.sendMessage("Usage: /<command> [Player]");
				}
				else if(args.length == 1){
					Player other = Bukkit.getServer().getPlayer(args[0]);
					if(other == null){
						sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
					}else{
						if(other.getGameMode() == GameMode.SURVIVAL){
							other.setGameMode(GameMode.CREATIVE);
							sender.sendMessage("Setting " + args[0] + "'s gamemode to creative!");
						}
						else if(other.getGameMode() == GameMode.CREATIVE){
							other.setGameMode(GameMode.SURVIVAL);
							sender.sendMessage("Setting " + args[0] + "'s gamemode to survival!");
						}
					}
				}
				return true;
			}else{
				if(args.length == 0){
					if(!((Player)sender).hasPermission("ste.gamemode")){
						((Player)sender).sendMessage(ChatColor.RED + "Insufficient permissions to do this command!");
					}else{
						if(((Player)sender).getGameMode() == GameMode.SURVIVAL){
							((Player)sender).setGameMode(GameMode.CREATIVE);
						}
						else if(((Player)sender).getGameMode() == GameMode.CREATIVE){
							((Player)sender).setGameMode(GameMode.SURVIVAL);
						}
					}
					return true;
				}
				else if(args.length == 1){
					if(!((Player)sender).hasPermission("ste.gamemode.other")){
						((Player)sender).sendMessage(ChatColor.RED + "Insufficient permissions to do this command!");
					}else{
						Player other = Bukkit.getServer().getPlayer(args[0]);
						if(other == null){
							sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
						}else{
							if(other.getGameMode() == GameMode.SURVIVAL){
								other.setGameMode(GameMode.CREATIVE);
								((Player)sender).sendMessage(ChatColor.GREEN + "Setting " + ChatColor.RESET + args[0] + ChatColor.GREEN + "'s gamemode to creative!");
							}
							else if(other.getGameMode() == GameMode.CREATIVE){
								other.setGameMode(GameMode.SURVIVAL);
								((Player)sender).sendMessage(ChatColor.GREEN + "Setting " + ChatColor.RESET + args[0] + ChatColor.GREEN + "'s gamemode to survival!");
							}
						}
					}
					return true;
				}
				else if(args.length > 1){
					((Player)sender).sendMessage(ChatColor.RED + "Too many arguments!");
					((Player)sender).sendMessage(ChatColor.RED + "Usage: /gamemode [Player]");
					return true;
				}
			}
		}
		else if(cmd.getName().equalsIgnoreCase("time")){
			if(!(sender instanceof Player)){
				sender.sendMessage("Time set to TIMEARG in world WORLDARG");
				return true;
			}else{
				if(!((Player)sender).hasPermission("ste.time")){
					((Player)sender).sendMessage(ChatColor.RED + "Insufficient permissions to do this command!");
				}else{
					if((args.length > 1) || (args.length < 1)){
						sender.sendMessage(ChatColor.RED + "Too many or not enough arguments!");
						sender.sendMessage(ChatColor.RED + "Usage: /time <day/night>");
					}
					else if(args.length == 1){
						String worldn = ((Player)sender).getWorld().getName().toString();
						if(args[0].equalsIgnoreCase("day")){
							((Player)sender).getWorld().setTime(0L);
							((Player)sender).sendMessage(ChatColor.YELLOW + "Time set to day in: " + ChatColor.RESET + worldn);
						}
						else if(args[0].equalsIgnoreCase("night")){
							((Player)sender).getWorld().setTime(14000L);
							((Player)sender).sendMessage(ChatColor.YELLOW + "Time set to night in: " + ChatColor.RESET + worldn);
						}
						else if((!args[0].equalsIgnoreCase("day")) || (!args[0].equalsIgnoreCase("night"))){
							sender.sendMessage(ChatColor.RED + "Incorrect arguments!");
							sender.sendMessage(ChatColor.RED + "Usage: /time <day/night>");
						}
					}
				}
				return true;
			}
		}
		return false;
	}
}
