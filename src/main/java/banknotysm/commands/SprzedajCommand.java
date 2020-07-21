package banknotysm.commands;

import banknotysm.util.BanknotyUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SprzedajCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            if (sender instanceof Player) {
                if (BanknotyUtil.isItemWithName(((Player) sender).getItemInHand(),Material.PAPER,"Banknot o wartości 1$"));
                //later - need to install vault
            }
        }




        return false;
    }
}
