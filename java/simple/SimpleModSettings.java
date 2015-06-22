package simple;

import com.google.common.collect.Maps;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Map;

/**
 * Created by Jason Robertson on 6/21/15.
 */
public class SimpleModSettings {

    private static Map<String, String> settings = Maps.newHashMap();

    static {
        settings.put("movement_speed_boost", "0.07");
        settings.put("fire_resist_percent", "0.8");
        settings.put("fall_damage_reduce", "7.0");
        settings.put("jump_height_increase", "0.2");
        settings.put("jump_horizontal_multiplier", "2.0");
        settings.put("jump_horizontal_sprint_multiplier", "3.0");
    }

    public static void loadConfig(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        for(Map.Entry<String, String> setting : settings.entrySet()) {
            config.getString(setting.getKey(), Configuration.CATEGORY_GENERAL, setting.getValue(), "");
        }

        config.save();
    }

    public static Double get(String key) {
        return Double.parseDouble(settings.get(key));
    }

}
