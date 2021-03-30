package de.zonlykroks.survival.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.zonlykroks.survival.Main;

public class AbstractConfigFile {

	protected Main main;
	protected static File file;
	protected static FileConfiguration config;
	
	public AbstractConfigFile(Main main, String fileName) {
		this.main = main;
		AbstractConfigFile.file = new File(main.getDataFolder(), fileName);
		if(file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		AbstractConfigFile.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
