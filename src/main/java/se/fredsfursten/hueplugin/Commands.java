package se.fredsfursten.hueplugin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands {
	private static Commands singleton = null;
	private static final String ON_COMMAND = "/donationboard on";
	private static final String OFF_COMMAND = "/donationboard off";
	private static final String GET_COMMAND = "/donationboard get";
	private static final String COLOR_COMMAND = "/donationboard color <saturation> <hue>";
	private static final String BRIGHT_COMMAND = "/donationboard bright <brightness>";

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

	void onCommand(CommandSender sender, String[] args)
	{
		if (!verifyPermission(sender, "donationboard.on")) return;
		if (!arrayLengthIsWithinInterval(args, 1, 1)) {
			sender.sendMessage(ON_COMMAND);
			return;
		}

		HueController.get().on(sender);
	}

	void offCommand(CommandSender sender, String[] args)
	{
		if (!verifyPermission(sender, "donationboard.off")) return;
		if (!arrayLengthIsWithinInterval(args, 1, 1)) {
			sender.sendMessage(OFF_COMMAND);
			return;
		}

		HueController.get().off(sender);
	}
	
	void getCommand(CommandSender sender, String[] args)
	{
		if (!verifyPermission(sender, "donationboard.get")) return;
		if (!arrayLengthIsWithinInterval(args, 1, 1)) {
			sender.sendMessage(GET_COMMAND);
			return;
		}

		HueController.get().getData(sender);
	}

	private boolean verifyPermission(CommandSender sender, String permission)
	{
		if (sender.hasPermission(permission)) return true;
		sender.sendMessage("You must have permission " + permission);
		return false;
	}

	private boolean arrayLengthIsWithinInterval(Object[] args, int min, int max) {
		return (args.length >= min) && (args.length <= max);
	}

	public void colorCommand(CommandSender sender, String[] args) 
	{
		if (!verifyPermission(sender, "donationboard.color")) return;
		if (!arrayLengthIsWithinInterval(args, 3, 3)) {
			sender.sendMessage(COLOR_COMMAND);
			return;
		}
		
		double saturation = Double.parseDouble(args[1]);
		double hue = Double.parseDouble(args[2]);

		HueController.get().changeColor(sender, saturation, hue);
	}

	public void brightnessCommand(CommandSender sender, String[] args) 
	{
		if (!verifyPermission(sender, "donationboard.bright")) return;
		if (!arrayLengthIsWithinInterval(args, 2, 2)) {
			sender.sendMessage(BRIGHT_COMMAND);
			return;
		}
		
		double brightness = Double.parseDouble(args[1]);

		HueController.get().changeBrightness(sender, brightness);
	}
}
