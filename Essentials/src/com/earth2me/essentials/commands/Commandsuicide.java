package com.earth2me.essentials.commands;

import com.earth2me.essentials.User;
import org.bukkit.Server;
import org.bukkit.event.entity.EntityDamageEvent;

import static com.earth2me.essentials.I18n.tl;

@SuppressWarnings("unused")
public class Commandsuicide extends EssentialsCommand {
    public Commandsuicide() {
        super("suicide");
    }

    @Override
    public void run(final Server server, final User user, final String commandLabel, final String[] args) {
        EntityDamageEvent ede = new EntityDamageEvent(user.getBase(), EntityDamageEvent.DamageCause.SUICIDE, Short.MAX_VALUE);
        server.getPluginManager().callEvent(ede);
        ede.getEntity().setLastDamageCause(ede);
        user.getBase().damage(Short.MAX_VALUE);
        if (user.getBase().getHealth() > 0) {
            user.getBase().setHealth(0);
        }
        user.sendMessage(tl("suicideMessage"));
        ess.broadcastMessage(user, tl("suicideSuccess", user.getDisplayName()));
    }
}
