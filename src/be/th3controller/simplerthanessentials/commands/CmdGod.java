package be.th3controller.simplerthanessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.th3controller.simplerthanessentials.Messages;
import be.th3controller.simplerthanessentials.SimplerThanEssentials;

public class CmdGod implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			if(args.length == 0) {
				sender.sendMessage("Please provide a player name!");
			}
			else if(args.length > 1) {
				sender.sendMessage("Too many arguments!");
				sender.sendMessage("Usage: /<command> [Player]");
			}
			else if(args.length == 1) {
				Player other = Bukkit.getServer().getPlayer(args[0]);
				if(other == null) {
					sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
				}else{
					if(SimplerThanEssentials.godlist.contains(other.getName())) {
						SimplerThanEssentials.godlist.remove(other.getName());
						sender.sendMessage(ChatColor.GREEN + "God mode disabled for: " + ChatColor.RESET + args[0]);
						other.sendMessage(ChatColor.GREEN + "God mode disabled");
					}else{
						SimplerThanEssentials.godlist.add(other.getName());
						sender.sendMessage(ChatColor.GREEN + "God mode enabled for: " + ChatColor.RESET + args[0]);
						other.sendMessage(ChatColor.GREEN + "God mode enabled");
					}
				}
			}
			return true;
		}else{
			Player player = (Player)sender;
			if(!player.hasPermission("ste.god")){
				Messages.noPerm(player);
			}else{
				if(args.length == 0) {
					if(SimplerThanEssentials.godlist.contains(player.getName())) {
						SimplerThanEssentials.godlist.remove(player.getName());
						sender.sendMessage(ChatColor.GREEN + "God mode disabled");
					}else{
						SimplerThanEssentials.godlist.add(player.getName());
						sender.sendMessage(ChatColor.GREEN + "God mode enabled");
					}
				}
				else if(args.length > 1) {
					sender.sendMessage("Too many arguments!");
					sender.sendMessage("Usage: /<command> [Player]");
				}
				else if(args.length == 1) {
					Player other = Bukkit.getServer().getPlayer(args[0]);
					if(other == null) {
						sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
					}else{
						if(SimplerThanEssentials.godlist.contains(other.getName())) {
							SimplerThanEssentials.godlist.remove(other.getName());
							sender.sendMessage(ChatColor.GREEN + "God mode disabled for: " + ChatColor.RESET + args[0]);
						}else{
							SimplerThanEssentials.godlist.add(other.getName());
							sender.sendMessage(ChatColor.GREEN + "God mode enabled for: " + ChatColor.RESET + args[0]);
						}
					}
				}
			}
			return true;
		}
	}
}
