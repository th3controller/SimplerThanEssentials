package be.th3controller.simplerthanessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.th3controller.simplerthanessentials.Messages;

public class CmdTp implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			Messages.consoleUse(sender);
			return true;
		}else{
			Player player = (Player)sender;
			if(!player.hasPermission("ste.tp")){
				Messages.noPerm(player);
			}else{
				ChatColor red = ChatColor.RED;
				ChatColor reset = ChatColor.RESET;
				ChatColor green = ChatColor.GREEN;
				if(args.length == 1) {
					Player other = Bukkit.getServer().getPlayer(args[0]);
					if(other == null) {
						sender.sendMessage(red + args[0] + "is not online!");
					}else{
						player.teleport(other.getLocation());
						sender.sendMessage(green + "Successfully teleported to " + reset + args[0]);
					}
				}
				else if(args.length == 2) {
					Player target0 = Bukkit.getServer().getPlayer(args[0]);
					Player target1 = Bukkit.getServer().getPlayer(args[1]);
					if(target0 == null || target1 == null) {
						sender.sendMessage(red + "One of the players is not online!");
					}else{
						target0.teleport(target1.getLocation());
						sender.sendMessage(green + "Successfully teleported " + args[0] + " to " + args[1]);
						target0.sendMessage(green + "Successfully teleported to " + reset + args[1]);
					}
				}
				else if(args.length == 3) {
					int xCord = Integer.parseInt(args[0]);
					int yCord = Integer.parseInt(args[1]);
					int zCord = Integer.parseInt(args[2]);
					player.teleport(new Location(player.getWorld(), xCord, yCord, zCord));
					sender.sendMessage(green+"Successfully teleported to "+xCord+", "+yCord+", "+zCord);
				}
				else if(args.length > 3) {
					sender.sendMessage(red + "Too many arguments!");
				}
			}
			return true;
		}
	}
}
