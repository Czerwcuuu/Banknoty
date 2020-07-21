package banknotysm.commands;

import banknotysm.BanknotySM;
import banknotysm.util.BanknotyUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BanknotCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {

            if (sender.hasPermission(BanknotyUtil.BANKNOT_GIVE_SELF_PERM)) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    ItemStack banknot = BanknotyUtil.createBanknot(1);
                    player.getInventory().addItem(banknot);

                    return true;
                } else return false;
            } else return false;
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("5") ||
                    args[0].equalsIgnoreCase("10") || args[0].equalsIgnoreCase("20") || args[0].equalsIgnoreCase("50") ||
                    args[0].equalsIgnoreCase("100")) {
                //Sprawdz czy egzekutor ma permisje do dawania destroyera innym
                if (sender.hasPermission(BanknotyUtil.BANKNOT_GIVE_SELF_PERM)) {
                    //Pobierz informacje o graczu z tym nickiem
                    Player player = (Player) sender;

                    //Stworz destroyera
                    ItemStack banknot = BanknotyUtil.createBanknot(Integer.parseInt(args[0]));
                    player.getInventory().addItem(banknot);

                    //Wyslij informacje o przekazaniu destroyera
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getGiveBanknotMessage()).replaceAll("%PLAYER%", player.getDisplayName()));

                    return true;
                } else {
                    //Nie ma permisji do dawania innym graczom
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getNoPermMessage()));

                    return false;
                }
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission(BanknotyUtil.BANKNOT_RELOAD_PERM)) {
                    BanknotySM.getInternalConfig().reloadConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getReloadMessage()));

                    return true;
                } else return false;
            } else return false;
        } else return false;
    }
}
