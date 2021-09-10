/*
 * Copyright (C) 2021 Zerthick
 *
 * This file is part of NoSleep.
 *
 * NoSleep is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NoSleep is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NoSleep.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.zerthick.nosleep;

import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent;
import org.spongepowered.api.event.network.ServerSideConnectionEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;


@Plugin("nosleep")
public class NoSleep {

    private final PluginContainer container;
    private final Logger logger;

    @Inject
    NoSleep(final PluginContainer container, final Logger logger) {
        this.container = container;
        this.logger = logger;
    }

    @Listener
    public void onConstructPlugin(final ConstructPluginEvent event) {
        // Log Start Up to Console
        logger.info(
                container.metadata().name().orElse("Unknown Plugin") + " version " + container.metadata().version()
                        + " enabled!");
    }


    @Listener
    public void onPlayerJoin(ServerSideConnectionEvent.Join event, @Getter("player") ServerPlayer player) {
        // Disable Sleeping for the Player
        player.offer(Keys.IS_SLEEPING_IGNORED, true);
    }
}
