package me.jesusmx.lunar.runnable;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.jesusmx.lunar.NameTags;
import me.jesusmx.lunar.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.stream.Collectors;

public class NameTagRunnable extends BukkitRunnable {

    private final NameTags instance;

    public NameTagRunnable(NameTags instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        for (Player player : this.instance.getServer().getOnlinePlayers()) {
            List<String> show = null;
            for (String s : this.instance.getConfig().getConfigurationSection("lunar-nametags").getKeys(false)) {
                String path = "lunar-nametags." + s + ".";
                if (player.hasPermission(this.instance.getConfig().getString(path + "permission"))) {
                    show = this.instance.getConfig().getStringList(path + "show");
                    break;
                }
            }

            if (show == null || show.isEmpty()) return;
            show = show.stream().map(s -> s.replace("%player-name%", player.getDisplayName())).collect(Collectors.toList());
            List<String> finalShow = show;

            for (Player target : this.instance.getServer().getOnlinePlayers()) {
                LunarClientAPI.getInstance().overrideNametag(player, Color.translate(finalShow), target);
            }
        }
    }
}
