package banknotysm.commands;

import banknotysm.BanknotySM;
import banknotysm.configs.Config;
import banknotysm.util.BanknotyUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class BanknotCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Config config = BanknotySM.getInternalConfig();
        Player p = (Player) sender;
        PlayerInventory inv = p.getInventory();
        if (args.length >= 1) {
// /banknot kup 1
            if(inv.firstEmpty() >0) {
                if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase("5") ||
                            args[1].equalsIgnoreCase("10") || args[1].equalsIgnoreCase("20") || args[1].equalsIgnoreCase("50") || args[1].equalsIgnoreCase("100")) {
                        if (p.hasPermission(BanknotyUtil.BANKNOT_GIVE_SELF_PERM)) {

                            ItemStack banknot = BanknotyUtil.createBanknot(Integer.parseInt(args[1]));
                            if (args[0].equalsIgnoreCase("kup")) {
                                if (BanknotySM.getEconomy().getBalance(p) >= Integer.parseInt(args[1])) {
                                    inv.addItem(banknot);
                                    BanknotySM.getEconomy().withdrawPlayer(p, Integer.parseInt(args[1]));
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.AQUA + config.getBuyBanknotMessage()).replaceAll("%PLAYER%", p.getDisplayName()));
                                } else {
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.AQUA + config.getNoMoneyMessage()));
                                }
                            }
                            //Need a change - need a method to compare ItemStack with customitem - equals don't work, isSimillar same
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.AQUA + config.getNoPermMessage()));

                        }
                    }
                } else if (args[0].equalsIgnoreCase("sprzedaj")) {
                    String lore = p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0);
                    if (p.getInventory().getItemInMainHand().getType() != Material.AIR && lore != null) {
                        String mess = ChatColor.translateAlternateColorCodes('&', config.getSellBanknotMessage()).replaceAll("%PLAYER%", p.getDisplayName());
                        ItemStack item = p.getInventory().getItemInMainHand();
                        int howmany = item.getAmount();
                        if (lore.equals("Banknot o wartości 1$")) {
                            inv.removeItem(item);
                            BanknotySM.getEconomy().depositPlayer(p, howmany);
                            p.sendMessage(ChatColor.AQUA + mess);
                        } else if (lore.equals("Banknot o wartości 2$")) {
                            inv.removeItem(item);
                            BanknotySM.getEconomy().depositPlayer(p, 2 * howmany);
                            p.sendMessage(ChatColor.AQUA + mess);
                        } else if (lore.equals("Banknot o wartości 5$")) {
                            inv.removeItem(item);
                            BanknotySM.getEconomy().depositPlayer(p, 5 * howmany);
                            p.sendMessage(ChatColor.AQUA + mess);
                        } else if (lore.equals("Banknot o wartości 10$")) {
                            inv.removeItem(item);
                            BanknotySM.getEconomy().depositPlayer(p, 10 * howmany);
                            p.sendMessage(ChatColor.AQUA + mess);
                        } else if (lore.equals("Banknot o wartości 20$")) {
                            inv.removeItem(item);
                            BanknotySM.getEconomy().depositPlayer(p, 20 * howmany);
                            p.sendMessage(ChatColor.AQUA + mess);
                        } else if (lore.equals("Banknot o wartości 50$")) {
                            inv.removeItem(item);
                            BanknotySM.getEconomy().depositPlayer(p, 50 * howmany);
                            p.sendMessage(ChatColor.AQUA + mess);
                        } else if (lore.equals("Banknot o wartości 100$")) {
                            inv.removeItem(item);
                            BanknotySM.getEconomy().depositPlayer(p, 100 * howmany);
                            p.sendMessage(ChatColor.AQUA + mess);
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.AQUA + config.getNoBanknotInHandMessage()));
                        }
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (p.hasPermission(BanknotyUtil.BANKNOT_RELOAD_PERM)) {
                        config.reloadConfig();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.AQUA + config.getReloadMessage()));

                        return true;
                    }
                }
            }
            else{
                p.sendMessage(ChatColor.RED+"Nie masz wolnego miejsca w ekwipunku!");
            }
        }
            else{
            p.sendMessage(ChatColor.AQUA+"Poprawne uzycie: /banknoty kup/sprzedaj (1,2,5,10,20,50,100)");

        } return false;
    }
}

