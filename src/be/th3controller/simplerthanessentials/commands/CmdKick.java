package be.th3controller.simplerthanessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.th3controller.simplerthanessentials.Messages;

public class CmdKick implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!(sender instanceof Player)){
			ChatColor red = ChatColor.RED;
			ChatColor yel = ChatColor.YELLOW;
			ChatColor res = ChatColor.RESET;
			if(args.length >= 2){
				Player other = Bukkit.getServer().getPlayer(args[0]);
				if(other == null){
					sender.sendMessage(red + args[0] + " is not online!");
				}else{
					String reason = new String();
					for (int i = 1; i < args.length; i++) {
						reason = reason + " " + args[i];
					}
					other.kickPlayer("You have been kicked. Reason:" + reason);
					Bukkit.getServer().broadcastMessage(sender.getName()+yel+" kicked: "+res+args[0]);
					Bukkit.getServer().broadcastMessage(yel+"Reason:"+res+reason);
				}
			}
			else if(args.length == 1){
				Player other = Bukkit.getServer().getPlayer(args[0]);
				if(other == null){
					sender.sendMessage(red + args[0] + " is not online!");
				}else{
					other.kickPlayer("You have been kicked. Reason: Undefined");
					Bukkit.getServer().broadcastMessage(sender.getName()+yel+" kicked: "+res+args[0]);
					Bukkit.getServer().broadcastMessage(yel+"Reason:" +res+"Undefined");
				}
			}
			else if(args.length < 1){
				sender.sendMessage(red + "Incorrect arguments!");
				sender.sendMessage(red + "Usage: /kick <Player> [reason]");
			}
			return true;
		}else{
			Player player = (Player)sender;
			ChatColor red = ChatColor.RED;
			ChatColor yel = ChatColor.YELLOW;
			ChatColor res = ChatColor.RESET;
			if(!player.hasPermission("ste.kick")){
				Messages.noPerm(player);
			}else{
				if(args.length >= 2){
					Player other = Bukkit.getServer().getPlayer(args[0]);
					if(other == null){
						sender.sendMessage(red + args[0] + " is not online!");
					}else{
						String reason = new String();
						for (int i = 1; i < args.length; i++) {
							reason = reason + " " + args[i];
						}
						other.kickPlayer("You have been kicked. Reason:" + reason);
						Bukkit.getServer().broadcastMessage(sender.getName()+yel+" kicked: "+res+args[0]);
						Bukkit.getServer().broadcastMessage(yel+"Reason:"+res+reason);
					}
				}
				else if(args.length == 1){
					Player other = Bukkit.getServer().getPlayer(args[0]);
					if(other == null){
						sender.sendMessage(red + args[0] + " is not online!");
					}else{
						other.kickPlayer("You have been kicked. Reason: Undefined");
						Bukkit.getServer().broadcastMessage(sender.getName()+yel+" kicked: "+res+args[0]);
						Bukkit.getServer().broadcastMessage(yel+"Reason:" +res+"Undefined");
					}
				}
				else if(args.length < 1){
					sender.sendMessage(red + "Incorrect arguments!");
					sender.sendMessage(red + "Usage: /kick <Player> [reason]");
				}
			}
			return true;
		}
	}
}
