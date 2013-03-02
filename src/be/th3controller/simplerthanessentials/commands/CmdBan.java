package be.th3controller.simplerthanessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.th3controller.simplerthanessentials.Messages;
import be.th3controller.simplerthanessentials.SimplerThanEssentials;

public class CmdBan implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			if(args.length >= 2) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				String reason = new String();
				for (int i = 1; i < args.length; i++) {
					reason = reason + " " + args[i];
				}
				SimplerThanEssentials.banlist.set("banned."+args[0].toLowerCase()+".reason", reason);
				SimplerThanEssentials.banlist.set("banned."+args[0].toLowerCase()+".issuer", sender.getName());
				SimplerThanEssentials.saveBan();
				if(target != null) {
					target.kickPlayer("You have been banned, reason:"+reason);
				}
			}
			if(args.length == 1) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				SimplerThanEssentials.banlist.set("banned."+args[0].toLowerCase()+".reason", "Undefined");
				SimplerThanEssentials.banlist.set("banned."+args[0].toLowerCase()+".issuer", sender.getName());
				SimplerThanEssentials.saveBan();
				if(target != null) {
					target.kickPlayer("You have been banned, reason: Undefined");
				}
			}
			if(args.length == 0) {
				return false;
			}
			return true;
		} else {
			if(sender.hasPermission("ste.ban")) {
				if(args.length >= 2) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					String reason = new String();
					for (int i = 1; i < args.length; i++) {
						reason = reason + " " + args[i];
					}
					SimplerThanEssentials.banlist.set("banned."+args[0].toLowerCase()+".reason", reason);
					SimplerThanEssentials.banlist.set("banned."+args[0].toLowerCase()+".issuer", sender.getName());
					SimplerThanEssentials.saveBan();
					if(target != null) {
						target.kickPlayer("You have been banned, reason:"+reason);
					}
				}
				if(args.length == 1) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					SimplerThanEssentials.banlist.set("banned."+args[0].toLowerCase()+".reason", "Undefined");
					SimplerThanEssentials.banlist.set("banned."+args[0].toLowerCase()+".issuer", sender.getName());
					SimplerThanEssentials.saveBan();
					if(target != null) {
						target.kickPlayer("You have been banned, reason: Undefined");
					}
				}
				if(args.length == 0) {
					return false;
				}
			} else {
				Messages.noPerm((Player)sender);
			}
			return true;
		}
	}
}
