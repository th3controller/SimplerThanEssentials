package be.th3controller.simplerthanessentials;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messages {
	
	public static void noPerm(Player player) {
		player.sendMessage(ChatColor.RED + "Insufficient permissions to do this command!");
	}
	public static void consoleUse(CommandSender commandsender) {
		commandsender.sendMessage("You have to be in-game to do this command!");
	}
}
