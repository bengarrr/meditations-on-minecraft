package hey.bengarrr.uniqueplayer;

import java.io.File;
import java.util.logging.Logger;
import java.lang.String;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UniquePlayer extends JavaPlugin {
	public String welcomeMessage = "&dA wild &a{Player} &dappears";
	public String countMessage = "&dUnique player: &3{Count} &dhas joined the server";
	public int count;

	public static String cast = "[UniquePlayer] ";
	public static Logger log = Logger.getLogger("Minecraft");

	FileConfiguration config;
	File configFile;

	PlayerListener p;

	@Override
	public void onEnable() {
		try {
			configFile = new File(getDataFolder(), "config.yml");
			config = YamlConfiguration.loadConfiguration(configFile);
			if (!getDataFolder().exists()) {
				getDataFolder().mkdir();
			}
			if (!configFile.exists()) {
				configFile.createNewFile();
				config.set("WelcomeMessage", welcomeMessage);
				config.set("CountMessage", countMessage);
				config.save(configFile);
			} else {
				welcomeMessage = config.getString("WelcomeMessage");
				countMessage = config.getString("CountMessage");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		File playerFile = new File("plugins/Essentials/userdata");
		count = playerFile.listFiles().length;

		p = new PlayerListener(this);
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(p, this);

		log.info(cast + this.getDescription().getVersion() + " Enabled");
	}
	
	public void onDisbale() {
		log.info(cast + this.getDescription().getVersion() + " Disabled");
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
    	if(cmd.getName().equalsIgnoreCase("UniquePlayerReload")){
    		Player player = null;
    		if (sender instanceof Player) {
    			player = (Player) sender;
    		}
    		
    		log.info(cast + this.getDescription().getVersion() + " Disabled");
    		
    		try {
    			configFile = new File(getDataFolder(), "config.yml");
    			config = YamlConfiguration.loadConfiguration(configFile);
    			if (!getDataFolder().exists()) {
    				getDataFolder().mkdir();
    			}
    			if (!configFile.exists()) {
    				configFile.createNewFile();
    				config.set("WelcomeMessage", welcomeMessage);
    				config.set("CountMessage", countMessage);
    				config.save(configFile);
    			} else {
    				welcomeMessage = config.getString("WelcomeMessage");
    				countMessage = config.getString("CountMessage");
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		File playerFile = new File("plugins/Essentials/userdata");
    		count = playerFile.listFiles().length;

    		p = new PlayerListener(this);
    		PluginManager pm = this.getServer().getPluginManager();
    		pm.registerEvents(p, this);
    		
    		player.sendMessage(ChatColor.RED + "Unique player was successfully reloaded");
    		log.info(cast + this.getDescription().getVersion() + " Enabled");
    		return true;
    	}
    	return false; 
    }


}
