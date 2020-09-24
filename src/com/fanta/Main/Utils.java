package com.fanta.Main;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class Utils {


    public static Location addingLoc(Location loc, double x, double y, double z) {
        return new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z);
    }


}
