package be.th3controller.simplerthanessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdKick implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!(sender instanceof Player)){
			if(args.length >= 2){
				Player other = Bukkit.getServer().getPlayer(args[0]);
				if(other == null){
					sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
				}else{
					StringBuilder b = new StringBuilder();
					/* 57 */        for (int i = 1; i < args.length; i++) {
					/* 58 */          if (i != 1)
					/* 59 */            b.append(" ");
					/* 60 */          b.append(args[i]);
					}
					other.kickPlayer("You have been kicked. Reason: " + b);
				}
			}
			else if(args.length == 1){
				Player other = Bukkit.getServer().getPlayer(args[0]);
				if(other == null){
					sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
				}else{
					other.kickPlayer("You have been kicked. Reason: Undefined");
				}
			}
			else if(args.length < 1){
				sender.sendMessage(ChatColor.RED + "Incorrect arguments!");
				sender.sendMessage(ChatColor.RED + "Usage: /kick <Player> [reason]");
			}
			return true;
		}else{
			if(!((Player)sender).hasPermission("ste.kick")){
				((Player)sender).sendMessage(ChatColor.RED + "Insufficient permissions to do this command!");
			}else{
				if(args.length >= 2){
					Player other = Bukkit.getServer().getPlayer(args[0]);
					if(other == null){
						sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
					}else{
						StringBuilder b = new StringBuilder();
						/* 57 */        for (int i = 1; i < args.length; i++) {
						/* 58 */          if (i != 1)
						/* 59 */            b.append(" ");
						/* 60 */          b.append(args[i]);
						}
						other.kickPlayer("You have been kicked. Reason: " + b);
					}
				}
				else if(args.length == 1){
					Player other = Bukkit.getServer().getPlayer(args[0]);
					if(other == null){
						sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
					}else{
						other.kickPlayer("You have been kicked. Reason: Undefined");
					}
				}
				else if(args.length < 1){
					sender.sendMessage(ChatColor.RED + "Incorrect arguments!");
					sender.sendMessage(ChatColor.RED + "Usage: /kick <Player> [reason]");
				}
			}
			return true;
		}
	}
}
