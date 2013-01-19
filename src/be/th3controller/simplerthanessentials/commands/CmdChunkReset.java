package be.th3controller.simplerthanessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.th3controller.simplerthanessentials.Messages;

public class CmdChunkReset implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Messages.consoleUse(sender);
			return true;
		}else{
			Player player = (Player)sender;
			if(player.hasPermission("ste.chunkreset")) {
				if(args.length == 0) {
					player.getWorld().regenerateChunk(player.getLocation().getChunk().getX(), player.getLocation().getChunk().getZ());
					sender.sendMessage(ChatColor.GREEN+"Successfully regenerated the chunk!");
				}
				else if(args.length > 0) {
					sender.sendMessage(ChatColor.RED+"Too many arguments!");
				}
			}else{
				Messages.noPerm(player);
			}
			return true;
		}
	}
}
