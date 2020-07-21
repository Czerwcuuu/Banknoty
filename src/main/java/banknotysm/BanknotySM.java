package banknotysm;

import banknotysm.commands.BanknotCommand;
import banknotysm.configs.Config;
import banknotysm.util.BanknotyUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class BanknotySM extends JavaPlugin {

    private final Logger logger = Logger.getLogger("banknotysm");

    private static Config config;

    private static BanknotySM plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        this.getCommand("banknoty").setExecutor(new BanknotCommand());
        config = new Config();
        BanknotyUtil.registerPermissions();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        BanknotyUtil.unregisterPermissions();
    }

    public Logger getLogger(){
        return logger;
    }

    public static BanknotySM getInstance(){
        return plugin;
    }

    public static Config getInternalConfig(){
        return config;
    }
}
