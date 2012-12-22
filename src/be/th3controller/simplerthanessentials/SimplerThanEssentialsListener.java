package be.th3controller.simplerthanessentials;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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
	public void PlayerDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player) {
			Player god = (Player)e.getEntity();
			if(SimplerThanEssentials.godlist.contains(god)) {
				e.setCancelled(true);
			}
		}
	}
}
