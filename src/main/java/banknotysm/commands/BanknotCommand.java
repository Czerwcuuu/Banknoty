package banknotysm.commands;

import banknotysm.BanknotySM;
import banknotysm.util.BanknotyUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BanknotCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (args.length >= 1) {
// /banknot kup 1
            if (args.length == 3) {
                    if (args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase("5") ||
                        args[1].equalsIgnoreCase("10") || args[1].equalsIgnoreCase("20") || args[1].equalsIgnoreCase("50") || args[1].equalsIgnoreCase("100")) {
                        if (sender.hasPermission(BanknotyUtil.BANKNOT_GIVE_SELF_PERM)) {

                        ItemStack banknot = BanknotyUtil.createBanknot(Integer.parseInt(args[1]));
                            if(args[0].equals("kup")) {
                                p.getInventory().addItem(banknot);
                                if(BanknotySM.getEconomy().has(p,Integer.parseInt(args[1]))){
                                    BanknotySM.getEconomy().depositPlayer(p,-Integer.parseInt(args[1]));
                                }
                                else{
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getNoMoneyMessage()));
                                }
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getBuyBanknotMessage()).replaceAll("%PLAYER%", p.getDisplayName()));
                            }
                            else if (args[0].equals("sprzedaj")){
                                if(p.getInventory().getItemInMainHand() == banknot) {
                                    p.getInventory().removeItem(banknot);
                                    BanknotySM.getEconomy().depositPlayer(p,Integer.parseInt(args[1]));
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getSellBanknotMessage()));
                                } else {
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getNoBanknotInHandMessage()));
                                }
                            }
                        } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getNoPermMessage()));

                        }
                    }


            } else if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission(BanknotyUtil.BANKNOT_RELOAD_PERM)) {
                    BanknotySM.getInternalConfig().reloadConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getReloadMessage()));

                    return true;
                }
            }
        }else{
            p.sendMessage("Poprawne uzycie: /banknoty kup/sprzedaj (1,2,5,10,20,50,100)");

        } return false;
    }
}

