package me.jesusmx.lunar;

import lombok.Getter;
import me.jesusmx.lunar.runnable.NameTagRunnable;
import me.jesusmx.lunar.utils.Color;
import me.jesusmx.lunar.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class NameTags extends JavaPlugin {

    private Config config;
    private NameTagRunnable nameTagRunnable;

    @Override
    public void onEnable() {
        this.config = new Config(this, "config");
        this.nameTagRunnable = new NameTagRunnable(this);
        this.nameTagRunnable.runTaskTimerAsynchronously(this, 5L, 10L);
        this.getServer().getConsoleSender().sendMessage(Color.translate("&b[LunarNametags] &ahas been registered"));
    }
}
