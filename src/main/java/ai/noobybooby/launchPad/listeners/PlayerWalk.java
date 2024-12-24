package ai.noobybooby.launchPad.listeners;

import ai.noobybooby.launchPad.LaunchPad;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerWalk implements Listener {
    private final LaunchPad plugin;
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    private final Set<UUID> launchedPlayers = new HashSet<>();

    public PlayerWalk(LaunchPad plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent event) {
        if (plugin.getConfig().getBoolean("enable-plugin")) {
            Player player = event.getPlayer();

            if (!player.hasPermission("launchpad.use")) {
                return;
            }

            Location underBlock = player.getLocation().clone();
            underBlock.setY(underBlock.getY() - 1);

            if (player.getLocation().getBlock().getType().equals(Material.valueOf(plugin.getConfig().getString("top-block"))) &&
                    underBlock.getBlock().getType().equals(Material.valueOf(plugin.getConfig().getString("under-block")))) {
                player.setVelocity(player.getLocation().getDirection()
                        .multiply(plugin.getConfig().getDouble("multiply-velocity"))
                        .setY(plugin.getConfig().getDouble("y-velocity")));
                launchedPlayers.add(player.getUniqueId());
                if (plugin.getConfig().getBoolean("message")) {
                    UUID playerId = player.getUniqueId();
                    long currentTime = System.currentTimeMillis();
                    if (!cooldowns.containsKey(playerId) || (currentTime - cooldowns.get(playerId) >= plugin.getConfig().getInt("message-cooldown"))) {
                        String launchMessage = plugin.getConfig().getString("launch-message");
                        if (launchMessage != null) {
                            player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(launchMessage));
                        }
                        cooldowns.put(playerId, currentTime);
                    }
                }
            }
        }
    }

    public Set<UUID> getLaunchedPlayers() {
        return launchedPlayers;
    }
}