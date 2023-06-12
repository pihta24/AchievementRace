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
import java.util.*
import kotlin.math.min

class AdvancementsGenerator(private val plugin: Plugin) {

    private val players = mutableMapOf<UUID, List<String>>()
    private val goals = mutableListOf<String>()
    private val names = mutableMapOf<String, Map<String, String>>()
    init {
        Bukkit.getOnlinePlayers().forEach {
            if (it.gameMode === GameMode.SURVIVAL) players[it.uniqueId] = listOf()
        }

        plugin.config.getList("goals")?.forEach {
            if (it !is Map<*, *>) throw Exception("goal is not a map")
            names[it["key"] as String] = mapOf(
                "name" to it["name"] as String,
                "description" to it ["description"] as String
            )
        }
    }

    fun calculateWeights(round: Int) {
        goals.clear()

        plugin.config.getList("goals")?.forEach {
            if (it !is Map<*, *>) throw Exception("goal is not a map")
            if (!it.containsKey("key") || it["key"] !is String) throw Exception("goal doesn't have key")
            if (!it.containsKey("name") || it["name"] !is String) throw Exception("goal doesn't have name")
            if (!it.containsKey("description") || it["description"] !is String) throw Exception("goal doesn't have description")
            if ((!it.containsKey("weight") ||  it["weight"] !is Int) &&
                (!it.containsKey("weights") ||  it["weights"] !is List<*>) &&
                (!it.containsKey("start_weight") || !it.containsKey("end_weight") || !it.containsKey("step")
                        || it["start_weight"] !is Int || it["end_weight"] !is Int || it["step"] !is Double)
                ) throw Exception("goal doesn't have weight")

            if (it.containsKey("weight") && it["weight"] is Int) {
                for (i in 1..it["weight"] as Int) {
                    goals.add(it["key"] as String)
                }
            } else if (it.containsKey("weights") && it["weights"] is List<*>) {
                for (i in 1..(it["weights"] as List<*>)[min(round, (it["weights"] as List<*>).size - 1)] as Int) {
                    goals.add(it["key"] as String)
                }
            } else {
                val startWeight = it["start_weight"] as Int
                val endWeight = it["end_weight"] as Int
                val step = it["step"] as Double

                var weight = (startWeight + (round) * step).toInt()
                if (endWeight >= 0) {
                    if  (endWeight < weight && step > 0) {
                        weight = endWeight
                    }
                    if (endWeight > weight && step < 0) {
                        weight = endWeight
                    }
                }
                if (weight < 0) weight = 0
                for (i in 1..weight) {
                    goals.add(it["key"] as String)
                }
            }
        }
    }

    fun generate(uuid: UUID): String {
        if (goals.isEmpty()) throw Exception("No goals, please check config or call calculateWeights")
        goals.shuffle()

        var index = 0
        var looped = false

        while (players[uuid]!!.contains(goals[index]) || Bukkit.getAdvancement(NamespacedKey("minecraft", goals[index])) === null){
            index++
            if (index == goals.size){
                if (looped) throw Exception("No correct goals keys, please check config")
                players[uuid] = listOf()
                index = 0
                looped = true
            }
        }

        players[uuid] = players[uuid]!! + listOf(goals[index])

        return goals[index]
    }

    fun getName(key: String): Map<String, String> {
        return names[key]!!
    }
}