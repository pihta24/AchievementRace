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

object DefaultConfig {
    const val timeDefault = 300

    /*
     * config format:
     * key: string - unique key of achievement (required)
     * name: string - name of achievement  (required)
     * weight: int - weight of achievement for all rounds (optional)
     * weights: list of ints - weights of achievement for each round (optional)
     *  last element is used for rounds after last element
     * start_weight: int - weight of achievement for first round (optional)
     * step: float - step of weight for each round (optional)
     *  if float, weight is changing not every round
     *  can be negative
     * end_weight: int - weight of achievement to stop changing (optional)
     *  if negative, weight is changing without stop
     * description: string - description of achievement (required, can be empty)
     *
     * weights are used for random generation of achievement
     *
     * one of following must be specified:
     *  weight
     *  weights
     *  start_weight, step, end_weight
     */

    val goalsDefaults = listOf(
        mapOf(
            "key" to "story/mine_stone",
            "name" to "Stone Age",
            "weight" to 100,
            "description" to ""
        ),
        mapOf(
            "key" to "story/upgrade_tools",
            "name" to "Getting an Upgrade",
            "weight" to 200,
            "description" to ""
        ),
        mapOf(
            "key" to "story/smelt_iron",
            "name" to "Acquire Hardware",
            "weight" to 200,
            "description" to ""
        ),
        mapOf(
            "key" to "story/obtain_armor",
            "name" to "Suit Up",
            "weight" to 250,
            "description" to ""
        ),
        mapOf(
            "key" to "story/lava_bucket",
            "name" to "Hot Stuff",
            "weight" to 300,
            "description" to ""
        ),
        mapOf(
            "key" to "story/iron_tools",
            "name" to "Isn't It Iron Pick",
            "weight" to 250,
            "description" to ""
        ),
        mapOf(
            "key" to "story/deflect_arrow",
            "name" to "Not Today, Thank You",
            "weight" to 100,
            "description" to ""
        ),
        mapOf(
            "key" to "story/form_obsidian",
            "name" to "Ice Bucket Challenge",
            "weight" to 50,
            "description" to ""
        ),
        mapOf(
            "key" to "story/mine_diamond",
            "name" to "Diamonds!",
            "weight" to 100,
            "description" to ""
        ),
        mapOf(
            "key" to "story/enter_the_nether",
            "name" to "We Need to Go Deeper",
            "weight" to 100,
            "description" to ""
        ),
        mapOf(
            "key" to "story/shiny_gear",
            "name" to "Cover Me with Diamonds",
            "weight" to 50,
            "description" to ""
        ),
        mapOf(
            "key" to "story/enchant_item",
            "name" to "Enchanter",
            "weight" to 20,
            "description" to ""
        ),
        mapOf(
            "key" to "story/cure_zombie_villager",
            "name" to "Zombie Doctor",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "story/follow_ender_eye",
            "name" to "Eye Spy",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "story/enter_the_end",
            "name" to "The End?",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/return_to_sender",
            "name" to "Return to Sender",
            "weight" to 20,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/find_bastion",
            "name" to "Those Were the Days",
            "weight" to 30,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/obtain_ancient_debris",
            "name" to "Hidden in the Depths",
            "weight" to 10,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/fast_travel",
            "name" to "Subspace Bubble",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/find_fortress",
            "name" to "A Terrible Fortress",
            "weight" to 30,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/obtain_crying_obsidian",
            "name" to "Who is Cutting Onions?",
            "weight" to 25,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/distract_piglin",
            "name" to "Oh Shiny",
            "weight" to 25,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/ride_strider",
            "name" to "This Boat Has Legs",
            "weight" to 1,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/uneasy_alliance",
            "name" to "Uneasy Alliance",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/loot_bastion",
            "name" to "War Pigs",
            "weight" to 30,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/use_lodestone",
            "name" to "Country Lode, Take Me Home",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/netherite_armor",
            "name" to "Cover Me in Debris",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/get_wither_skull",
            "name" to "Spooky Scary Skeleton",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/obtain_blaze_rod",
            "name" to "Into Fire",
            "weight" to 10,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/charge_respawn_anchor",
            "name" to "Not Quite \"Nine\" Lives",
            "weight" to 10,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/ride_strider_in_overworld_lava",
            "name" to "Feels Like Home",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/explore_nether",
            "name" to "Hot Tourist Destinations",
            "weight" to 1,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/summon_wither",
            "name" to "Withering Heights",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/brew_potion",
            "name" to "Local Brewery",
            "weight" to 5,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/create_beacon",
            "name" to "Bring Home the Beacon",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/all_potions",
            "name" to "A Furious Cocktail",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/create_full_beacon",
            "name" to "Beaconator",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "nether/all_effects",
            "name" to "How Did We Get Here?",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "end/kill_dragon",
            "name" to "Free the End",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "end/dragon_egg",
            "name" to "The Next Generation",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "end/enter_end_gateway",
            "name" to "Remote Getaway",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "end/respawn_dragon",
            "name" to "The End... Again...",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "end/dragon_breath",
            "name" to "You Need a Mint",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "end/find_end_city",
            "name" to "The City at the End of the Game",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "end/elytra",
            "name" to "Sky's the Limit",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "end/levitate",
            "name" to "Great View From Up Here",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/voluntary_exile",
            "name" to "Voluntary Exile",
            "weight" to 7,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/spyglass_at_parrot",
            "name" to "Is It a Bird?",
            "weight" to 1,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/kill_a_mob",
            "name" to "Monster Hunter",
            "weight" to 300,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/read_power_of_chiseled_bookshelf",
            "name" to "The Power of Books",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/trade",
            "name" to "What a Deal!",
            "weight" to 50,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/trim_with_any_armor_pattern",
            "name" to "Crafting a New Look",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/honey_block_slide",
            "name" to "Sticky Situation",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/ol_betsy",
            "name" to "Ol' Betsy",
            "weight" to 25,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/lightning_rod_with_villager_no_fire",
            "name" to "Surge Protector",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/fall_from_world_height",
            "name" to "Caves & Cliffs",
            "weight" to 1,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/salvage_sherd",
            "name" to "Respecting the Remnants",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/avoid_vibration",
            "name" to "Sneak 100",
            "weight" to 1,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/sleep_in_bed",
            "name" to "Sweet Dreams",
            "weight" to 5,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/hero_of_the_village",
            "name" to "Hero of the Village",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/spyglass_at_ghast",
            "name" to "Is It a Balloon?",
            "weight" to 1,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/throw_trident",
            "name" to "A Throwaway Joke",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/kill_mob_near_sculk_catalyst",
            "name" to "It Spreads",
            "weight" to 1,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/shoot_arrow",
            "name" to "Take Aim",
            "weight" to 50,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/kill_all_mobs",
            "name" to "Monsters Hunted",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/totem_of_undying",
            "name" to "Postmortal",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/summon_iron_golem",
            "name" to "Hired Help",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/trade_at_world_height",
            "name" to "Star Trader",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/trim_with_all_exclusive_armor_patterns",
            "name" to "Smithing with Style",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/two_birds_one_arrow",
            "name" to "Two Birds, One Arrow",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/whos_the_pillager_now",
            "name" to "Who's the Pillager Now?",
            "weight" to 1,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/arbalistic",
            "name" to "Arbalistic",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/craft_decorated_pot_using_only_sherds",
            "name" to "Careful Restoration",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/adventuring_time",
            "name" to "Adventuring Time",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/play_jukebox_in_meadows",
            "name" to "Sound of Music",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/walk_on_powder_snow_with_leather_boots",
            "name" to "Light as a Rabbit",
            "weight" to 10,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/spyglass_at_dragon",
            "name" to "Is It a Plane?",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/very_very_frightening",
            "name" to "Very Very Frightening",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/sniper_duel",
            "name" to "Sniper Duel",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "adventure/bullseye",
            "name" to "Bullseye",
            "weight" to 1,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/safely_harvest_honey",
            "name" to "Bee Our Guest",
            "weight" to 1,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/breed_an_animal",
            "name" to "The Parrots and the Bats",
            "weight" to 100,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/allay_deliver_item_to_player",
            "name" to "You've Got a Friend in Me",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/ride_a_boat_with_a_goat",
            "name" to "Whatever Floats Your Goat!",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/tame_an_animal",
            "name" to "Best Friends Forever",
            "weight" to 70,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/make_a_sign_glow",
            "name" to "Glow and Behold!",
            "weight" to 60,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/fishy_business",
            "name" to "Fishy Business",
            "weight" to 60,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/silk_touch_nest",
            "name" to "Total Beelocation",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/tadpole_in_a_bucket",
            "name" to "Bukkit Bukkit",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/obtain_sniffer_egg",
            "name" to "Smells Interesting",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/plant_seed",
            "name" to "A Seedy Place",
            "weight" to 100,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/wax_on",
            "name" to "Wax On",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/bred_all_animals",
            "name" to "Two by Two",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/allay_deliver_cake_to_note_block",
            "name" to "Birthday Song",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/complete_catalogue",
            "name" to "A Complete Catalogue",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/tactical_fishing",
            "name" to "Tactical Fishing",
            "weight" to 100,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/leash_all_frog_variants",
            "name" to "When the Squad Hops into Town",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/feed_snifflet",
            "name" to "Little Sniffs",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/balanced_diet",
            "name" to "A Balanced Diet",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/obtain_netherite_hoe",
            "name" to "Serious Dedication",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/wax_off",
            "name" to "Wax Off",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/axolotl_in_a_bucket",
            "name" to "The Cutest Predator",
            "weight" to 10,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/froglights",
            "name" to "With Our Powers Combined!",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/kill_axolotl_target",
            "name" to "The Healing Power of Friendship!",
            "weight" to 0,
            "description" to ""
        ),
        mapOf(
            "key" to "husbandry/plant_any_sniffer_seed",
            "name" to "Planting the Past",
            "weight" to 0,
            "description" to ""
        )
    )
}