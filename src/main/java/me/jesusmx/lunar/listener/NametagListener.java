package me.jesusmx.lunar.listener;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.jesusmx.lunar.nametags;
import me.jesusmx.lunar.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.stream.Collectors;

public class NametagListener implements Listener {

    private FileConfiguration config = nametags.getInstance().getConfig();

    @EventHandler
    public void lunarTags(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(nametags.getInstance(), () -> {
            Player player = event.getPlayer();
            List<String> show = null;
            for(String s : config.getConfigurationSection("lunar-nametags").getKeys(false)) {
                String path = "lunar-nametags." + s + ".";
                if(player.hasPermission(config.getString(path + "permission"))) {
                    show = config.getStringList(path + "show");
                    break;
                }
            }

            if(show == null || show.isEmpty()) return;
            show = show.stream().map(s -> s.replace("%player-name%", player.getDisplayName())).collect(Collectors.toList());
            List<String> finalShow = show;
            Bukkit.getOnlinePlayers().forEach(target -> {
                LunarClientAPI.getInstance().overrideNametag(player, Color.translate(finalShow), target);
            });
        }, 5L);
    }
}
