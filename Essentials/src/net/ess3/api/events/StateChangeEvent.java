package net.ess3.api.events;

import net.ess3.api.IUser;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;


/**
 * This handles common boilerplate for events for changes in state.
 * For boolean state, events should extend StatusChangeEvent instead.
 */
public abstract class StateChangeEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    final IUser affected;
    final IUser controller;

    public StateChangeEvent(IUser affected, IUser controller) {
        super();
        this.affected = affected;
        this.controller = controller;
    }

    public StateChangeEvent(boolean isAsync, IUser affected, IUser controller) {
        super(isAsync);
        this.affected = affected;
        this.controller = controller;
    }

    /**
     * Get the user who is affected by the state change.
     *
     * @return The user who is affected by the state change.
     */
    public IUser getAffected() {
        return this.affected;
    }

    /**
     * Get the user who caused the state change.
     *
     * @return The user who caused the state change.
     */
    public IUser getController() {
        return controller;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
