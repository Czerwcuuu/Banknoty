package banknotysm.commands;

import banknotysm.BanknotySM;
import banknotysm.util.BanknotyUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BanknotCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (args.length >= 1) {
// /banknot kup 1
            if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase("5") ||
                        args[1].equalsIgnoreCase("10") || args[1].equalsIgnoreCase("20") || args[1].equalsIgnoreCase("50") || args[1].equalsIgnoreCase("100")) {
                        if (sender.hasPermission(BanknotyUtil.BANKNOT_GIVE_SELF_PERM)) {

                        ItemStack banknot = BanknotyUtil.createBanknot(Integer.parseInt(args[1]));
                            if(args[0].equalsIgnoreCase("kup")) {
                                if(BanknotySM.getEconomy().getBalance(p)>=Integer.parseInt(args[1])){
                                    p.getInventory().addItem(banknot);
                                    BanknotySM.getEconomy().withdrawPlayer(p,Integer.parseInt(args[1]));
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getBuyBanknotMessage()).replaceAll("%PLAYER%", p.getDisplayName()));
                                }
                                else{
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getNoMoneyMessage()));
                                }
                            }
                            //Need a change - need a method to compare ItemStack with customitem - equals don't work, isSimillar same
                        } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getNoPermMessage()));

                        }
                    }
            }
            else if (args[0].equalsIgnoreCase("sprzedaj")){
                if(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).equals("Banknot o wartości 1$")) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    int howmany = item.getAmount();
                    p.getInventory().removeItem(item);
                    BanknotySM.getEconomy().depositPlayer(p,1 * howmany);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getSellBanknotMessage()).replaceAll("%PLAYER%", p.getDisplayName()));
                }
                else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).equals("Banknot o wartości 2$")){
                    ItemStack item = p.getInventory().getItemInMainHand();
                    int howmany = item.getAmount();
                    p.getInventory().removeItem(item);
                    BanknotySM.getEconomy().depositPlayer(p,2 * howmany);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getSellBanknotMessage()).replaceAll("%PLAYER%", p.getDisplayName()));
                }
                else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).equals("Banknot o wartości 5$")){
                    ItemStack item = p.getInventory().getItemInMainHand();
                    int howmany = item.getAmount();
                    p.getInventory().removeItem(item);
                    BanknotySM.getEconomy().depositPlayer(p,5 * howmany);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getSellBanknotMessage()).replaceAll("%PLAYER%", p.getDisplayName()));
                }
                else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).equals("Banknot o wartości 10$")){
                    ItemStack item = p.getInventory().getItemInMainHand();
                    int howmany = item.getAmount();
                    p.getInventory().removeItem(item);
                    BanknotySM.getEconomy().depositPlayer(p,10 * howmany);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getSellBanknotMessage()).replaceAll("%PLAYER%", p.getDisplayName()));
                }
                else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).equals("Banknot o wartości 20$")){
                    ItemStack item = p.getInventory().getItemInMainHand();
                    int howmany = item.getAmount();
                    p.getInventory().removeItem(item);
                    BanknotySM.getEconomy().depositPlayer(p,20 * howmany);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getSellBanknotMessage()).replaceAll("%PLAYER%", p.getDisplayName()));
                }
                else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).equals("Banknot o wartości 50$")){
                    ItemStack item = p.getInventory().getItemInMainHand();
                    int howmany = item.getAmount();
                    p.getInventory().removeItem(item);
                    BanknotySM.getEconomy().depositPlayer(p,50 * howmany);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getSellBanknotMessage()).replaceAll("%PLAYER%", p.getDisplayName()));
                }
                else if(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).equals("Banknot o wartości 100$")){
                    ItemStack item = p.getInventory().getItemInMainHand();
                    int howmany = item.getAmount();
                    p.getInventory().removeItem(item);
                    BanknotySM.getEconomy().depositPlayer(p,100 * howmany);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getSellBanknotMessage()).replaceAll("%PLAYER%", p.getDisplayName()));
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', BanknotySM.getInternalConfig().getNoBanknotInHandMessage()));
                }
            }
            else if (args[0].equalsIgnoreCase("reload")) {
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

