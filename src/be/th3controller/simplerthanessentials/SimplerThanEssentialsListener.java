package be.th3controller.simplerthanessentials;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SimplerThanEssentialsListener implements Listener{
	
	SimplerThanEssentials plugin;
	
	public SimplerThanEssentialsListener(SimplerThanEssentials plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void PlayerJoins(PlayerJoinEvent event){
		event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("loginmessage")));
	}
	@EventHandler
	public void PlayerDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player god = (Player)e.getEntity();
			if(SimplerThanEssentials.godlist.contains(god.getName())) {
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void PlayerRespawn(PlayerRespawnEvent event) {
		if(!(event.isBedSpawn())) {
			event.setRespawnLocation(event.getPlayer().getWorld().getSpawnLocation());
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void PlayerLogin(PlayerLoginEvent event) {
		String reason = SimplerThanEssentials.banlist.getString("banned."+event.getPlayer().getName().toLowerCase()+".reason");
		Set<String> players = SimplerThanEssentials.banlist.getConfigurationSection("banned").getKeys(false);
		if(players.contains(event.getPlayer().getName().toLowerCase())) {
			event.disallow(Result.KICK_BANNED, ChatColor.RED+"You are banned, reason: "+ChatColor.RESET+reason);
		}
	}
}
