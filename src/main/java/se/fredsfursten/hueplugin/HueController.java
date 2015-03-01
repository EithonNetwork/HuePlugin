package se.fredsfursten.hueplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import se.fredsfursten.plugintools.PhilipsHue;
import se.fredsfursten.plugintools.PhilipsHueInfo;

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

	public void on(CommandSender sender) {
		PhilipsHue.on();
	}
	public void off(CommandSender sender) {
		PhilipsHue.off();
	}
	public void playerJoined(Player player) {
		blink(1.0, 0.38, 1.0);
	}
	
	public void playerQuit(Player player) {
		blink(1.0, 0.0, 1.0);
	}

	public void changeColor(CommandSender sender, double saturation, double hue) {
		PhilipsHue.changeAll(saturation, hue, 1.0);
	}

	public void changeBrightness(CommandSender sender, double brightness) {
		PhilipsHue.changeBrightness(brightness);
	}

	public void getData(CommandSender sender) {
		String message = PhilipsHue.getData();
		sender.sendMessage(message);
	}
	
	private void blink(double saturation, double hue, double brightness) {
		PhilipsHueInfo lamp = PhilipsHue.getLamp();
		if (!lamp.isOn()) return;
		PhilipsHue.dark();
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(this._plugin, new Runnable() {
			public void run() {
				PhilipsHue.changeAll(saturation, hue, brightness);
			}
		}, 10L);
		scheduler.scheduleSyncDelayedTask(this._plugin, new Runnable() {
			public void run() {
				PhilipsHue.dark();
			}
		}, 50L);
		scheduler.scheduleSyncDelayedTask(this._plugin, new Runnable() {
			public void run() {
				PhilipsHue.changeAll(lamp.getSat(), lamp.getHue(), lamp.getBri());
			}
		}, 70L);
	}
}
