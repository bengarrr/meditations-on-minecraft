package hey.bengarrr.uniqueplayer;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerListener implements Listener {

	File playerFile;
	
	boolean newPlayer = false;

	int count;
	String welcomeMessage;
	String countMessage;

	public PlayerListener(UniquePlayer p) {
		count = p.count;
		welcomeMessage = p.welcomeMessage;
		countMessage = p.countMessage;
	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		playerFile = new File("plugins/Essentials/userdata" + File.separator
				+ event.getPlayer().getName().toLowerCase() + ".yml");
		if (!playerFile.exists()) {
			count++;
			newPlayer = true;
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
	if(newPlayer)
	{
			if (!countMessage.isEmpty())
				Bukkit.getServer()
						.broadcastMessage(
								format(countMessage, event.getPlayer()
										.getName(), Bukkit.getServerName(),
										Integer.toString(count)));
			if (!welcomeMessage.isEmpty())
				Bukkit.getServer()
						.broadcastMessage(
								format(welcomeMessage, event.getPlayer()
										.getName(), Bukkit.getServerName(),
										Integer.toString(count)));
			newPlayer = false;
		}
	}

	public String format(String s, String player, String server, String count) {
		return s.replace('&', '§').replace("§§", "&")
				.replace("{Player}", player).replace("{Server}", server)
				.replace("{Count}", count);
	}
}
