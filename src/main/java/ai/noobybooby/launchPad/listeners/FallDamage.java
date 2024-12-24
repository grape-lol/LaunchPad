package ai.noobybooby.launchPad.listeners;

import ai.noobybooby.launchPad.LaunchPad;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.UUID;

public class FallDamage implements Listener {
    private final LaunchPad plugin;

    public FallDamage(LaunchPad plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL) &&
                plugin.getConfig().getBoolean("disable-fall-damage")) {

            Entity entity = event.getEntity();
            if (entity instanceof Player) {
                Player player = (Player) entity;

                if (!player.hasPermission("launchpad.nofalldamage")) {
                    return;
                }

                UUID playerId = player.getUniqueId();
                PlayerWalk playerWalk = plugin.getPlayerWalkListener();
                if (playerWalk.getLaunchedPlayers().contains(playerId)) {
                    event.setCancelled(true);
                    playerWalk.getLaunchedPlayers().remove(playerId);
                }
            }
        }
    }
}
