package banknotysm.configs;

import banknotysm.BanknotySM;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    public static final String NO_PERM_KEY = "locale.noperm";
    public static final String INVALID_PLAYER_KEY = "locale.invalidplayer";
    public static final String ONLY_PLAYER_KEY = "locale.onlyplayers";
    public static final String GIVE_KEY = "locale.givebanknot";
    public static final String RELOAD_KEY = "locale.reload";

    private String noPermMessage, invalidPlayerMessage, onlyPlayersMessage, giveBanknotMessage,reloadMessage;

    public Config(){
        setDefaults();
        loadConfig();
    }

    public void loadConfig(){
        FileConfiguration config = BanknotySM.getInstance().getConfig();

        noPermMessage = config.getString(NO_PERM_KEY,"&4Nie masz do tego permisji");
        invalidPlayerMessage = config.getString(INVALID_PLAYER_KEY,"&4To nie jest prawidłowy gracz!");
        onlyPlayersMessage = config.getString(ONLY_PLAYER_KEY,"Tylko gracz moze uzyc tej komendy");
        giveBanknotMessage = config.getString(GIVE_KEY,"&2Daj banknot dla %PLAYER%!");
        reloadMessage = config.getString(RELOAD_KEY,"&2[Config Banknotow Zreloadowany!");
    }

    public void setDefaults(){
        FileConfiguration config = BanknotySM.getInstance().getConfig();

        config.addDefault(NO_PERM_KEY,"&4Nie masz do tego permisji");
        config.addDefault(INVALID_PLAYER_KEY,"&4To nie jest prawidłowy gracz!");
        config.addDefault(ONLY_PLAYER_KEY,"Tylko gracz moze uzyc tej komendy");
        config.addDefault(GIVE_KEY,"&2Daj banknot dla %PLAYER%!");
        config.addDefault(RELOAD_KEY,"&2[Config Banknotow Zreloadowany!");

        config.options().copyDefaults(true);
        BanknotySM.getInstance().saveConfig();
    }

    public void saveConfig(){
        FileConfiguration config = BanknotySM.getInstance().getConfig();

        config.set(NO_PERM_KEY,noPermMessage);
        config.set(INVALID_PLAYER_KEY,invalidPlayerMessage);
        config.set(ONLY_PLAYER_KEY,onlyPlayersMessage);
        config.set(GIVE_KEY,giveBanknotMessage);
        config.set(RELOAD_KEY,reloadMessage);

        BanknotySM.getInstance().saveConfig();
    }

    public void reloadConfig(){
        loadConfig();
    }


    public void setNoPermMessage(String message){
        this.noPermMessage = message;
    }
    public String getNoPermMessage() {
        return noPermMessage;
    }

    public void setInvalidPlayerMessage(String message){
        this.invalidPlayerMessage = message;
    }

    public String getInvalidPlayerMessage(){
        return invalidPlayerMessage;
    }

    public void setOnlyPlayersMessage(String message) {
        this.onlyPlayersMessage = message;
    }

    public String getOnlyPlayersMessage(){
        return onlyPlayersMessage;
    }

    public void setGiveBanknotMessage(String message){
        this.giveBanknotMessage = message;
    }

    public String getGiveBanknotMessage(){
        return giveBanknotMessage;
    }

    public void setReloadMessage(String message){
        this.reloadMessage = message;
    }

    public String getReloadMessage(){
        return reloadMessage;
    }
}
