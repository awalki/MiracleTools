package net.letmiracle.miracletools.settings;

import org.mineacademy.fo.settings.ConfigSection;
import org.mineacademy.fo.settings.SimpleSettings;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Represents the plugin configuration for MiracleTools
 */
@SuppressWarnings("unused")
public final class Settings extends SimpleSettings {
    @Override
    protected List<String> getUncommentedSections() {
        return Arrays.asList("Suffix.List", "HideNameTag.Blacklist", "Observer.Blocks");
    }

    public static class SampleSection {
        public static Boolean WHITESPACE;
        public static Map<String, ConfigSection> LIST;

        /*
         * Automatically called method when we load settings.yml to load values in this subclass
         */
        private static void init() {
            setPathPrefix("Suffix");

            WHITESPACE = getBoolean("Whitespace");
            LIST = getMap("List", String.class, ConfigSection.class);
        }
    }

    public static class HideNameTag {
        public static List<String> BLACKLIST;

        private static void init() {
            setPathPrefix("HideNameTag");

            BLACKLIST = getStringList("Blacklist");
        }
    }

    public static class Observer {
        public static List<String> BLOCKS;

        private static void init() {
            setPathPrefix("Observer");

            BLOCKS = getStringList("Blocks");
        }
    }

    /*
     * Automatically called method when we load settings.yml to load values in this class
     * See above for usage.
     */
    private static void init() {
        setPathPrefix(null);
    }
}
