/*
 * Copyright (c) 2023 pihta24 <admin@pihta24.ru>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package ru.pihta24.achievementplugin

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.plugin.java.JavaPlugin
import ru.pihta24.achievementplugin.utils.DefaultConfig


class AchievementRace: JavaPlugin(), Listener {
    lateinit var game: Game
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        config.addDefault("goals", DefaultConfig.goalsDefaults)
        config.addDefault("round_time", 300)
        config.options().copyDefaults(true)
        saveConfig()

        getCommand("start_race")?.setExecutor(StartExecutor(this))
        getCommand("stop_race")?.setExecutor(StopExecutor(this))
    }

    override fun onDisable() {
        if (gameStarted()) game.stop()
        Bukkit.getScheduler().cancelTasks(this)
    }

    @EventHandler
    fun onPlayerAdvancementDone(event: PlayerAdvancementDoneEvent){
        if (gameStarted()) game.processAdvancementEvent(event)
    }

    fun gameStarted(): Boolean {
        if (!::game.isInitialized) return false
        return game.running
    }
}