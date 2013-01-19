package be.th3controller.simplerthanessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.th3controller.simplerthanessentials.Messages;

public class CmdGamemode implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!(sender instanceof Player)){
			if(args.length == 0) {
				sender.sendMessage("Please provide a player name!");
			}
			else if(args.length > 1){
				sender.sendMessage("Too many arguments!");
				sender.sendMessage("Usage: /<command> [Player]");
			}
			else if(args.length == 1){
				Player other = Bukkit.getServer().getPlayer(args[0]);
				if(other == null){
					sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
				}else{
					if(other.getGameMode() == GameMode.SURVIVAL){
						other.setGameMode(GameMode.CREATIVE);
						sender.sendMessage("Setting " + args[0] + "'s gamemode to creative!");
					}
					else if(other.getGameMode() == GameMode.CREATIVE){
						other.setGameMode(GameMode.SURVIVAL);
						sender.sendMessage("Setting " + args[0] + "'s gamemode to survival!");
					}
				}
			}
			return true;
		}else{
			Player player = (Player)sender;
			if(args.length == 0){
				if(!player.hasPermission("ste.gamemode")){
					Messages.noPerm(player);
				}else{
					if(player.getGameMode() == GameMode.SURVIVAL){
						player.setGameMode(GameMode.CREATIVE);
					}
					else if(player.getGameMode() == GameMode.CREATIVE){
						player.setGameMode(GameMode.SURVIVAL);
					}
				}
			}
			else if(args.length == 1){
				if(!player.hasPermission("ste.gamemode.other")){
					Messages.noPerm(player);
				}else{
					Player other = Bukkit.getServer().getPlayer(args[0]);
					if(other == null){
						sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
					}else{
						if(other.getGameMode() == GameMode.SURVIVAL){
							other.setGameMode(GameMode.CREATIVE);
							player.sendMessage(ChatColor.GREEN + "Setting " + ChatColor.RESET + args[0] + ChatColor.GREEN + "'s gamemode to creative!");
						}
						else if(other.getGameMode() == GameMode.CREATIVE){
							other.setGameMode(GameMode.SURVIVAL);
							player.sendMessage(ChatColor.GREEN + "Setting " + ChatColor.RESET + args[0] + ChatColor.GREEN + "'s gamemode to survival!");
						}
					}
				}
			}
			else if(args.length > 1){
				player.sendMessage(ChatColor.RED + "Too many arguments!");
				player.sendMessage(ChatColor.RED + "Usage: /gamemode [Player]");
			}
			return true;
		}
	}
}
