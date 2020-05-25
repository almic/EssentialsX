package com.earth2me.essentials;

import com.earth2me.essentials.commands.WarpNotFoundException;
import com.earth2me.essentials.utils.StringUtil;
import net.ess3.api.InvalidWorldException;
import org.bukkit.Location;
import org.bukkit.Server;
import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.earth2me.essentials.I18n.tl;


public class Warps implements IConf, net.ess3.api.IWarps {
    private static final Logger logger = Logger.getLogger("Essentials");
    private final Map<StringIgnoreCase, EssentialsConf> warpPoints = new HashMap<>();
    private final File warpsFolder;
    private final Server server;

    public Warps(Server server, File dataFolder) {
        this.server = server;
        warpsFolder = new File(dataFolder, "warps");
        if (!warpsFolder.exists()) {
            //noinspection ResultOfMethodCallIgnored
            warpsFolder.mkdirs();
        }
        reloadConfig();
    }

    @Override
    public boolean isEmpty() {
        return warpPoints.isEmpty();
    }

    @Override
    public Collection<String> getList() {
        final List<String> keys = new ArrayList<>();
        for (StringIgnoreCase stringIgnoreCase : warpPoints.keySet()) {
            keys.add(stringIgnoreCase.getString());
        }
        keys.sort(String.CASE_INSENSITIVE_ORDER);
        return keys;
    }

    @Override
    public Location getWarp(String warp) throws WarpNotFoundException, InvalidWorldException {
        EssentialsConf conf = warpPoints.get(new StringIgnoreCase(warp));
        if (conf == null) {
            throw new WarpNotFoundException();
        }
        return conf.getLocation(null, server);
    }

    @Override
    public void setWarp(String name, Location loc) throws Exception {
        setWarp(null, name, loc);
    }

    @Override
    public void setWarp(IUser user, String name, Location loc) throws Exception {
        String filename = StringUtil.sanitizeFileName(name);
        EssentialsConf conf = warpPoints.get(new StringIgnoreCase(name));
        if (conf == null) {
            File confFile = new File(warpsFolder, filename + ".yml");
            if (confFile.exists()) {
                throw new Exception(tl("similarWarpExist"));
            }
            conf = new EssentialsConf(confFile);
            warpPoints.put(new StringIgnoreCase(name), conf);
        }
        conf.setProperty(null, loc);
        conf.setProperty("name", name);
        if (user != null) conf.setProperty("lastowner", user.getBase().getUniqueId().toString());
        conf.save();
    }

    @Override
    public UUID getLastOwner(String warp) throws WarpNotFoundException {
        EssentialsConf conf = warpPoints.get(new StringIgnoreCase(warp));
        if (conf == null) {
            throw new WarpNotFoundException();
        }
        UUID uuid = null;
        try {
            uuid = UUID.fromString(conf.getString("lastowner"));
        }
        catch (Exception ignored) {}
        return uuid;
    }

    @Override
    public void removeWarp(String name) throws Exception {
        EssentialsConf conf = warpPoints.get(new StringIgnoreCase(name));
        if (conf == null) {
            throw new Exception(tl("warpNotExist"));
        }
        if (!conf.getFile().delete()) {
            throw new Exception(tl("warpDeleteError"));
        }
        warpPoints.remove(new StringIgnoreCase(name));
    }

    @Override
    public final void reloadConfig() {
        warpPoints.clear();
        File[] listOfFiles = warpsFolder.listFiles();
        if (listOfFiles != null && listOfFiles.length >= 1) {
            for (File listOfFile : listOfFiles) {
                String filename = listOfFile.getName();
                if (listOfFile.isFile() && filename.endsWith(".yml")) {
                    try {
                        EssentialsConf conf = new EssentialsConf(listOfFile);
                        conf.load();
                        String name = conf.getString("name");
                        if (name != null) {
                            warpPoints.put(new StringIgnoreCase(name), conf);
                        }
                    } catch (Exception ex) {
                        logger.log(Level.WARNING, tl("loadWarpError", filename), ex);
                    }
                }
            }
        }
    }

    @Override
    public int getCount() {
        return getList().size();
    }

    private static class StringIgnoreCase {
        private final String string;

        public StringIgnoreCase(String string) {
            this.string = string;
        }

        @Override
        public int hashCode() {
            return getString().toLowerCase(Locale.ENGLISH).hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof StringIgnoreCase) {
                return getString().equalsIgnoreCase(((StringIgnoreCase) o).getString());
            }
            return false;
        }

        public String getString() {
            return string;
        }
    }
}
