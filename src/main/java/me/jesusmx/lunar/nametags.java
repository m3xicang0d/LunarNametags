package me.jesusmx.lunar;

import lombok.Getter;
import me.jesusmx.lunar.listener.NametagListener;
import me.jesusmx.lunar.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class nametags extends JavaPlugin {

    @Getter private static nametags instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();

        if (!this.getDescription().getAuthors().contains("JesusMX") || !this.getDescription().getName().equals("LunarNametags") || !this.getDescription().getDescription().equals("Lunar Nametags")) {
            System.exit(0);
            Bukkit.shutdown();
        }
        this.getServer().getConsoleSender().sendMessage(Color.translate("&b[LunarNametags] &ahas been registered"));
        this.getServer().getPluginManager().registerEvents(new NametagListener(), this);
    }

    @Override
    public void onDisable() {
    }

}
