package be.th3controller.simplerthanessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdNotify implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
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
				Bukkit.getServer().broadcast(ChatColor.YELLOW + "[Notification]: " + ChatColor.GREEN + b, "ste.hearnotify");
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
					Bukkit.getServer().broadcast(ChatColor.YELLOW + "[Notification]: " + ChatColor.GREEN + b, "ste.hearnotify");
				}
			}
			return true;
		}
	}
}
