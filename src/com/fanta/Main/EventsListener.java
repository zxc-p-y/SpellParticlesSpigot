package com.fanta.Main;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class EventsListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        e.setJoinMessage(ChatColor.DARK_AQUA+"Hi "+e.getPlayer().getName());
    }

    @EventHandler
    public void onCrunch(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        if (e.isSneaking()) {

            // Testing zone

            ParticleEffects.eatApple(player.getLocation());
        }
    }



}
