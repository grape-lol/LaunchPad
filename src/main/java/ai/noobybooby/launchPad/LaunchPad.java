package ai.noobybooby.launchPad;

import ai.noobybooby.launchPad.listeners.FallDamage;
import ai.noobybooby.launchPad.listeners.PlayerWalk;
import org.bukkit.plugin.java.JavaPlugin;

public final class LaunchPad extends JavaPlugin {
    private PlayerWalk playerWalkListener;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        playerWalkListener = new PlayerWalk(this);
        getServer().getPluginManager().registerEvents(playerWalkListener, this);
        getServer().getPluginManager().registerEvents(new FallDamage(this), this);
    }

    public PlayerWalk getPlayerWalkListener() {
        return playerWalkListener;
    }
}
