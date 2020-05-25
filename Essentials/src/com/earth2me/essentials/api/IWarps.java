package com.earth2me.essentials.api;

import com.earth2me.essentials.IConf;
import com.earth2me.essentials.IUser;
import com.earth2me.essentials.commands.WarpNotFoundException;
import org.bukkit.Location;

import java.util.Collection;
import java.util.UUID;

@SuppressWarnings("unused")
public interface IWarps extends IConf {
    /**
     * Get a warp by name
     *
     * @param warp - Warp name
     *
     * @return - Location the warp is set to
     *
     * @throws WarpNotFoundException When the warp is not found
     * @throws net.ess3.api.InvalidWorldException When the world the warp is in is not found
     */
    Location getWarp(String warp) throws WarpNotFoundException, net.ess3.api.InvalidWorldException;

    /**
     * Gets a list of warps
     *
     * @return - A {@link Collection} of warps
     */
    Collection<String> getList();

    /**
     * Gets the number of warps
     *
     * @return the size of the list of warps
     */
    int getCount();

    /**
     * Delete a warp from the warp DB
     *
     * @param name - Name of warp
     *
     * @throws Exception
     */
    void removeWarp(String name) throws Exception;

    /**
     * Set a warp
     *
     * @param name - Name of warp
     * @param loc  - Location of warp
     *
     * @throws Exception
     */
    void setWarp(String name, Location loc) throws Exception;

    /**
     * Set a warp
     *
     * @param user - User of warp
     * @param name - Name of warp
     * @param loc  - Location of warp
     *
     * @throws Exception
     */
    void setWarp(IUser user, String name, Location loc) throws Exception;
      
    /**
     * Gets Lastowner UUID
     *   
     * @param warp - Name of warp
     *
     * @throws WarpNotFoundException
     */
    UUID getLastOwner(String warp) throws WarpNotFoundException;
    /**
     * Check to see if the file is empty
     *
     * @return true if empty, false otherwise
     */
    boolean isEmpty();
}
