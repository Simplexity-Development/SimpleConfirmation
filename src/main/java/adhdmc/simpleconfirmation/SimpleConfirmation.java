package adhdmc.simpleconfirmation;

import adhdmc.simpleconfirmation.listener.ConfirmCommandListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleConfirmation extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ConfirmCommandListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
