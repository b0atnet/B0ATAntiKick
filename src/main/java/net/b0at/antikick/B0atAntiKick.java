package net.b0at.antikick;

import net.b0at.antikick.util.AntiKickConfig;
import net.b0at.torsion.FileStorage;
import net.b0at.torsion.Storage;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jordin on 7/15/2017.
 * Jordin is still best hacker.
 */
public class B0atAntiKick extends JavaPlugin implements Listener {
    private String message;

    private List<String> allowedKickReasons;

    public B0atAntiKick() {
        try {
            FileStorage.setBaseDirectory(getDataFolder());
            Storage<AntiKickConfig> antiKickConfigStorage = FileStorage.of(AntiKickConfig.class, "config.yml");
            AntiKickConfig config = antiKickConfigStorage.load();

            message = ChatColor.translateAlternateColorCodes('&', config.prefix + config.preventedKickMessage);
            allowedKickReasons = config.allowedKickReasons;
            antiKickConfigStorage.save(config);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerKickEvent(PlayerKickEvent kickEvent) {
        String reason = kickEvent.getReason();
        for (String allowedReason : allowedKickReasons) {
            if (reason.contains(allowedReason)) {
                return;
            }
        }

        if (reason.startsWith("-f")) {
            kickEvent.setReason(kickEvent.getReason().replaceFirst("-f", "").trim());
        } else {
            kickEvent.setCancelled(true);
            kickEvent.getPlayer().sendMessage(String.format(message, reason));
        }
    }
}
