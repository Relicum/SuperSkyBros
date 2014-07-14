package com.relicum.scb.arena;

import com.relicum.scb.objects.ArenaLobby;
import com.relicum.scb.types.SkyApi;
import org.bukkit.configuration.ConfigurationSection;

/**
 * SuperSkyBros First Created 25/10/13
 *
 * @author Relicum
 * @version 0.1
 */
public class ALobbyIO {

    private ArenaLobby arenaLobby;

    private ConfigurationSection section;

    private Arena arena;


    public ALobbyIO(ArenaLobby arenaLobby) {

        this.arenaLobby = arenaLobby;
        this.section = SkyApi.getSm().getArenaConfig().getConfig().getConfigurationSection("arena.arenas." + arenaLobby.getArenaId());
        this.arena = SkyApi.getArenaManager().getArenaById(arenaLobby.getArenaId());
    }

    public ArenaLobby getArenaLobby() {
        return arenaLobby;
    }

    public void setArenaLobby(ArenaLobby arenaLobby) {
        this.arenaLobby = arenaLobby;
    }

    public ConfigurationSection getSection() {
        return section;
    }

    public void setSection(ConfigurationSection section) {
        this.section = section;
    }

    public void saveLobby() {

        this.section.set("lobby.min", this.arenaLobby.getMin());
        this.section.set("lobby.max", this.arenaLobby.getMax());
        this.section.set("lobby.spawn", this.arenaLobby.getSpawn().toVector());
        this.section.set("lobby.world", this.arenaLobby.getWorld().getName());
        this.section.set("lobby.id", this.arenaLobby.getArenaId());

        SkyApi.getSm().getArenaConfig().saveConfig();
        SkyApi.getSm().getArenaConfig().reloadConfig();
        this.arena.setArenaLobby(arenaLobby);
        this.arena.saveArena();

    }
}
