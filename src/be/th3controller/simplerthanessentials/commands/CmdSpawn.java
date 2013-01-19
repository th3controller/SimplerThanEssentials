package be.th3controller.simplerthanessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.th3controller.simplerthanessentials.Messages;

public class CmdSpawn implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Messages.consoleUse(sender);
			return true;
		}else{
			Player player = (Player)sender;
			if(!player.hasPermission("ste.spawn")) {
				Messages.noPerm(player);
			}else{
				player.teleport(player.getWorld().getSpawnLocation());
			}
			return true;
		}
	}
}
