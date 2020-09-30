package com.fanta.Main;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

public class ParticleEffects {


    //Particle.FLASH = flash as in CS GO

    public static void bashEffect(Location loc, Intencity intensity) {
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            int maxInt = 6;
            double randomX = rand.nextInt(maxInt) - maxInt/2;
            double randomY = rand.nextInt(maxInt) - maxInt/2;
            double randomZ = rand.nextInt(maxInt) - maxInt/2;
            Location newLoc = loc.add(randomX/10, randomY/10, randomZ/10);
            loc.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, newLoc, 1);
        }
    }

    public static void critEffect(Location loc, Intencity intencity) {
        Random rand = new Random();
        int count = 2;
        switch (intencity) {
            case Normal: count = 2; break;
            case High: count = 4; break;
            case Massive: count = 6; break;
            default: break;
        }
        for (int i = 0; i < count; i++) {
            double randomX = rand.nextInt(70) * 0.01;
            double randomY= rand.nextInt(50) * 0.01;
            double randomZ = rand.nextInt(70) * 0.01;
            loc.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, loc.add(randomX, randomY, randomZ), 1);
        }
    }

//    public static void immolationEffect(Location loc) {
//        Random rand = new Random();
//        for (int i = 0; i < 2; i++) {
//            double randomX = rand.nextInt(70) * 0.01;
//            double randomY= rand.nextInt(50) * 0.01;
//            double randomZ = rand.nextInt(70) * 0.01;
//            loc.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, loc.add(randomX, randomY, randomZ), 1);
//        }
//    }


    //return total ticks before reaching final point

    private static ArrayList<Location> getLocationsBetween(Location loc1, Location loc2) {
        Location cLoc1 = between(loc1, loc2);// calculated location 1
        Location cLoc2 = between(loc1, cLoc1);
        Location cLoc3 = between(cLoc1, loc2);
        Location cLoc4 = between(cLoc2, loc1);
        Location cLoc5 = between(cLoc1, cLoc2);
        Location cLoc6 = between(cLoc1, cLoc3);
        Location cLoc7 = between(cLoc3, loc2);

        //filling array
        ArrayList<Location> locs = new ArrayList<>();
        locs.add(loc1);locs.add(cLoc2);locs.add(cLoc1);locs.add(cLoc3);locs.add(loc2);
        locs.add(cLoc4);locs.add(cLoc5);locs.add(cLoc6);locs.add(cLoc7);
        return locs;
    }

    public static int drawTracer(Location loc1, Location loc2, Main main) {
        //presenting them
        int addTicks = 0;
        for (Location loc : getLocationsBetween(loc1, loc2)) {
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    loc1.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, loc, 0, 0,0,0, 1);
                    loc1.getWorld().spawnParticle(Particle.FLAME, loc, 0, 0,0,0, 1);
                }
            };
            task.runTaskLater(main, addTicks);
            addTicks+=2;
        }
        return addTicks;
    }

    public static void drawMjolnir(ArrayList<Location> locs, Main main) {
        Location prevLoc = null;
        int afterTicks = 0;
        for (Location loc : locs) {
            if (prevLoc == null) {
                prevLoc = loc;
            }
            afterTicks = drawParticleLine(prevLoc, loc, afterTicks, 48, 221, 255, main);
            prevLoc = loc;
        }
    }

    //returning new ticks
    public static int drawParticleLine(Location loc1, Location loc2, int afterTicks, int r, int g, int b, Main main) {
        for (Location loc : getLocationsBetween(loc1, loc2)) {
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
//                	loc.getWorld().spawnParticle(Particle.SPELL_MOB, loc, 0, r/255, g/255, b/255, 1);
                    loc.getWorld().spawnParticle(Particle.REVERSE_PORTAL, loc, 1);
                }
            };
            task.runTaskLater(main, afterTicks);
        }
        return afterTicks+2;
    }

    public static void drawCircle(Location center, int radius, int r, int g, int b) {
        int scaleX = radius;  // use these to tune the size of your circle
        int scaleZ = radius;
        double density = 0.04;  // smaller numbers make the particles denser

        for (double i=0; i < 2 * Math.PI ; i +=density) {
            double x = Math.cos(i) * scaleX;
            double z = Math.sin(i) * scaleZ;
//		     center.getWorld().spawnParticle(Particle.SPELL_MOB, center.getX()+x, center.getY(), center.getZ()+z, 0, 255/255, 0/255, 0/255, 1);
            center.getWorld().spawnParticle(Particle.REDSTONE, center.getX()+x, center.getY(), center.getZ()+z,
                    0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.fromBGR(b, g, r), 1));
        }
    }
    public static void drawCircle(Location center, double radius, double y_up, int r, int g, int b) {
        double scaleX = radius;  // use these to tune the size of your circle
        double scaleZ = radius;
        double density = 0.04;  // smaller numbers make the particles denser

        for (double i=0; i < 2 * Math.PI ; i +=density) {
            double x = Math.cos(i) * scaleX;
            double z = Math.sin(i) * scaleZ;
//		     center.getWorld().spawnParticle(Particle.SPELL_MOB, center.getX()+x, center.getY(), center.getZ()+z, 0, 255/255, 0/255, 0/255, 1);
            center.getWorld().spawnParticle(Particle.REDSTONE, center.getX()+x, center.getY()+y_up, center.getZ()+z,
                    0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.fromBGR(b, g, r), 1));
        }
    }

    public static void drawMidas(Location loc) {
        loc.getWorld().spawnParticle(Particle.DRIP_LAVA, 		Utils.addingLoc(loc, 0, 1.7, 0), 1, 0,0,0); // head
        loc.getWorld().spawnParticle(Particle.DRIP_LAVA, 		Utils.addingLoc(loc, 0, 1.1, 0), 1, 0,0,0); // heart
        loc.getWorld().spawnParticle(Particle.DRIPPING_HONEY, 	Utils.addingLoc(loc, 0, 1.7, 0), 1, 0,0,0); // head
        loc.getWorld().spawnParticle(Particle.DRIPPING_HONEY, 	Utils.addingLoc(loc, 0, 1.1, 0), 1, 0,0,0); // heart

        loc.getWorld().spawnParticle(Particle.DRIP_LAVA, 		Utils.addingLoc(loc, 0.35, 1.2, 0), 1, 0,0,0); //hands
        loc.getWorld().spawnParticle(Particle.DRIP_LAVA, 		Utils.addingLoc(loc, -0.35, 1.2, 0), 1, 0,0,0); //hands

        loc.getWorld().spawnParticle(Particle.DRIP_LAVA, 		Utils.addingLoc(loc, 0, 0.75, 0), 1, 0,0,0); //middle

        loc.getWorld().spawnParticle(Particle.DRIP_LAVA, 		Utils.addingLoc(loc, 0.15, 0.3, 0), 1, 0,0,0); //foots
        loc.getWorld().spawnParticle(Particle.DRIP_LAVA, 		Utils.addingLoc(loc, -0.15, 0.3, 0), 1, 0,0,0); //foots

    }

    // Utils

    private static Location between(Location loc1, Location loc2) {
        return new Location(loc1.getWorld(),
                (loc1.getX()+loc2.getX())/2,
                (loc1.getY()+loc2.getY())/2,
                (loc1.getZ()+loc2.getZ())/2);
    }

    public static void spawnTowerExplosions(Location loc, Main main, int dispXZ, int dispY) {
        Random rand = new Random();
        // 5 big
        for (int i = 0; i<25; i++) {
            int randomDelay = rand.nextInt(40);
            int randomX = rand.nextInt(dispXZ*2+1)-dispXZ; // 0...6 -> (-3) -> -3...3
            int randomY = rand.nextInt(dispY*2+1)-dispY;
            int randomZ = rand.nextInt(dispXZ*2+1)-dispXZ;
            Location randLocation = new Location(loc.getWorld(), loc.getX()+randomX, loc.getY()+randomY, loc.getZ()+randomZ);
            Bukkit.getScheduler().runTaskLater(main,  new Runnable() {
                public void run() {
                    loc.getWorld().playSound(randLocation, Sound.BLOCK_GRASS_HIT, 1.0F, 1.0F);
                    loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, randLocation, 1);
                }
            }, randomDelay);
        }
        // 7 small explosions
        for (int i = 0; i<27; i++) {
            int randomDelay = rand.nextInt(40);
            int randomX = rand.nextInt(dispXZ*2+1)-dispXZ; // 0...6 -> (-3) -> -3...3
            int randomY = rand.nextInt(dispY*2+1)-dispY;
            int randomZ = rand.nextInt(dispXZ*2+1)-dispXZ;
            Location randLocation = new Location(loc.getWorld(), loc.getX()+randomX, loc.getY()+randomY, loc.getZ()+randomZ);
            Bukkit.getScheduler().runTaskLater(main,  new Runnable() {
                public void run() {
                    loc.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, randLocation, 1);
                }
            }, randomDelay);
        }
        //with 3-5 angry villager effect
        for (int i = 0; i<25; i++) {
            int randomDelay = rand.nextInt(40);
            int randomX = rand.nextInt(dispXZ*2+1)-dispXZ; // 0...6 -> (-3) -> -3...3
            int randomY = rand.nextInt(dispY*2+1)-dispY;
            int randomZ = rand.nextInt(dispXZ*2+1)-dispXZ;
            Location randLocation = new Location(loc.getWorld(), loc.getX()+randomX, loc.getY()+randomY, loc.getZ()+randomZ);
            Bukkit.getScheduler().runTaskLater(main,  new Runnable() {
                public void run() {
                    loc.getWorld().playSound(randLocation, Sound.ENTITY_ENDER_DRAGON_HURT, 0.7F, 1.0F);
                    loc.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, randLocation, 1);
                }
            }, randomDelay);
        }

    }

    public static void eatApple(Location location) {
        location.getWorld().playSound(location, Sound.ENTITY_CAT_EAT, 0.7F, 1.0F);
        location.getWorld().spawnParticle(Particle.SOUL, location, 15);
    }

    public static void vampiricEffect(Player player) {
        // TODO Auto-generated method stub
    }

    public static void dispellEffect(Location location, Main main) {
        Random rand = new Random();
        for (int i = 0; i<30; i++) {
            int randomDelay = rand.nextInt(11);
            Double randomX = rand.nextInt(2)-0.5; // 0...6 -> (-3) -> -3...3
            Double randomY = rand.nextInt(2)-0.5;
            Double randomZ = rand.nextInt(2)-0.5;
            Bukkit.getScheduler().runTaskLater(main,  new Runnable() {
                public void run() {
                    location.getWorld().spawnParticle(Particle.WATER_SPLASH, Utils.addingLoc(location, randomX, 1.3+randomY, randomZ), 10);
                }
            }, randomDelay);
        }
    }

    public static void immolationEffect(Player player, Main main) {
        if (Utils.getMetadata(player, Strings.mdHitKey, main) == null) {
            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BELL_USE, 1.0F, 1.0F);
            int counter = Bukkit.getScheduler().scheduleSyncRepeatingTask(main,  new Runnable() {
                public void run() {
                    ParticleEffects.drawCircle(player.getLocation(), 0.6,0.9, 200,0,0);
                }
            }, 0, 2L);
            Utils.setMetadata(player, Strings.mdHitKey, counter, main);
        }
    }

    public enum Intencity {
        Low,
        Normal,
        High,
        Massive
    }
//    public static void immolationEffect(Player player, Main main){
//        if (Utils.getMetadata(player, Strings.mdHitKey, main) == null) {
//            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BELL_USE, 1.0F, 1.0F);
//            int counter = Bukkit.getScheduler().scheduleSyncRepeatingTask(main,  new Runnable() {
//                public void run() {
//                    player.getLocation().getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, player.getLocation().getX(),player.getLocation().getY()+1,player.getLocation().getZ(), 1);
//                }
//            }, 0, 2L);
//            Utils.setMetadata(player, Strings.mdHitKey, counter, main);
//        }
//    }

    public static void bigJump(Player player, Main main) {
        BukkitRunnable task = new BukkitRunnable() {  @Override
        public void run() {  conesAirFlow(player, main, 35, 6, 10); }
        };
        task.runTaskLater(main, 3L);

        BukkitRunnable task2 = new BukkitRunnable() {  @Override
        public void run() {  conesAirFlow(player, main, 18, 5, 6); }
        };
        task2.runTaskLater(main, 4L);

        BukkitRunnable task3 = new BukkitRunnable() {  @Override
        public void run() {  conesAirFlow(player, main, 9, 4, 3); }
        };
        task3.runTaskLater(main, 5L);
    }


    private static void conesAirFlow(Player player, Main main, int particleNumber, int radius, int offsetNormalized) {
//        player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, Utils.addingLoc(player.getLocation(), 0,-0.3,0), 1);
        ArrayList<Location> locations = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < particleNumber; i++) {
            double randomX = rand.nextInt(radius) - radius/2;
            double randomY = rand.nextInt(radius) - radius/2;
            double randomZ = rand.nextInt(radius) - radius/2;
            locations.add(player.getLocation().add(randomX/10, randomY/10+.8, randomZ/10));
        }
//        locations.add( Utils.addingLoc(player.getLocation(), 1,0,2) );
//        locations.add(Utils.addingLoc(player.getLocation(), 1.5,1,1));
//        locations.add(Utils.addingLoc(player.getLocation(), 0.5,-0.5,-1));
        for (Location loc : locations) {
            double d1 = rand.nextInt(offsetNormalized) - offsetNormalized/2;
            double d2 = rand.nextInt(offsetNormalized) - offsetNormalized/2;
            player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 0, d1/10,-0.8, d2/10);
        }
    }

}
