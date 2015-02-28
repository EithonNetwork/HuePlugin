package se.fredsfursten.hueplugin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import se.fredsfursten.plugintools.PhilipsHue;

public class HueController {

	private static HueController singleton = null;

	private JavaPlugin _plugin = null;

	private HueController() {
	}

	static HueController get()
	{
		if (singleton == null) {
			singleton = new HueController();
		}
		return singleton;
	}

	void enable(JavaPlugin plugin){
		this._plugin = plugin;
	}

	void disable() {
		this._plugin = null;
	}

	public void on(Player player) {
		PhilipsHue.on();
	}
	public void off(Player player) {
		PhilipsHue.off();
	}
	public void playerJoined(Player player) {
	}
}
