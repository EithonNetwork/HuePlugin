package se.fredsfursten.hueplugin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands {
	private static Commands singleton = null;
	private static final String ON_COMMAND = "/donationboard on";
	private static final String OFF_COMMAND = "/donationboard off";

	private JavaPlugin _plugin = null;

	private Commands() {
	}

	static Commands get()
	{
		if (singleton == null) {
			singleton = new Commands();
		}
		return singleton;
	}

	void enable(JavaPlugin plugin){
		this._plugin = plugin;
	}

	void disable() {
	}

	void onCommand(Player player, String[] args)
	{
		if (!verifyPermission(player, "donationboard.on")) return;
		if (!arrayLengthIsWithinInterval(args, 1, 1)) {
			player.sendMessage(ON_COMMAND);
			return;
		}

		HueController.get().on(player);
	}

	void offCommand(Player player, String[] args)
	{
		if (!verifyPermission(player, "donationboard.off")) return;
		if (!arrayLengthIsWithinInterval(args, 1, 1)) {
			player.sendMessage(OFF_COMMAND);
			return;
		}

		HueController.get().off(player);
	}

	private boolean verifyPermission(Player player, String permission)
	{
		if (player.hasPermission(permission)) return true;
		player.sendMessage("You must have permission " + permission);
		return false;
	}

	private boolean arrayLengthIsWithinInterval(Object[] args, int min, int max) {
		return (args.length >= min) && (args.length <= max);
	}
}
