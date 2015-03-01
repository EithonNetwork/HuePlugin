package se.fredsfursten.hueplugin;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import se.fredsfursten.plugintools.AlarmTrigger;
import se.fredsfursten.plugintools.PluginConfig;

public final class HuePlugin extends JavaPlugin implements Listener {

	private static PluginConfig configuration;

	@Override
	public void onEnable() {
		if (configuration == null) {
			configuration = new PluginConfig(this, "config.yml");
		} else {
			configuration.load();
		}
		getServer().getPluginManager().registerEvents(this, this);		
		HueController.get().enable(this);
		Commands.get().enable(this);
	}

	@Override
	public void onDisable() {
		HueController.get().disable();
		Commands.get().disable();
	}

	public static FileConfiguration getPluginConfig()
	{
		return configuration.getFileConfiguration();
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		HueController.get().playerJoined(player);
	}

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		HueController.get().playerQuit(player);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length < 1) {
			sender.sendMessage("Incomplete command...");
			return false;
		}

		String command = args[0].toLowerCase();
		if (command.equals("on")) {
			Commands.get().onCommand(sender, args);
		} else if (command.equals("off")) {
			Commands.get().offCommand(sender, args);
		} else if (command.equals("get")) {
			Commands.get().getCommand(sender, args);
		} else if (command.equals("color")) {
			Commands.get().colorCommand(sender, args);
		} else if (command.equals("bright")) {
			Commands.get().brightnessCommand(sender, args);
		} else {
			sender.sendMessage("Could not understand command.");
			return false;
		}
		return true;
	}
}
