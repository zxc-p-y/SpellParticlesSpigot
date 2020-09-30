package com.fanta.Main;

import org.bukkit.Location;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.List;

public class Utils {


    public static Location addingLoc(Location loc, double x, double y, double z) {
        return new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z);
    }

    //--------------------------------------------------------------------------------------------------------------------------

    // MetaData

    public static void setMetadata(Metadatable object, String key, Object value, Plugin plugin) {
        object.setMetadata(key, new FixedMetadataValue(plugin, value));
    }

    public static Object getMetadata(Metadatable object, String key, Plugin main) {
        List<MetadataValue> values = object.getMetadata(key);
        for (MetadataValue value : values) {
            if (value.getOwningPlugin() == main) {
                return value.value();
            }
        }
        return null;
    }


    public static int getMetadataInt(Metadatable object, String key, Main main) {
        Object valueDamage = Utils.getMetadata(object, key, main);
        if (valueDamage != null) {
            return ((int) valueDamage);
        } else {
            return 0;
        }
    }
    public static double getMetadataDouble(Metadatable object, String key, Main main) {
        Object value = Utils.getMetadata(object, key, main);
        if (value != null) {
            return ((double) value);
        } else {
            return 0;
        }
    }

    public static String getMetadataString(Metadatable object, String key, Main main) {
        Object value = Utils.getMetadata(object, key, main);
        if (value != null ) {
            return ((String) value);
        } else {
            return "";
        }
    }



}
