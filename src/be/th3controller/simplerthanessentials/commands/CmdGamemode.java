package be.th3controller.simplerthanessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.th3controller.simplerthanessentials.Messages;

public class CmdGamemode implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			if(args.length == 0) {
				sender.sendMessage("Please provide a player name!");
			}
			else if(args.length > 1) {
				sender.sendMessage("Too many arguments!");
				sender.sendMessage("Usage: /<command> [Player]");
			}
			else if(args.length == 1) {
				Player other = Bukkit.getServer().getPlayer(args[0]);
				if(other == null){
					sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
				} else {
					if(other.getGameMode() == GameMode.SURVIVAL) {
						other.setGameMode(GameMode.CREATIVE);
						sender.sendMessage("Setting " + args[0] + "'s gamemode to creative!");
					}
					else if(other.getGameMode() == GameMode.CREATIVE) {
						other.setGameMode(GameMode.SURVIVAL);
						sender.sendMessage("Setting " + args[0] + "'s gamemode to survival!");
					}
				}
			}
			return true;
		} else {
			Player player = (Player)sender;
			if(args.length == 2) {
				if(player.hasPermission("ste.gamemode.other")) {
					Player target = Bukkit.getServer().getPlayer(args[1]);
					if(target == null) {
						sender.sendMessage(ChatColor.RED+args[1]+" is not online!");
					} else {
						if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) {
							target.setGameMode(GameMode.SURVIVAL);
							Messages.newGameMode(player, "SURVIVAL");
						}
						if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
							target.setGameMode(GameMode.CREATIVE);
							Messages.newGameMode(player, "CREATIVE");
						}
						if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")) {
							target.setGameMode(GameMode.ADVENTURE);
							Messages.newGameMode(player, "ADVENTURE");
						}
					}
				} else {
					Messages.noPerm(player);
				}
			}
			if(args.length == 1) {
				if(player.hasPermission("ste.gamemode")) {
					if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) {
						Messages.newGameMode(player, "SURVIVAL");
						player.setGameMode(GameMode.SURVIVAL);
					}
					if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
						Messages.newGameMode(player, "CREATIVE");
						player.setGameMode(GameMode.CREATIVE);
					}
					if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")) {
						Messages.newGameMode(player, "ADVENTURE");
						player.setGameMode(GameMode.ADVENTURE);
					}
				} else {
					Messages.noPerm(player);
				}
			}
			if(args.length == 0){
				if(!player.hasPermission("ste.gamemode")){
					Messages.noPerm(player);
				} else {
					if(player.getGameMode() == GameMode.SURVIVAL){
						player.setGameMode(GameMode.CREATIVE);
					}
					else if(player.getGameMode() == GameMode.CREATIVE){
						player.setGameMode(GameMode.SURVIVAL);
					}
				}
			}
			if(args.length > 2){
				return false;
			}
			return true;
		}
	}
}
