package be.th3controller.simplerthanessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.th3controller.simplerthanessentials.Messages;

public class CmdNotify implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			if(args.length < 1){
				sender.sendMessage(ChatColor.RED + "Incorrect arguments!");
				sender.sendMessage(ChatColor.RED + "Usage: /notify <message>");
			}
			else if(args.length >= 1){
				String notify = new String();
				for (int i = 0; i < args.length; i++) {
					notify = notify + " " + args[i];
				}
				Bukkit.getServer().broadcast(ChatColor.YELLOW + "[Notification]:" + ChatColor.GREEN + notify, "ste.hearnotify");
			}
			return true;
		}else{
			Player player = (Player)sender;
			if(!player.hasPermission("ste.notify")){
				Messages.noPerm(player);
			}else{
				if(args.length < 1){
					sender.sendMessage(ChatColor.RED + "Incorrect arguments!");
					sender.sendMessage(ChatColor.RED + "Usage: /notify <message>");
				}
				else if(args.length >= 1){
					String notify = new String();
					for (int i = 0; i < args.length; i++) {
						notify = notify + " " + args[i];
					}
					Bukkit.getServer().broadcast(ChatColor.YELLOW + "[Notification]:" + ChatColor.GREEN + notify, "ste.hearnotify");
				}
			}
			return true;
		}
	}
}
