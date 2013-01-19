package be.th3controller.simplerthanessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.th3controller.simplerthanessentials.Messages;

public class CmdSetSpawn implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Messages.consoleUse(sender);
			return true;
		}else{
			Player player = (Player)sender;
			if(!player.hasPermission("ste.setspawn")) {
				Messages.noPerm(player);
			}else{
				Location loc = player.getLocation();
				player.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
				player.sendMessage(ChatColor.GREEN +"Successfully changed the spawn point");
			}
			return true;
		}
	}
}
