package net.letmiracle.miracletools;

import net.letmiracle.miracletools.event.PlayerListener;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.mineacademy.fo.platform.BukkitPlugin;

/**
 * PluginTemplate is a simple template you can use every time you make
 * a new plugin. This will save you time because you no longer have to
 * recreate the same skeleton and features each time.
 *
 * It uses Foundation for fast and efficient development process.
 */
public final class MiracleTools extends BukkitPlugin {

	/**
	* Automatically perform login ONCE when the plugin starts.
	*/
	@Override
	protected void onPluginStart() {
		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider != null) {
			LuckPerms api = provider.getProvider();
		}

		registerEvents(new PlayerListener());
	}

	@Override
	protected void onPluginPreReload() {

		// Close your database here if you use one
		//YourDatabase.getInstance().close();
	}

	// ------------------------------------------------------------------------------------------------------------
	// Static
	// ------------------------------------------------------------------------------------------------------------

	/**
	 * Return the instance of this plugin, which simply refers to a static
	 * field already created for you in {@link BukkitPlugin} but casts it to your
	 * specific plugin instance for your convenience.
	 *
	 * @return
	 */
	public static MiracleTools getInstance() {
		return (MiracleTools) BukkitPlugin.getInstance();
	}
}
