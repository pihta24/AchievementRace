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

package ru.pihta24.achievementplugin.utils

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.NamespacedKey
import org.bukkit.plugin.Plugin
import java.util.UUID

class AdvancementsGenerator(plugin: Plugin) {

    private val players = mutableMapOf<UUID, List<String>>()
    private val goals = mutableListOf<String>()
    private val names = mutableMapOf<String, Map<String, String>>()
    init {
        Bukkit.getOnlinePlayers().forEach {
            if (it.gameMode === GameMode.SURVIVAL) players[it.uniqueId] = listOf()
        }

        plugin.config.getList("goals")?.forEach {
            it as Map<*, *>
            for (i in 1..it["weight"] as Int){
                goals.add(it["key"] as String)
            }
            names[it["key"] as String] = mapOf(
                "name" to it["name"] as String,
                "description" to it ["description"] as String
            )
        }
    }

    fun generate(uuid: UUID): String {
        goals.shuffle()
        var index = 0

        while (players[uuid]!!.contains(goals[index]) || Bukkit.getAdvancement(NamespacedKey("minecraft", goals[index])) === null){
            index++
            if (index == goals.size){
                players[uuid] = listOf()
                index = 0
            }
        }

        players[uuid] = players[uuid]!! + listOf(goals[index])

        return goals[index]
    }

    fun getName(key: String): Map<String, String> {
        return names[key]!!
    }
}