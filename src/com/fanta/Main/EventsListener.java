package com.fanta.Main;


import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


public class EventsListener implements Listener {

    private Main main;

    public EventsListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        e.setJoinMessage(ChatColor.DARK_AQUA+"Hi "+e.getPlayer().getName());
    }

    @EventHandler
    public void onCrunch(PlayerToggleSneakEvent e) {
        // Testing zone

        Player player = e.getPlayer();
        if (player.isSneaking()){//если произойдет действие(action == true) появится круг
//            ParticleEffects.immolationEffect(player, main);
            player.setVelocity(new Vector(0, 1, 0));
            ParticleEffects.bigJump(player, main);
        }


    }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            if (Utils.getMetadata(player, Strings.mdHitKey, main) != null) {
                Bukkit.getScheduler().cancelTask( Utils.getMetadataInt(player, Strings.mdHitKey, main) );
                Utils.setMetadata(player, Strings.mdHitKey, null, main);
            }
        }
    }

}
