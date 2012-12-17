package be.th3controller.simplerthanessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTime implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!(sender instanceof Player)){
			if((args.length > 1) || (args.length < 1)){
				sender.sendMessage(ChatColor.RED + "Too many or not enough arguments!");
				sender.sendMessage(ChatColor.RED + "Usage: /time <day/night>");
			}
			else if(args.length == 1){
				for(World w : Bukkit.getWorlds()){
					if(args[0].equalsIgnoreCase("day")){
						w.setTime(0L);
						sender.sendMessage(ChatColor.YELLOW + "Time set to day in all worlds");
					}
					else if(args[0].equalsIgnoreCase("night")){
						w.setTime(14000L);
						sender.sendMessage(ChatColor.YELLOW + "Time set to night in all worlds");
					}
					else if((!args[0].equalsIgnoreCase("day")) || (!args[0].equalsIgnoreCase("night"))){
						sender.sendMessage(ChatColor.RED + "Incorrect arguments!");
						sender.sendMessage(ChatColor.RED + "Usage: /time <day/night>");
					}
				}
			}
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
}
