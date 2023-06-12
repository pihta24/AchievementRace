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

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.title.Title
import net.kyori.adventure.title.Title.Times
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitTask
import ru.pihta24.achievementplugin.utils.AdvancementsGenerator
import ru.pihta24.achievementplugin.utils.DefaultConfig
import java.time.Duration
import java.time.Instant
import java.util.UUID

class Game(private val plugin: AchievementRace, start: Player) {
    private val generator = AdvancementsGenerator(plugin)
    private val players = mutableSetOf<Player>()
    private val completed = mutableSetOf<Player>()
    private val goals = mutableMapOf<UUID, String>()
    private lateinit var bar: BossBar
    private lateinit var task: BukkitTask
    private var roundTime = plugin.config.getInt("round_time", DefaultConfig.timeDefault) * 1000
    var running = true
    private var allDone = false

    init {
        Bukkit.getOnlinePlayers().forEach {
            if (it.gameMode === GameMode.SURVIVAL) players.add(it)
        }

        if (players.size <= 1) {
            Bukkit.getServer()
                .sendMessage(Component.text("You need more players").color(TextColor.fromHexString("#FF0000")))
            running = false
        } else {

            bar =
                BossBar.bossBar(
                    Component.text("Time left: ${roundTime / 1000}"),
                    1F,
                    BossBar.Color.GREEN,
                    BossBar.Overlay.PROGRESS
                )

            giveEffects()

            for (i in players) {
                i.inventory.clear()
                i.showBossBar(bar)
                if (i != start) i.teleport(start)
            }

            generateGoals()
            sendGoals()

            task = Bukkit.getScheduler().runTaskAsynchronously(plugin, Runnable {
                countdown()

                Bukkit.getScheduler().runTask(plugin, Runnable {
                    clearEffects()
                })


                while (players.size > 1 && running) {
                    val startedAt = Instant.now().toEpochMilli()
                    var now = Instant.now().toEpochMilli()
                    allDone = false
                    while (now < startedAt + roundTime && running && !allDone) {
                        Bukkit.getScheduler().runTask(plugin, Runnable {
                            bar.name(Component.text("Time left: ${(startedAt + roundTime - now) / 1000}"))
                            bar.progress((startedAt + roundTime - now) / (1F * roundTime))
                        })
                        Thread.sleep(500)
                        now = Instant.now().toEpochMilli()
                    }

                    if (!running) {
                        return@Runnable
                    }

                    Bukkit.getScheduler().runTask(plugin, Runnable {
                        giveEffects()
                    })

                    val notCompleted = players.subtract(completed)

                    var message: Component = Component
                        .text("Completed the round:")
                        .color(
                            TextColor.fromHexString("#00FF00")
                        )

                    for (i in completed) {
                        message = message
                            .appendNewline()
                            .append {
                                Component
                                    .text(i.name)
                                    .color(
                                        TextColor.fromHexString("#00FF00")
                                    )
                            }
                    }

                    completed.clear()

                    message = message
                        .appendNewline()
                        .append {
                            Component
                                .text("Did not complete the round:")
                                .color(
                                    TextColor.fromHexString("#FF0000")
                                )
                        }

                    for (i in notCompleted) {
                        Bukkit.getScheduler().runTask(plugin, Runnable {
                            i.hideBossBar(bar)
                            i.removePotionEffect(PotionEffectType.SLOW)
                            i.removePotionEffect(PotionEffectType.SLOW_DIGGING)
                            i.removePotionEffect(PotionEffectType.BLINDNESS)
                            i.removePotionEffect(PotionEffectType.JUMP)
                        })
                        players.remove(i)
                        message = message
                            .appendNewline()
                            .append {
                                Component
                                    .text(i.name)
                                    .color(
                                        TextColor.fromHexString("#FF0000")
                                    )
                            }
                    }

                    when (players.size) {
                        0 -> {
                            message = message
                                .appendNewline()
                                .append {
                                    Component
                                        .text("No one won this game")
                                        .color(
                                            TextColor.fromHexString("#FF0000")
                                        )
                                }
                            Bukkit.getScheduler().runTask(plugin, Runnable {
                                Bukkit.getServer().sendMessage(message)
                            })
                        }

                        1 -> {
                            val player = players.iterator().next()
                            message = message
                                .appendNewline()
                                .append {
                                    Component
                                        .text("${player.name} won this game")
                                        .color(
                                            TextColor.fromHexString("#00FF00")
                                        )
                                }
                            Bukkit.getScheduler().runTask(plugin, Runnable {
                                player.hideBossBar(bar)
                                player.removePotionEffect(PotionEffectType.SLOW)
                                player.removePotionEffect(PotionEffectType.SLOW_DIGGING)
                                player.removePotionEffect(PotionEffectType.BLINDNESS)
                                player.removePotionEffect(PotionEffectType.JUMP)
                                Bukkit.getServer().sendMessage(message)
                            })
                        }

                        else -> {
                            message = message
                                .appendNewline()
                                .append {
                                    Component
                                        .text("New round")
                                        .color(
                                            TextColor.fromHexString("#FFFFFF")
                                        )
                                }

                            generateGoals()

                            Bukkit.getScheduler().runTask(plugin, Runnable {
                                Bukkit.getServer().sendMessage(message)
                                sendGoals()
                            })

                            countdown(5)

                            Bukkit.getScheduler().runTask(plugin, Runnable { clearEffects() })
                        }
                    }

                }
                running = false
            })
        }
    }

    fun processAdvancementEvent(event: PlayerAdvancementDoneEvent) {
        val goal = goals[event.player.uniqueId]
        if (!running) return
        if (!goal.equals(event.advancement.key.key)) return
        completed.add(event.player)
        Bukkit.getServer().sendMessage(
            Component
                .text("${event.player.name} completed the round")
                .color(
                    TextColor.fromHexString("#00FF00")
                )
        )
        if (completed.size == players.size) allDone = true
    }


    private fun giveEffects() {
        if (!running) return
        for (i in players) {
            i.addPotionEffect(
                PotionEffect(
                    PotionEffectType.SLOW,
                    PotionEffect.INFINITE_DURATION,
                    127,
                    false,
                    false
                )
            )
            i.addPotionEffect(
                PotionEffect(
                    PotionEffectType.BLINDNESS,
                    PotionEffect.INFINITE_DURATION,
                    127,
                    false,
                    false
                )
            )
            i.addPotionEffect(
                PotionEffect(
                    PotionEffectType.SLOW_DIGGING,
                    PotionEffect.INFINITE_DURATION,
                    127,
                    false,
                    false
                )
            )
            i.addPotionEffect(
                PotionEffect(
                    PotionEffectType.JUMP,
                    PotionEffect.INFINITE_DURATION,
                    128,
                    false,
                    false
                )
            )
        }
    }

    private fun clearEffects() {
        if (!running) return
        for (i in players) {
            i.removePotionEffect(PotionEffectType.SLOW)
            i.removePotionEffect(PotionEffectType.SLOW_DIGGING)
            i.removePotionEffect(PotionEffectType.BLINDNESS)
            i.removePotionEffect(PotionEffectType.JUMP)
        }
    }

    private fun generateGoals() {
        if (!running) return
        players.forEach { goals[it.uniqueId] = generator.generate(it.uniqueId) }
    }

    private fun sendGoals() {
        if (!running) return
        players.forEach {
            val progress =
                it.getAdvancementProgress(Bukkit.getAdvancement(NamespacedKey("minecraft", goals[it.uniqueId]!!))!!)
            for (j in progress.awardedCriteria) progress.revokeCriteria(j)
            val name = generator.getName(goals[it.uniqueId]!!)
            it.sendMessage(
                Component
                    .text("You need to get: ")
                    .color(
                        TextColor.fromHexString("#00538a")
                    )
                    .append {
                        Component
                            .text(name["name"]!!)
                            .color(
                                TextColor.fromHexString("#cf3476")
                            )
                    }
            )
            if (!name["description"].equals("")) {
                it.sendMessage(
                    Component
                        .text(name["description"]!!)
                        .color(
                            TextColor.fromHexString("#FFFFFF")
                        )
                )
            }
        }
    }

    private fun countdown(from: Int = 3) {
        if (!running) return
        for (i in from downTo 1) {
            players.forEach {
                Bukkit.getScheduler().runTask(plugin, Runnable {
                    it.showTitle(
                        Title.title(
                            Component.text(i).color(TextColor.fromHexString("#00FF00")),
                            Component.empty(),
                            Times.times(
                                Duration.ofMillis(500),
                                Duration.ZERO,
                                Duration.ofMillis(500)
                            )
                        )
                    )
                })

            }
            Thread.sleep(1000)
        }
    }

    fun stop() {
        if (!running) return
        running = false
        for (i in players) {
            i.removePotionEffect(PotionEffectType.SLOW)
            i.removePotionEffect(PotionEffectType.SLOW_DIGGING)
            i.removePotionEffect(PotionEffectType.BLINDNESS)
            i.hideBossBar(bar)
        }
        task.cancel()
    }
}